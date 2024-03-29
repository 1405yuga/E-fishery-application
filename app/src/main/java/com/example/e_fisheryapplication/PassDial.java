package com.example.e_fisheryapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class PassDial extends AppCompatDialogFragment {
    private EditText password;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_password,null);

        builder.setView(view)
        .setTitle("Society-password")
                .setCancelable(false)
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String passw=password.getText().toString();
                if(passw.equals("A1234")){
                    Intent i =new Intent(getActivity(),MainActivity.class);
                    startActivity(i);
                }else{
                    password.setError("Compulsory");
                    password.setHint("Enter correct password");
                }
            }
        });

        password=view.findViewById(R.id.passw);

        return builder.create();
    }


}
