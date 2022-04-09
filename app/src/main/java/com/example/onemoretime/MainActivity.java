package com.example.onemoretime;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.onemoretime.R;
import com.example.onemoretime.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends Fragment {

    private Spinner teamSpinner;
    private FragmentMatchHomeBinding binding;
    private int match = 1;
    ArrayList<Integer> teams = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMatchHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        addTeams();
        setUpSpinners();

        Button button = root.findViewById(R.id.button_start_match_scouting);
        Button allianceSelector = root.findViewById(R.id.matchHomeAllianceSelector);
        Button plusMatch = root.findViewById(R.id.plusMatch);
        Button minusMatch = root.findViewById(R.id.minusMatch);
        TextView matchNum = root.findViewById(R.id.textView_matchNumber);
        matchNum.setText(String.valueOf(match));

        SharedPreferences preferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(!preferences.contains("deviceAlliance")) {
            editor.putString("deviceAlliance", "red");
        }
        if(preferences.getString("deviceAlliance", "").equals("red")) {
            allianceSelector.setText("Red");
            allianceSelector.setBackgroundColor(Color.parseColor("#FF6961"));
        }

        if(preferences.getString("deviceAlliance", "").equals("blue")) {
            allianceSelector.setText("Blue");
            allianceSelector.setBackgroundColor(Color.parseColor("#6495ED"));
        }
        editor.apply();
        plusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                match++;
                matchNum.setText(String.valueOf(match));
            }
        });

        minusMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (match > 1) {
                    match--;
                    matchNum.setText(String.valueOf(match));
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_match_auto);
                SharedPreferences preferences = getActivity().getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("matchScouting", match);
                editor.putInt("teamScouting", Integer.parseInt(teamSpinner.getSelectedItem().toString()));
                editor.apply();
            }
        });
        allianceSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                if(preferences.getString("deviceAlliance", "").equals("blue")) {
                    allianceSelector.setText("Red");
                    allianceSelector.setBackgroundColor(Color.parseColor("#FF6961"));
                    editor.putString("deviceAlliance", "red");
                }
                if(preferences.getString("deviceAlliance", "").equals("red")) {
                    allianceSelector.setText("Blue");
                    allianceSelector.setBackgroundColor(Color.parseColor("#6495ED"));
                    editor.putString("deviceAlliance", "blue");
                }
                editor.apply();
            }
        });
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                BottomNavigationView scoutingBar = root.findViewById(R.id.scouting_bar);
                NavigationUI.setupWithNavController(scoutingBar, navController);
            }
        }, 10);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addTeams(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Events/UCD/teams");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot rawSnapshot) {
                teams.clear();
                for (DataSnapshot snapshot : rawSnapshot.getChildren()) {
                    if (snapshot.getKey() != null) {
                        teams.add(Integer.valueOf(snapshot.getKey()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void setUpSpinners() {
        View root = binding.getRoot();
        teams.add(0);
        teamSpinner = root.findViewById(R.id.teamSpinner);
        ArrayAdapter<Integer> teamAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, teams);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(teamAdapter);

        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                Toast.makeText(binding.getRoot().getContext(), choice, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}