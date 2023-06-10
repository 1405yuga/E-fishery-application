package com.example.e_fisheryapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_fisheryapplication.CustRecycle;
import com.example.e_fisheryapplication.CustRecycleAdapter;
import com.example.e_fisheryapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView rv;

    ArrayList<CustRecycle> arr;
    CustRecycleAdapter adapter;
    FirebaseFirestore fb;
    CollectionReference colref;

    Integer images[]={R.drawable.ghol2,R.drawable.kolambi,R.drawable.paplet,R.drawable.rawas,R.drawable.surmai1};



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        rv=root.findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        arr=new ArrayList<>();
        adapter=new CustRecycleAdapter(arr);
        rv.setAdapter(adapter);

        fb=FirebaseFirestore.getInstance();
        colref=fb.collection("Freshfishes");

        colref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        int i=0;
                        for(DocumentSnapshot d:list){
                            CustRecycle e= new CustRecycle("Freshfishes",d.getId(),
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