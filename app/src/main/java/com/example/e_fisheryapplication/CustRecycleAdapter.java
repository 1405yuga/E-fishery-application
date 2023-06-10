package com.example.e_fisheryapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class CustRecycleAdapter extends RecyclerView.Adapter<CustRecycleAdapter.CustRecylerHolder> {

    public static class CustRecylerHolder extends RecyclerView.ViewHolder{
        public ImageView cimageView,cedit;
        public TextView citemname,cavail,cprice,availunit,priceunit;


        public CustRecylerHolder(@NonNull View itemView) {
            super(itemView);
            cimageView=itemView.findViewById(R.id.imageV);
            citemname=itemView.findViewById(R.id.HOMETV);
            cavail=itemView.findViewById(R.id.AVAIL);
            cprice=itemView.findViewById(R.id.PRICE);
            cedit=itemView.findViewById(R.id.editBtn);
            availunit=itemView.findViewById(R.id.availbleunit);
            priceunit=itemView.findViewById(R.id.priceunitt);
        }
    }

    public ArrayList<CustRecycle> arr;

    public CustRecycleAdapter(ArrayList<CustRecycle> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public CustRecylerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        return new CustRecylerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustRecylerHolder holder, final int position) {
        CustRecycle cr=arr.get(position);
        holder.cimageView.setImageResource(cr.getImage());
        holder.citemname.setText(cr.getItem());

        holder.cavail.setText(Integer.toString(cr.getAvail()));
        holder.cprice.setText(Integer.toString(cr.getPrice()));
        if(cr.getKg()==0){
            holder.availunit.setText("kg");
            holder.priceunit.setText("Rs/kg");
        }
        else if(cr.getKg()==1){
            holder.availunit.setText("pieces");
            holder.priceunit.setText("Rs/piece");
        }
        else{
            holder.availunit.setText("liters");
            holder.priceunit.setText("Rs/liter");
        }

        //edit button clicked
        holder.cedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogplus=DialogPlus.newDialog(holder.citemname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.layout_update))
                        .setExpanded(true,1500)
                        .create();
                //get update panel widgets
                View myview=dialogplus.getHolderView();
                TextView itemname=myview.findViewById(R.id.productname);
                EditText avail=myview.findViewById(R.id.upavail);
                EditText price=myview.findViewById(R.id.upprice);
                Button update=myview.findViewById(R.id.uButon);
                TextView unita=myview.findViewById(R.id.unitavail);
                TextView unitp=myview.findViewById(R.id.unitprice);

                //set values in panel widgets
                itemname.setText(cr.getItem());
                String unit="kg";
                if(cr.getKg()==0){
                    unit="kg";
                }
                else if(cr.getKg()==1){
                    unit="pieces";
                }
                else{
                    unit="liters";
                }

                //set values in panel widgets
                itemname.setText(cr.getItem());
                unita.setText(unit);
                unitp.setText("Rs/"+unit);
                avail.setText(Integer.toString(cr.getAvail()));
                avail.setHint(unit);
                price.setHint("Rs/"+unit);
                price.setText(Integer.toString(cr.getPrice()));

                dialogplus.show();

                //update values in Firestore
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> obj=new HashMap<>();
                        obj.put("avail",Integer.parseInt(avail.getText().toString()));
                        obj.put("price",Integer.parseInt(price.getText().toString()));


                        //updating
                        FirebaseFirestore fb=FirebaseFirestore.getInstance();
                        DocumentReference db=fb.collection(cr.getOption()).document(cr.getItem());
                        db.set(obj)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogplus.dismiss();
                                        holder.cavail.setText(avail.getText().toString());
                                        holder.cprice.setText(price.getText().toString());
                                        Toast.makeText(holder.citemname.getContext(),"Updated Successfully!",
                                                Toast.LENGTH_LONG).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogplus.dismiss();
                                        Toast.makeText(holder.citemname.getContext(),"Updation failed!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                });


            }
        });



    }

    @Override
    public int getItemCount() {
        return arr.size();
    }





}
