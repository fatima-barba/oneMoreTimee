package com.example.onemoretime.MatchSouting;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import org.robovikes.frost.R;
import org.robovikes.frost.Utils.SavePage;
import org.robovikes.frost.databinding.FragmentMatchAutoBinding;

public class MatchAutoFragment extends Fragment{

    private FragmentMatchAutoBinding binding;
    protected FragmentActivity Activity;
    public int totalAutoScoreL ;
    public int totalAutoScoreR;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            Activity = (FragmentActivity) context;
        }
    }
    public MAuto() {
    }
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentMatchAutoBinding.inflate(inflater, container, false);
        ViewGroup root = binding.getRoot();
        Button autoPlusL = root.findViewById(R.id.autoPlusL);
        Button autoMinusL = root.findViewById(R.id.autoMinusL);
        Button autoPlusR = root.findViewById(R.id.autoPlusR);
        Button autoMinusR = root.findViewById(R.id.autoMinusR);
        Button teleNav = root.findViewById(R.id.auto_bar);
        TextView autoScoreL = root.findViewById(R.id.textView_upperScoreAuto);
        TextView autoScoreR = root.findViewById(R.id.textView_lowerScoreAuto);
        TextView teamAuto = root.findViewById(R.id.textView_teamAuto);
        TextView matchAuto = root.findViewById(R.id.textView_matchAuto);
        TextView allianceAuto = root.findViewById(R.id.textView_allianceAuto);
        autoScoreL.setText(String.valueOf(totalAutoScoreL));
        autoScoreR.setText(String.valueOf(totalAutoScoreR));
        SavePage.loadSave(this, root);
        SharedPreferences preferences = Activity.getPreferences(MODE_PRIVATE);
        teamAuto.setText("Team: " + preferences.getInt("teamScouting", -1));
        matchAuto.setText("Match: " + preferences.getInt("matchScouting", -1));
        allianceAuto.setText("Alliance: " + preferences.getString("deviceAlliance", ""));
        totalAutoScoreR = preferences.getInt("AutoLowerScore", -1);
        totalAutoScoreL = preferences.getInt("AutoUpperScore", -1);
        autoScoreL.setText(String.valueOf(totalAutoScoreL));
        autoScoreR.setText(String.valueOf(totalAutoScoreR));

        autoPlusL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAutoScoreL++;
                autoScoreL.setText(String.valueOf(totalAutoScoreL));
            }
        });
        autoMinusL.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalAutoScoreL > 0){
                    totalAutoScoreL--;
                    autoScoreL.setText(String.valueOf(totalAutoScoreL));
                }
            }
        }));

        autoPlusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAutoScoreR++;
                autoScoreR.setText(String.valueOf(totalAutoScoreR));
            }
        });

        autoMinusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalAutoScoreR > 0){
                    totalAutoScoreR--;
                    autoScoreR.setText(String.valueOf(totalAutoScoreR));
                }
            }
        });
        teleNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(Activity, R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_match_tele);
            }
        });
        return root;
    }

    @Override
    public void onPause(){
        super.onPause();
        ViewGroup root = binding.getRoot();
        Activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        SharedPreferences preferences = Activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("AutoUpperScore", totalAutoScoreL);
        editor.putInt("AutoLowerScore", totalAutoScoreR);
        editor.apply();
        SavePage.saveLayout(this, root);
        binding = null;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        binding = null;
    }
}