package com.itz.isc.greenco;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.itz.isc.greenco.ListProducts.Products;

import org.json.JSONException;
import org.json.JSONObject;

public class Verproducto extends AppCompatActivity {

    ImageView imageView;
    TextView nombreVer;
    TextView precioVer;
    TextView descVer;
    EditText editText;

    Button btnmas;
    Button btnmenos;
    Button btncarrito;
    Products products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verproducto);

        String id = getIntent().getStringExtra("Id");
        //Toast.makeText(this, "Id: " + id, Toast.LENGTH_SHORT).show();
        imageView = findViewById(R.id.img_prodver);
        nombreVer = findViewById(R.id.nomProdver);
        precioVer = findViewById(R.id.precProdver);
        descVer = findViewById(R.id.descProdver);
        btnmas = findViewById(R.id.buttonmas);
        btnmenos = findViewById(R.id.buttonmenos);
        btncarrito = findViewById(R.id.buttonshopver);
        editText = findViewById(R.id.editTextNumberver);

        extractProducts(id);
    }

    private void extractProducts(String Id) {
        String JSON_URL = "https://androidexd.000webhostapp.com/loginphp/showProduct.php?Id="+Id;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET,
                        JSON_URL,
                        null,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject item) {

                        products = null;
                        try {

                            nombreVer.setText(item.getString("nomProducto"));
                            descVer.setText(item.getString("descripcion"));
                            precioVer.setText(item.getString("precio"));
                            editText.setText(item.getString("existencia"));
                            cargarImagen(item.getString("imageName").toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Verproducto.this,"Something Went Wrong",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
        queue.add(jsonObjectRequest);

    }

    private void cargarImagen(String imageURL) {
        String URL = "https://androidexd.000webhostapp.com/media/logo2.png";
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Verproducto.this,"Something Went Wrong Loading the Image",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
    }
}
