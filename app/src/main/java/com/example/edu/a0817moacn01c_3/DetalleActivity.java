package com.example.edu.a0817moacn01c_3;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity{
    private DetalleFragment detalleFragment;
    private Bundle unBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        recibirDatos();
        // detallesViewPager.setArguments(unBundle);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager_Detalles);
        FragmentManager fragmentManager = getSupportFragmentManager();
        AdaptadorDetallesViewPager adaptadorDetallesViewPager= new AdaptadorDetallesViewPager(fragmentManager);
        viewPager.setAdapter(adaptadorDetallesViewPager);

        Integer posicion = unBundle.getInt("position");

        viewPager.setCurrentItem(posicion);
    }

    public void recibirDatos(){
        Intent unIntent = getIntent();
        this.unBundle = unIntent.getExtras();
        // Creo que masa adelante solo pasamos un identificador y el fragment hace la consulta en BD y/o API
    }

}
