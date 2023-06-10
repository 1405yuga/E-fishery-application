package com.example.e_fisheryapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.e_fisheryapplication.CustomerModel.AddressDetails;
import com.example.e_fisheryapplication.CustomerModel.FsModel;
import com.example.e_fisheryapplication.CustomerModel.OrderPlaced;
import com.example.e_fisheryapplication.CustomerModel.PersonalDetails;
import com.example.e_fisheryapplication.OrderRecyclerAdapter;
import com.example.e_fisheryapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderIntake extends Fragment {

    public OrderIntake() {
        // Required empty public constructor
    }
    public static OrderIntake newInstance(String param1, String param2) {
        OrderIntake fragment = new OrderIntake();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    RecyclerView rv;
    ArrayList<FsModel> arr;
    OrderRecyclerAdapter adapter;
    FirebaseFirestore fb;
    CollectionReference colref;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_order_intake, container, false);
        rv=root.findViewById(R.id.orderintakerv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        arr=new ArrayList<>();
        adapter=new OrderRecyclerAdapter(arr);
        rv.setAdapter(adapter);
        Toast.makeText(getContext(),"Swipe to remove order",Toast.LENGTH_SHORT).show();

        fb=FirebaseFirestore.getInstance();
        colref=fb.collection("Orders");

        colref.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d : list){
                            //id
                            String Ono=d.getId();

                            //Personal Details
                            Map<String,Object> map=d.getData();
                            String smob="",sfullname="",
                                    scategory="",sitem="",sqty="",stotal="",
                            sflatno="",sarea="",slandmark="",scity="",spin="";
                            for(Map.Entry<String,Object> entry:map.entrySet()){
                                if(entry.getKey().equals("pd")){
                                    Map<String ,Object> datas=(Map<String,Object>)entry.getValue();
                                    for(Map.Entry<String,Object> di:datas.entrySet()){
                                        switch(di.getKey()){
                                            case "mob":smob=di.getValue().toString();
                                                       break;
                                            case "fullname":sfullname=di.getValue().toString();
                                            break;
                                        }

                                    }
                                }
                                else if(entry.getKey().equals("op")){
                                    Map<String ,Object> datas=(Map<String,Object>)entry.getValue();
                                    for(Map.Entry<String,Object> di:datas.entrySet()){
                                        switch(di.getKey()){
                                            case "category":scategory=di.getValue().toString();
                                                break;
                                            case "item":sitem=di.getValue().toString();
                                                break;
                                            case "qty":sqty=di.getValue().toString();
                                                break;
                                            case "total":stotal=di.getValue().toString();
                                                break;
                                        }

                                    }
                                }
                                else if(entry.getKey().equals("ad")){
                                    Map<String ,Object> datas=(Map<String,Object>)entry.getValue();
                                    for(Map.Entry<String,Object> di:datas.entrySet()){
                                        switch(di.getKey()){
                                            case "faltno":sflatno=di.getValue().toString();
                                                break;
                                            case "area":sarea=di.getValue().toString();
                                                break;
                                            case "landmark":slandmark=di.getValue().toString();
                                                break;
                                            case "city":scity=di.getValue().toString();
                                                break;
                                            case "pin":spin=di.getValue().toString();
                                                break;

                                        }

                                    }
                                }
                            }
                            PersonalDetails pdd=new PersonalDetails(sfullname, Long.parseLong(smob));
                            OrderPlaced opp=new OrderPlaced(scategory,sitem,Integer.parseInt(sqty)
                                    ,Integer.parseInt(stotal));
                            AddressDetails add=new AddressDetails(sflatno,sarea,slandmark,scity,Integer.parseInt(spin));
                            FsModel fsm=new FsModel(Ono,pdd,add,opp);
                            arr.add(fsm);

                        }
                        adapter.notifyDataSetChanged();


                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getContext(),"Error!!",Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.delitem(viewHolder.getAdapterPosition());
                arr.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(rv);


        return root;

    }
}