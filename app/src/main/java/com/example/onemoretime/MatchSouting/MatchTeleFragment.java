package com.example.onemoretime.MatchSouting;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import org.robovikes.frost.R;
import org.robovikes.frost.Utils.SavePage;
import org.robovikes.frost.databinding.FragmentMatchTeleBinding;

public class MatchTeleFragment extends Fragment{

    private FragmentMatchTeleBinding binding;
    protected FragmentActivity Activity;
    private int totalTeleScoreL = 0;
    private int totalTeleScoreR = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity){
            Activity = (FragmentActivity) context;
        }
    }
    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        binding = FragmentMatchTeleBinding.inflate(inflater, container, false);
        ViewGroup root = binding.getRoot();
        Button telePlusL = root.findViewById(R.id.telePlusL);
        Button teleMinusL = root.findViewById(R.id.teleMinusL);
        Button telePlusR = root.findViewById(R.id.telePlusR);
        Button teleMinusR = root.findViewById(R.id.teleMinusR);
        Button endMatch = root.findViewById(R.id.button_end_matchScout);
        Button teleBar = root.findViewById(R.id.tele_bar);
        TextView teleScoreL = root.findViewById(R.id.textView_upperScoreTele);
        TextView teleScoreR = root.findViewById(R.id.textView_lowerScoreTele);
        teleScoreL.setText(String.valueOf(totalTeleScoreL));
        teleScoreR.setText(String.valueOf(totalTeleScoreR));
        SharedPreferences preferences = Activity.getPreferences(MODE_PRIVATE);
        TextView teamAuto = root.findViewById(R.id.textView_teamTele);
        TextView matchAuto = root.findViewById(R.id.textView_matchTele);
        TextView allianceAuto = root.findViewById(R.id.textView_allianceTele);
        totalTeleScoreR = preferences.getInt("TeleLowerScore", -1);
        totalTeleScoreL = preferences.getInt("TeleUpperScore", -1);
        teleScoreL.setText(String.valueOf(totalTeleScoreL));
        teleScoreR.setText(String.valueOf(totalTeleScoreR));
        teamAuto.setText("Team: " + preferences.getInt("teamScouting", -1));
        matchAuto.setText("Match: " + preferences.getInt("matchScouting", -1));
        allianceAuto.setText("Alliance: " + preferences.getString("deviceAlliance", ""));

        telePlusL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalTeleScoreL++;
                teleScoreL.setText(String.valueOf(totalTeleScoreL));
            }
        });

        teleMinusL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalTeleScoreL > 0){
                    totalTeleScoreL--;
                    teleScoreL.setText(String.valueOf(totalTeleScoreL));
                }
            }
        });

        telePlusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalTeleScoreR++;
                teleScoreR.setText(String.valueOf(totalTeleScoreR));
            }
        });

        teleMinusR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalTeleScoreR > 0){
                    totalTeleScoreR--;
                    teleScoreR.setText(String.valueOf(totalTeleScoreR));
                }
            }
        });

        endMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = Activity.getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                int match = preferences.getInt("matchScouting", -1);
                int team = preferences.getInt("teamScouting", -1);
                int autoUpper = preferences.getInt("AutoUpperScore", -1);
                int autoLower = preferences.getInt("AutoLowerScore", -1);
                int teleUpper = preferences.getInt("TeleUpperScore", -1);
                int teleLower = preferences.getInt("TeleLowerScore", -1);
                int scoredPoints = (autoLower * 2) + (autoUpper * 4) + teleLower + (teleUpper * 2);
                int barPoints = 0;
                int barScore = 0;
                int humanPlayerScore = 0;
                int taxiing = 0;
                String alliance = preferences.getString("deviceAlliance", "");
                if(preferences.getBoolean("checkBox_taxiing", false)) {
                    taxiing = 2;
                }
                if(preferences.getBoolean("checkBox_humanPlayerLower", false)) {
                    humanPlayerScore = 2;
                }
                if(preferences.getBoolean("checkBox_humanPlayerUpper", false)) {
                    humanPlayerScore = 4;
                }
                RadioButton low = root.findViewById(R.id.checkBox_lowRungClimbTele);
                RadioButton mid = root.findViewById(R.id.checkBox_midRungClimbTele);
                RadioButton high = root.findViewById(R.id.checkBox_highRungClimbTele);
                RadioButton traversal = root.findViewById(R.id.checkBox_traversalRungClimbTele);
                if(low.isChecked()) {
                    barScore = 1;
                    barPoints = 4;
                }
                if(mid.isChecked()) {
                    barScore = 2;
                    barPoints = 6;
                }
                if(high.isChecked()) {
                    barScore = 3;
                    barPoints = 10;
                }
                if(traversal.isChecked()) {
                    barScore = 4;
                    barPoints = 15;
                }
                int autoPoints = (autoLower * 2) + (autoUpper * 4) + taxiing + humanPlayerScore;
                int telePoints = teleLower + (teleUpper * 4) + barPoints;
                int points = autoPoints + telePoints;
                if(points >= 16) {
                    FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match +  "/rankingPoints").setValue(1);
                }
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match +  "/upperAuto").setValue(autoUpper);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match +  "/lowerAuto").setValue(autoLower);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match +  "/upperTele").setValue(teleUpper);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/lowerTele").setValue(teleLower);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/scoredPoints").setValue(scoredPoints);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/barScore").setValue(barScore);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/barPoints").setValue(barPoints);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/humanPlayerScore").setValue(humanPlayerScore);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/taxiing").setValue(taxiing);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/defense").setValue(taxiing);
                FirebaseDatabase.getInstance().getReference("Events/UCD/teams/" + team + "/matches/" + match + "/alliance").setValue(alliance);

                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/upperAuto").setValue(autoUpper);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/lowerAuto").setValue(autoLower);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/upperTele").setValue(teleUpper);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/lowerTele").setValue(teleLower);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/scoredPoints").setValue(scoredPoints);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/barScore").setValue(barScore);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/barPoints").setValue(barPoints);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/humanPlayerScore").setValue(humanPlayerScore);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/taxiing").setValue(taxiing);
                FirebaseDatabase.getInstance().getReference("Events/UCD/matches/" + match + "/alliances/" + alliance + "/" + team + "/defense").setValue(taxiing);
                editor.putInt("TeleUpperScore", 0);
                editor.putInt("TeleLowerScore", 0);
                editor.putInt("AutoUpperScore", 0);
                editor.putInt("AutoLowerScore", 0);

                editor.remove("checkBox_humanPlayerLower");
                editor.remove("checkBox_humanPlayerUpper");
                editor.remove("checkBox_taxiing");
                editor.remove("checkBox_upper_hubAuto");
                editor.remove("checkBox_lower_hubAuto");
                editor.apply();
                SavePage.clearSave(MTele.this, root);
                NavController navController = Navigation.findNavController(Activity, R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_match_home);
            }
        });
        teleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(Activity, R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_match_auto);
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
        editor.putInt("TeleUpperScore", totalTeleScoreL);
        editor.putInt("TeleLowerScore", totalTeleScoreR);
        editor.apply();
        binding = null;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        binding = null;
    }
}
