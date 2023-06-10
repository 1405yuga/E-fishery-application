package com.example.e_fisheryapplication;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_fisheryapplication.CustomerModel.AddressDetails;
import com.example.e_fisheryapplication.CustomerModel.CModel;
import com.example.e_fisheryapplication.CustomerModel.FsModel;
import com.example.e_fisheryapplication.CustomerModel.OrderPlaced;
import com.example.e_fisheryapplication.CustomerModel.PersonalDetails;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.Orderholder>{
    public void delitem(int adapterPosition) {
        FsModel cm=arr.get(adapterPosition);
        FirebaseFirestore fb=FirebaseFirestore.getInstance();
        CollectionReference colref=fb.collection("Orders");
        colref.document(cm.getOrderno()).delete();
    }

    public static class Orderholder extends RecyclerView.ViewHolder{
        TextView custname,orderno;
        ImageView infoBtn;
        public Orderholder(@NonNull View itemView) {
            super(itemView);
            custname=itemView.findViewById(R.id.custname);
            orderno=itemView.findViewById(R.id.OrderNo);
            infoBtn=itemView.findViewById(R.id.infoBtn);

        }
    }

    public ArrayList<FsModel> arr;

    public OrderRecyclerAdapter(ArrayList<FsModel> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public Orderholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_orderlist,parent,false);
        return new Orderholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Orderholder holder, int position) {
        FsModel cm=arr.get(position);
        String Ordernumber=cm.getOrderno();
        PersonalDetails pd=cm.getPersonal_details();
        AddressDetails ad=cm.getAddress_Details();
        OrderPlaced op=cm.getOrder_Placed();

        holder.custname.setText(pd.getFullname());
        holder.orderno.setText(Ordernumber);

        holder.infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogplus= com.orhanobut.dialogplus.DialogPlus.newDialog(holder.orderno.getContext())
                        .setContentHolder(new ViewHolder(R.layout.layout_infopanel))
                        .setExpanded(true,1500)
                        .create();
                View myview=dialogplus.getHolderView();
                //order
                TextView category=myview.findViewById(R.id.dcategory);
                TextView item=myview.findViewById(R.id.ditem);
                TextView qty=myview.findViewById(R.id.dqty);
                TextView total=myview.findViewById(R.id.dtotal);
                category.setText(op.getCategory());
                item.setText(op.getItem());
                qty.setText(Integer.toString(op.getQty()));
                total.setText(Integer.toString(op.getTotal()));

                //personal details
                TextView name=myview.findViewById(R.id.dname);
                TextView mobile=myview.findViewById(R.id.dmobile);
                name.setText(pd.getFullname());
                mobile.setText(Long.toString(pd.getMob()));

                //address details
                TextView faltno=myview.findViewById(R.id.dflatno);
                TextView area=myview.findViewById(R.id.darea);
                TextView landmark=myview.findViewById(R.id.dlandmark);
                TextView city=myview.findViewById(R.id.dcity);
                TextView pin=myview.findViewById(R.id.dpin);

                faltno.setText(ad.getFaltno());
                area.setText(ad.getArea());
                landmark.setText(ad.getLandmark());
                city.setText(ad.getCity());
                pin.setText(Integer.toString(ad.getPin()));

                dialogplus.show();
                Toast.makeText(holder.orderno.getContext(),"Click anywhwere out of the Option infomation panel to exit",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
