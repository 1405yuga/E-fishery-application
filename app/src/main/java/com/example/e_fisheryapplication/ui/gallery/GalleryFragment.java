package com.example.e_fisheryapplication.ui.gallery;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    RecyclerView rv;

    ArrayList<CustRecycle> arr;
    CustRecycleAdapter adapter;
    FirebaseFirestore fb;
    CollectionReference colref;

    Integer images[]={R.drawable.diesel,R.drawable.grease};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        rv=root.findViewById(R.id.oilrv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        arr=new ArrayList<>();
        adapter=new CustRecycleAdapter(arr);
        rv.setAdapter(adapter);

        fb=FirebaseFirestore.getInstance();
        colref=fb.collection("Oil");

        colref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        int i=0;
                        for(DocumentSnapshot d:list){
                            int kg=0;
                            if(i==0){
                                kg=2;
                            }
                            CustRecycle e= new CustRecycle("Oil",d.getId(),
                                    Integer.parseInt(d.getData().get("avail").toString()),
                                    Integer.parseInt(d.getData().get("price").toString()),
                                    images[i],kg);
                            i++;
                            arr.add(e);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

        return root;
    }
}