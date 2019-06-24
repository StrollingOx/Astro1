package com.example.anon.astro.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anon.astro.Localization;
import com.example.anon.astro.R;

public class DialogRefreshTime extends AppCompatDialogFragment {
    private EditText mRefreshTime;
    private View view;
    private String valueRefreshTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        initDialog();

        return buildDialog(builder);
    }

    private Dialog buildDialog(AlertDialog.Builder builder) {
        builder
                .setView(view)
                .setTitle("Change refresh time")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        valueRefreshTime = mRefreshTime.getText().toString();
                        if(isInputCorrect(valueRefreshTime)){
                            Localization.setRefreshTime(Integer.parseInt(valueRefreshTime));
                            Toast.makeText(getActivity(),"Refresh time changed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return builder.create();
    }

    private boolean isInputCorrect(String valueRefreshTime) {
        if(valueRefreshTime.startsWith("0")) {
            Toast.makeText(getActivity(), "Can't start with 0", Toast.LENGTH_SHORT).show();
            return false;
        } else if(valueRefreshTime.equals("")){
            Toast.makeText(getActivity(), "Please enter value first", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_refreshtime,null);
        mRefreshTime = view.findViewById(R.id.dialog_refreshtime_time);
    }
}
