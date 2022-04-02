package com.example.onemoretime.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.onemoretime.R;
import com.example.onemoretime.data.model.Competitions;
import com.example.onemoretime.data.repo.CompetitionsRepo;

public class AddEventDialogFragment extends DialogFragment {
    final static private String TAG = AddEventDialogFragment.class.getSimpleName();

    //the way to deliver action events
    DialogListener mListener;

    private EditText editTextCompId;
    private EditText editTextCompName;
    private EditText editTextCompDate;

    private String compId = "Default";
    private String compName = "Default";
    private String compDate = "XXXX-XX-XX";

    private CompetitionsRepo competitionsRepo;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mListener = (DialogListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
            + " must implement DialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_create_events, null);

        assignViews(view);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("ADD EVENT")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mListener != null){
                            mListener.onDialogPositiveClick(AddEventDialogFragment.this);
                            Competitions competitions = new Competitions();
                            competitions.setCompId(editTextCompId.getText().toString());
                            competitions.setCompName(editTextCompName.getText().toString());
                            competitions.setCompDate(editTextCompDate.getText().toString());
                            competitionsRepo.insert(competitions);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onDialogNegativeClick(AddEventDialogFragment.this);
                    }
                });
        return builder.create();
    }

    private void assignViews(View view){
        competitionsRepo = new CompetitionsRepo();

        editTextCompId = (EditText) view.findViewById(R.id.et_eventID);
        editTextCompName = (EditText) view.findViewById(R.id.eventName);
        editTextCompDate = (EditText) view.findViewById(R.id.et_eventDate);
    }

}
