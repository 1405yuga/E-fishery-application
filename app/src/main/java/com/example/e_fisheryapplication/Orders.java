package com.example.e_fisheryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_fisheryapplication.CustomerModel.AddressDetails;
import com.example.e_fisheryapplication.CustomerModel.CModel;
import com.example.e_fisheryapplication.CustomerModel.OrderPlaced;
import com.example.e_fisheryapplication.CustomerModel.PersonalDetails;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Orders extends AppCompatActivity {


    TextView Category,Item,Quantity,Total,msg;
    Button place,cancel;
    EditText Fullname,Mob,PIN,Flatno,Street,Landmark,City;
    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        getSupportActionBar().setTitle("Place Order");
        Category=findViewById(R.id.coption);
        Item=findViewById(R.id.citem);
        Quantity=findViewById(R.id.cquantity);
        Total=findViewById(R.id.ctotal);
        place=findViewById(R.id.placeorderBtn);
        cancel=findViewById(R.id.cancelorder);
        msg=findViewById(R.id.text12);

        Fullname=findViewById(R.id.cname);
        Mob=findViewById(R.id.cmob);
        PIN=findViewById(R.id.cpincode);
        Flatno=findViewById(R.id.cflatno);
        Street=findViewById(R.id.cstreet);
        Landmark=findViewById(R.id.clandmark);
        City=findViewById(R.id.ctown);

        Intent i=getIntent();
        int price=i.getIntExtra("price",0);
        int avail=i.getIntExtra("avail",0);
        int quant=i.getIntExtra("qty",0);
        total=quant*price;
        Category.setText(i.getStringExtra("option"));
        Item.setText(i.getStringExtra("item"));
        Quantity.setText(Integer.toString(quant));
        Total.setText(Integer.toString(total));
        msg.setText("*Only cash on delivery mode available");

        //cancel btn
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Orders.this);
                builder.setTitle("Cancel Order")
                        .setMessage("Are you sure you want to cancel order?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Orders.this,"Order cancelled successfully!",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(Orders.this,CustOptions.class);
                                startActivity(i);
                            }
                        });
                AlertDialog box=builder.create();
                box.show();
            }
        });

        //place order button
        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Fullname.getText().toString().isEmpty()){
                    Fullname.setError("Enter Fullname");
                }
                if(Mob.getText().toString().isEmpty()){
                    Mob.setError("Enter mobile no.");
                }
                if(PIN.getText().toString().isEmpty()){
                    PIN.setError("Enter pincode");
                }
                if(Flatno.getText().toString().isEmpty()){
                    Flatno.setError("Enter Flat no.");
                }
                if(Street.getText().toString().isEmpty()){
                    Street.setError("Enter street");
                }
                if(Landmark.getText().toString().isEmpty()){
                    Landmark.setError("Enter landmark");
                }
                if(City.getText().toString().isEmpty()){
                    City.setError("Enter City");
                }
                else{
                    PersonalDetails Personal_Details=new PersonalDetails(Fullname.getText().toString(),
                            Long.parseLong(Mob.getText().toString()));
                    AddressDetails Address_Details=new AddressDetails(Flatno.getText().toString(),
                            Street.getText().toString(),Landmark.getText().toString(),City.getText().toString(),
                            Integer.parseInt(PIN.getText().toString()));
                    OrderPlaced Order_Placed=new OrderPlaced(Category.getText().toString(),
                    Item.getText().toString(),
                    Integer.parseInt(Quantity.getText().toString()),
                    Integer.parseInt(Total.getText().toString()));

                    CModel cm=new CModel(Personal_Details,Address_Details,Order_Placed);

                    FirebaseFirestore fb=FirebaseFirestore.getInstance();
                    CollectionReference colref=fb.collection("Orders");
                    colref.add(cm)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(Orders.this,"Order placed successfully!",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(Orders.this,CustOptions.class);
                                    startActivity(i);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Orders.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                    DocumentReference docref=fb.collection(i.getStringExtra("option"))
                            .document(i.getStringExtra("item"));
                    int a=avail-quant;
                    Map<String,Object> obj=new HashMap<>();
                    obj.put("avail",a);
                    obj.put("price",price);
                    docref.set(obj);



                }


            }
        });


    }

}