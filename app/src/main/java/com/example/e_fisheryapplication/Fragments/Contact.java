package com.example.e_fisheryapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fisheryapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class Contact extends Fragment {


    public Contact() {
        // Required empty public constructor
    }

    public static Contact newInstance() {
        Contact fragment = new Contact();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    TextView officeno,officename,streetname,cityname,taluka,district,mob,landline;
    ImageView editphn,editadd;
    FirebaseFirestore fb;
    DocumentReference docref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_contact, container, false);
        //Address
        officeno=root.findViewById(R.id.officeno);
        officename=root.findViewById(R.id.officename);
        streetname=root.findViewById(R.id.streetname);
        cityname=root.findViewById(R.id.cityvillagename);
        taluka=root.findViewById(R.id.talukaname);
        district=root.findViewById(R.id.districtname);
        editadd=root.findViewById(R.id.editAdd);
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
        editphn=root.findViewById(R.id.editPhn);
        docref=fb.collection("Contact").document("Phone");
        docref.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        mob.setText(documentSnapshot.get("Mobile").toString());
                        landline.setText(documentSnapshot.get("Landline").toString());
                    }
                });
        //edit phone
        editphn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogplus=DialogPlus.newDialog(mob.getContext())
                        .setContentHolder(new ViewHolder(R.layout.phoneupdate))
                        .setExpanded(true,1500)
                        .create();
                //get update panel widgets
                View myview=dialogplus.getHolderView();
                EditText mobile=myview.findViewById(R.id.emobile);
                EditText landl=myview.findViewById(R.id.elandline);
                Button upPhn=myview.findViewById(R.id.updatePhone);

                //setvalue
                mobile.setText(mob.getText());
                landl.setText(landline.getText());

                dialogplus.show();

                //update in firestore
                upPhn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> obj=new HashMap<>();
                        obj.put("Landline",Integer.parseInt(landl.getText().toString()));
                        obj.put("Mobile",Integer.parseInt(mobile.getText().toString()));

                        docref=fb.collection("Contact").document("Phone");
                        docref.set(obj)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogplus.dismiss();
                                        mob.setText(mobile.getText().toString());
                                        landline.setText(landl.getText().toString());
                                        Toast.makeText(mob.getContext(),"Updated Successfully!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogplus.dismiss();
                                Toast.makeText(mob.getContext(),"Error in Updation!",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

            }
        });

        //edit address
        editadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogplus=DialogPlus.newDialog(mob.getContext())
                        .setContentHolder(new ViewHolder(R.layout.contact_update))
                        .setExpanded(true,1600)
                        .create();
                //get update panel widgets
                View myview=dialogplus.getHolderView();
                EditText offno=myview.findViewById(R.id.eofficeno);
                EditText offname=myview.findViewById(R.id.eofficename);
                EditText stname=myview.findViewById(R.id.estreetname);
                EditText cname=myview.findViewById(R.id.ecityname);
                EditText tal=myview.findViewById(R.id.etaluka);
                EditText dist=myview.findViewById(R.id.edistrict);
                Button upAdd=myview.findViewById(R.id.updateAdd);

                //setvalue
                offno.setText(officeno.getText());
                offname.setText(officename.getText());
                stname.setText(streetname.getText());
                cname.setText(cityname.getText());
                tal.setText(taluka.getText());
                dist.setText(district.getText());

                dialogplus.show();

                //update in firestore
                upAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> obj=new HashMap<>();
                        obj.put("Cityname",cname.getText().toString());
                        obj.put("District",dist.getText().toString());
                        obj.put("Officename",offname.getText().toString());
                        obj.put("Officeno",offno.getText().toString());
                        obj.put("Streetname",stname.getText().toString());
                        obj.put("Taluka",tal.getText().toString());

                        docref=fb.collection("Contact").document("Address");
                        docref.set(obj)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogplus.dismiss();
                                        officeno.setText(offno.getText().toString());
                                        officename.setText(offname.getText().toString());
                                        streetname.setText(stname.getText().toString());
                                        cityname.setText(cname.getText().toString());
                                        taluka.setText(tal.getText().toString());
                                        district.setText(dist.getText().toString());
                                        Toast.makeText(mob.getContext(),"Updated Successfully!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogplus.dismiss();
                                        Toast.makeText(mob.getContext(),"Error in Updation!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });





            }
        });




        return root;
    }
}