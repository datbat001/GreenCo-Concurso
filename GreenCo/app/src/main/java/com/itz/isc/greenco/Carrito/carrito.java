package com.itz.isc.greenco.Carrito;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.itz.isc.greenco.R;
import com.itz.isc.greenco.adapters.carritoAdapter;
import com.itz.isc.greenco.models.Carrito;

import java.util.List;

public class carrito extends AppCompatActivity implements carritoAdapter.onItemClickListener{

    RecyclerView carritoRecycler;
    List<Carrito> carrito;
    carritoAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        carritoRecycler = findViewById(R.id.idCarritoRecycler);

    }



    @Override
    public void onItemClick(int position) {
        
    }
}