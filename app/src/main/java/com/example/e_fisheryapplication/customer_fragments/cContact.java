package com.example.e_fisheryapplication.customer_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.e_fisheryapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class cContact extends Fragment {


    public cContact() {
        // Required empty public constructor
    }

    public static cContact newInstance(String param1, String param2) {
        cContact fragment = new cContact();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TextView officeno,officename,streetname,cityname,taluka,district,mob,landline;
    FirebaseFirestore fb;
    DocumentReference docref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_c_contact, container, false);

        officeno=root.findViewById(R.id.officeno);
        officename=root.findViewById(R.id.officename);
        streetname=root.findViewById(R.id.streetname);
        cityname=root.findViewById(R.id.cityvillagename);
        taluka=root.findViewById(R.id.talukaname);
        district=root.findViewById(R.id.districtname);

        fb=FirebaseFirestore.getInstance();
        docref=fb.collection("Contact").document("Address");
        docref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        officeno.setText(documentSnapshot.get("Officeno").toString());
                        officename.setText(documentSnapshot.get("Officename").toString());
                        streetname.setText(documentSnapshot.get("Streetname").toString());
                        cityname.setText(documentSnapshot.get("Cityname").toString());
                        taluka.setText(documentSnapshot.get("Taluka").toString());
                        district.setText(documentSnapshot.get("District").toString());
                    }
                });



        //phone
        mob=root.findViewById(R.id.mobile);
        landline=root.findViewById(R.id.landline);
        docref=fb.collection("Contact").document("Phone");
        docref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        mob.setText(documentSnapshot.get("Mobile").toString());
                        landline.setText(documentSnapshot.get("Landline").toString());
                    }
                });


        return root;
    }
}