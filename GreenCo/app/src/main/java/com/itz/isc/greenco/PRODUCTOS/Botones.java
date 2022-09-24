package com.itz.isc.greenco.PRODUCTOS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.itz.isc.greenco.Carrito.carrito;
import com.itz.isc.greenco.LOGIN.insertar;
import com.itz.isc.greenco.MainActivity;
import com.itz.isc.greenco.R;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Botones extends AppCompatActivity {
    ImageButton home,carritoP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        home = findViewById(R.id.btnHome);
        carritoP=findViewById(R.id.carritoProductos);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home(view);
            }
        });
        carritoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Carrito(view);
            }
        });

    }
    public void Home(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
    public void Carrito(View view) {
        startActivity(new Intent(getApplicationContext(), carrito.class));
        finish();
    }
}