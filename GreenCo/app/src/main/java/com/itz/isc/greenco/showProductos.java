package com.itz.isc.greenco;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.itz.isc.greenco.Carrito.carrito;
import com.itz.isc.greenco.ListProducts.Adapter;
import com.itz.isc.greenco.ListProducts.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class showProductos extends AppCompatActivity  implements Adapter.onItemClickListener {

    RecyclerView recyclerView=null;
    List<Products> productos;
    private static String JSON_URL = "https://androidexd.000webhostapp.com/loginphp/listProducts.php";
    Adapter adapter;
    ImageButton btnHome,btnShop;
    TextView Categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        Categoria = findViewById(R.id.categoria);
        recyclerView = findViewById(R.id.products_list);
        btnHome = findViewById(R.id.btnHome);
        btnShop = findViewById(R.id.carritoProductos);

        Intent intent = getIntent();


        productos = new ArrayList<>();

        extractProducts();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home(view);
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shop(view);
            }
        });

    }

    public void shop(View view){
        startActivity(new Intent(getApplicationContext(), carrito.class));
    }

    public void home(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    private void extractProducts() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject productObject = response.getJSONObject(i);

                        Products products = new Products();
                        products.setId(productObject.getString("idProducto").toString());
                        products.setNomProd(productObject.getString("nomProducto").toString());
                        products.setDescProd(productObject.getString("descripcion").toString());
                        products.setPrecio(productObject.getString("precio").toString());
                        products.setExistencia(productObject.getString("existencia").toString());

                        productos.add(products);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),productos);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(showProductos.this);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse: "+ error.getMessage());

            }
        });

        queue.add(jsonArrayRequest);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,Verproducto.class);
        Products clickedProduct = productos.get(position);
        intent.putExtra("Id", clickedProduct.getId().toString());


        startActivity(intent);
    }
}