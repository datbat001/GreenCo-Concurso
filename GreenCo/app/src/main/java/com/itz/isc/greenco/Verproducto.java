package com.itz.isc.greenco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Verproducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verproducto);

        String name = getIntent().getStringExtra("productName");
        String productDesc = getIntent().getStringExtra("productName");
        String productPrice = getIntent().getStringExtra("productName");
        String imageProduct = getIntent().getStringExtra("productName");

        TextView nameProd = findViewById(R.id.nomProd);
        TextView descProd = findViewById(R.id.descProd);
        TextView precioProd = findViewById(R.id.precProd);
        ImageView imageProd = findViewById(R.id.img_prod);

        nameProd.setText(name);
        descProd.setText(productDesc);
        precioProd.setText(productPrice);
        imageProd.setImageResource(Integer.parseInt(imageProduct));
    }
}