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


                switch(item.getItemId()){
                    case R.id.cfreshfishes:
                        fragment=new cFreshfishes();
                        title="Fresh fishes";
                        break;

                    case R.id.coil:
                        fragment=new cOil();
                        title="Oil";
                        break;

                    case R.id.cnet:
                        fragment=new cEngine();
                        title="Engine/Spare parts";
                        break;

                    case R.id.cfishfarming:
                        fragment=new cFarming();
                        title="Fishfaming seeds";
                        break;

                    case R.id.cicefac:
                        fragment=new cIcefac();
                        title="Ice factory";
                        break;

                    case R.id.cContact:
                        fragment=new cContact();
                        title="Contact";
                        break;
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