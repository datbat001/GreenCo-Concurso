package com.itz.isc.greenco.LOGIN;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itz.isc.greenco.MainActivity;
import com.itz.isc.greenco.R;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    EditText usuario, password;
    Button btnregistrar;
    Button btnlogin;
    String user, pass;
    String url= "https://androidexd.000webhostapp.com/loginphp/RegistrarLogin.php";
    //'https://androidexd.000webhostapp.com/loginphp/showProducts.php'
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginr);

        usuario = findViewById(R.id.CorreoNick);
        password = findViewById(R.id.passwordlogin);
        btnregistrar=findViewById((R.id.button4));
        btnlogin = findViewById(R.id.ingresaButton);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login(view);

            }
        });

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro(view);
            }
        });

    }

    public void Login (View view){


        if(usuario.getText().toString().equals("") || usuario.getText().toString().equals(" ")){
            Toast.makeText(this,"Ingresa tu correo o tu usuario",Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().equals("") || password.getText().toString().equals(" ")){
            Toast.makeText(this,"Ingresa tu correo o tu usuario",Toast.LENGTH_SHORT).show();
        }
        else{

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Por favor espere...");

            progressDialog.show();

            user = usuario.getText().toString().trim();
            pass = password.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if (response.equalsIgnoreCase("Ingreso exitosamente")) {
                        usuario.setText("");
                        password.setText("");

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(login.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(login.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String,String> getParams()throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();

                    params.put("usuario",user);
                    params.put("password",pass);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(login.this);
            requestQueue.add(request);

        }
    }
    public void registro(View view) {
        startActivity(new Intent(getApplicationContext(), insertar.class));
        finish();
    }

}