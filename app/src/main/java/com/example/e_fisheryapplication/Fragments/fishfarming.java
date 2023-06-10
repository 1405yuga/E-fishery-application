package com.example.e_fisheryapplication.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_fisheryapplication.CustRecycle;
import com.example.e_fisheryapplication.CustRecycleAdapter;
import com.example.e_fisheryapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class fishfarming extends Fragment {


    public fishfarming() {
        // Required empty public constructor
    }

    public static fishfarming newInstance() {
        fishfarming fragment = new fishfarming();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView rv;

    ArrayList<CustRecycle> arr;
    CustRecycleAdapter adapter;
    FirebaseFirestore fb;
    CollectionReference colref;

    Integer images[]={R.drawable.crab,R.drawable.kolambi};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_fishfarming, container, false);

        rv=root.findViewById(R.id.farmingrv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        arr=new ArrayList<>();
        adapter=new CustRecycleAdapter(arr);
        rv.setAdapter(adapter);

        fb=FirebaseFirestore.getInstance();
        colref=fb.collection("Fishfarming");

        colref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        int i=0;
                        for(DocumentSnapshot d:list){
                            CustRecycle e= new CustRecycle("Fishfarming",d.getId(),
                                    Integer.parseInt(d.getData().get("avail").toString()),
                                    Integer.parseInt(d.getData().get("price").toString()),
                                    images[i],0);
                            i++;
                            arr.add(e);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        return root;
    }
}