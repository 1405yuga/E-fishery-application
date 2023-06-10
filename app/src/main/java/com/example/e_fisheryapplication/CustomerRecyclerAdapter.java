package com.example.e_fisheryapplication;

import android.content.Context;
import android.content.Intent;
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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class CustomerRecyclerAdapter extends RecyclerView.Adapter<CustomerRecyclerAdapter.Cholder>{

    public static class Cholder extends RecyclerView.ViewHolder{

        public ImageView cimageView,cedit;
        public TextView citemname,cavail,cprice,availunit,priceunit;

        public Cholder(@NonNull View itemView) {
            super(itemView);

            cimageView=itemView.findViewById(R.id.cimageV);
            citemname=itemView.findViewById(R.id.cHOMETV);
            cavail=itemView.findViewById(R.id.cAVAIL);
            cprice=itemView.findViewById(R.id.cPRICE);
            cedit=itemView.findViewById(R.id.ceditBtn);
            availunit=itemView.findViewById(R.id.aunit);
            priceunit=itemView.findViewById(R.id.punit);

        }
    }

    public ArrayList<CustRecycle> arr;
    public Context context;
    public CustomerRecyclerAdapter(ArrayList<CustRecycle> arr,Context context) {
        this.arr = arr;
        this.context=context;

    }

    @NonNull
    @Override
    public Cholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item_list,parent,false);

        return new Cholder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull Cholder holder, int position) {
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
        holder.cedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogplus= com.orhanobut.dialogplus.DialogPlus.newDialog(holder.citemname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.layout_order_panel))
                        .setExpanded(true,800)
                        .create();

                //get update panel widgets
                View myview=dialogplus.getHolderView();
                TextView itemname=myview.findViewById(R.id.productname);
                EditText qty=myview.findViewById(R.id.qtyn);
                Button order=myview.findViewById(R.id.uOrder);
                TextView unit=myview.findViewById(R.id.unitt);

                //set values in panel widgets
                itemname.setText(cr.getItem());
                if(cr.getKg()==0){
                    unit.setText("kg");
                    qty.setHint("in kg");
                }
                else if(cr.getKg()==1){
                    unit.setText("pieces");
                    qty.setHint("in pieces");
                }
                else{
                    unit.setText("liters");
                    qty.setHint("in liters");
                }

                dialogplus.show();

                //clicked on order
                order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(qty.getText().toString().isEmpty()){
                            qty.setError("Enter quantity");

                        }
                        else if(Integer.parseInt(qty.getText().toString())>
                                Integer.parseInt(holder.cavail.getText().toString())){
                            qty.setError("Re-enter quantity");
                            Toast.makeText(holder.citemname.getContext(),"Required stock not available",
                                    Toast.LENGTH_LONG).show();

                        }
                        else{

                            dialogplus.dismiss();
                            Intent intent=new Intent(context,Orders.class);
                            intent.putExtra("option",cr.getOption());
                            intent.putExtra("item",cr.getItem());
                            intent.putExtra("avail",cr.getAvail());
                            intent.putExtra("price",cr.getPrice());
                            intent.putExtra("qty",Integer.parseInt(qty.getText().toString()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }

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
