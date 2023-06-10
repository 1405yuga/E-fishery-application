package com.example.e_fisheryapplication.customer_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_fisheryapplication.CustRecycle;
import com.example.e_fisheryapplication.CustRecycleAdapter;
import com.example.e_fisheryapplication.CustomerRecyclerAdapter;
import com.example.e_fisheryapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class cEngine extends Fragment {



    public cEngine() {
        // Required empty public constructor
    }
    public static cEngine newInstance() {
        cEngine fragment = new cEngine();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    RecyclerView rv;

    ArrayList<CustRecycle> arr;
    CustomerRecyclerAdapter adapter;
    FirebaseFirestore fb;
    CollectionReference colref;

    Integer images[]={R.drawable.floater,R.drawable.fishingnet,R.drawable.marinepump,R.drawable.nylonthread};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_c_engine, container, false);
        rv=root.findViewById(R.id.cenginerv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        arr=new ArrayList<>();
        adapter=new CustomerRecyclerAdapter(arr,getContext());
        rv.setAdapter(adapter);

        fb=FirebaseFirestore.getInstance();
        colref=fb.collection("Engine");

        colref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        int i=0;
                        for(DocumentSnapshot d:list){
                            int unit=0;
                            if(i%2==0){
                                unit=1;
                            }
                            else{
                                unit=0;
                            }
                            CustRecycle e= new CustRecycle("Engine",d.getId(),
                                    Integer.parseInt(d.getData().get("avail").toString()),
                                    Integer.parseInt(d.getData().get("price").toString()),
                                    images[i],unit);
                            i++;
                            arr.add(e);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });
        return root;
    }
}