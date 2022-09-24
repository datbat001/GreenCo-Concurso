package com.itz.isc.greenco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.itz.isc.greenco.Carrito.carrito;

public class MainActivity extends AppCompatActivity {

    ImageButton btnExperiencias, btnBebidas, btnIngredientes,btnPanaderia,btnProductos,btnCongelados,btnCarnes,btnMariscos,btnshop;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBebidas = findViewById(R.id.btnbeb);
        btnCarnes = findViewById(R.id.btncarnes);
        btnCongelados = findViewById(R.id.btncong);
        btnExperiencias = findViewById(R.id.btnxp);
        btnIngredientes = findViewById(R.id.btning);
        btnPanaderia = findViewById(R.id.btnpan);
        btnProductos = findViewById(R.id.btnprod);
        btnMariscos = findViewById(R.id.btnmar);
        btnshop = findViewById(R.id.btnShop);

        btnshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shop(view);
            }
        });
        btnExperiencias.setOnClickListener(view -> {
            String str = null;
            Intent intent = new Intent(getApplicationContext(),showProductos.class);
            intent.putExtra("Experiencias",str);
            startActivity(intent);
        });
        btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bebidas(view);
            }
        });
        btnIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ingredientes(view);
            }
        });
        btnPanaderia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Panaderia(view);
            }
        });
        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Productos(view);
            }
        });
        btnCongelados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Congelados(view);
            }
        });
        btnCarnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carnes(view);
            }
        });
        btnMariscos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mariscos(view);
            }
        });

    }
    /*public void experiencias(View view){
        String str= "";
        Intent intent = new Intent();

        startActivity(new Intent(getApplicationContext(), showProductos.class));
        intent.putExtra("Experiencias",str);
    }*/
    public void Bebidas(View view){
        startActivity(new Intent(getApplicationContext(), showProductos.class));
    }public void Ingredientes(View view){
        startActivity(new Intent(getApplicationContext(), showProductos.class));
    }public void Panaderia(View view){
        startActivity(new Intent(getApplicationContext(), showProductos.class));
    }public void Productos(View view){
        startActivity(new Intent(getApplicationContext(), showProductos.class));
    }public void Congelados(View view){
        startActivity(new Intent(getApplicationContext(), showProductos.class));
    }public void Carnes(View view){
        startActivity(new Intent(getApplicationContext(), showProductos.class));
    }public void Mariscos(View view){
        startActivity(new Intent(getApplicationContext(), showProductos.class));
    }
    public void shop(View view){
        startActivity(new Intent(getApplicationContext(), carrito.class));
    }
}