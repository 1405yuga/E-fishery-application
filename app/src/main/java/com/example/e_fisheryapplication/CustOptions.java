package com.example.e_fisheryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.e_fisheryapplication.customer_fragments.cContact;
import com.example.e_fisheryapplication.customer_fragments.cEngine;
import com.example.e_fisheryapplication.customer_fragments.cFarming;
import com.example.e_fisheryapplication.customer_fragments.cFreshfishes;
import com.example.e_fisheryapplication.customer_fragments.cIcefac;
import com.example.e_fisheryapplication.customer_fragments.cOil;
import com.google.android.material.navigation.NavigationView;

public class CustOptions extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_options);

        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.custdrawer);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav=findViewById(R.id.custnav);
        getSupportActionBar().setTitle("Fresh fishes");
        getSupportFragmentManager().beginTransaction().replace(R.id.cframe,new cFreshfishes()).commit();
        nav.setCheckedItem(R.id.cfreshfishes);


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            Fragment fragment;
            String title;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(R.id.cfreshfishes==item.getItemId()){
                    fragment=new cFreshfishes();
                    title="Fresh fishes";
                } else if (R.id.coil == item.getItemId()) {
                    fragment=new cOil();
                    title="Oil";
                } else if (R.id.cnet == item.getItemId()) {
                    fragment=new cEngine();
                    title="Engine/Spare parts";
                }else if (R.id.cfishfarming== item.getItemId()) {
                    fragment=new cFarming();
                    title="Fishfaming seeds";
                }else if (item.getItemId() == R.id.cicefac) {
                    fragment=new cIcefac();
                    title="Ice factory";
                }else{
                    fragment=new cContact();
                    title="Contact";
                }

                getSupportActionBar().setTitle(title);
                getSupportFragmentManager().beginTransaction().replace(R.id.cframe,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }


        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(CustOptions.this,welcome.class);
        startActivity(i);
    }
}