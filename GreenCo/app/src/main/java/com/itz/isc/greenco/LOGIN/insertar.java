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
import com.itz.isc.greenco.R;

import java.util.HashMap;
import java.util.Map;

public class insertar extends AppCompatActivity {

    EditText nombre,apellidos,correo,fechaN,direccion,nickname,pass1,pass2;
    Button bttninsertar;
    String nom,ap,email,FN,dir,nickn,pass,Rpass;
    String url= "https://androidexd.000webhostapp.com/loginphp/insertar.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        correo = findViewById(R.id.correo);
        fechaN = findViewById(R.id.fechaR);
        direccion = findViewById(R.id.direccion);
        nickname = findViewById(R.id.nickname);
        pass1 = findViewById(R.id.password1);
        pass2 = findViewById(R.id.password2);

        bttninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Register(view);
            }
        });

    }


    public void Register(View view){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere..");

        if(nombre.getText().toString().equals("") || nombre.getText().toString().equals(" ")
                || nombre.getText().toString().equals(",")){
            Toast.makeText(this,"Ingresa tu nombre porfavor",Toast.LENGTH_SHORT).show();
        }

        else if(apellidos.getText().toString().equals("") || apellidos.getText().toString().equals(" ")
                || apellidos.getText().toString().equals(",")){
            Toast.makeText(this,"Ingresa tus apellidos porfavor",Toast.LENGTH_SHORT).show();
        }
        else if(correo.getText().toString().equals("")|| correo.getText().toString().equals(" ")){
            Toast.makeText(this,"Ingresa tu correo porfavor",Toast.LENGTH_SHORT).show();
        }
        else if(fechaN.getText().toString().equals("")|| fechaN.getText().toString().equals(" ")){
            Toast.makeText(this,"Ingresa tu fecha de nacimiento porfavor",Toast.LENGTH_SHORT).show();
        }
        else if(direccion.getText().toString().equals("")|| direccion.getText().toString().equals(" ")){
            Toast.makeText(this,"Ingresa tu direccion porfavor",Toast.LENGTH_SHORT).show();
        }
        else if(nickname.getText().toString().equals("")|| nickname.getText().toString().equals(" ")){
            Toast.makeText(this,"Ingresa tu nombre de usuario porfavor",Toast.LENGTH_SHORT).show();
        }
        else if(pass1.getText().toString().equals("")|| pass1.getText().toString().equals(" ")){
            Toast.makeText(this,"Ingresa la contraseña porfavor",Toast.LENGTH_SHORT).show();
        }
        else if(pass2.getText().toString().equals("")|| pass2.getText().toString().equals(" ")){
            Toast.makeText(this,"Vuelve a ingresar la contraseña porfavor",Toast.LENGTH_SHORT).show();
        }
        else if(!pass2.getText().toString().equals(pass1.getText().toString())){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.show();
            nom= nombre.getText().toString().trim();
            ap= apellidos.getText().toString().trim();
            email= correo.getText().toString().trim();
            FN= fechaN.getText().toString().trim();
            dir= direccion.getText().toString().trim();
            nickn= nickname.getText().toString().trim();
            pass= pass1.getText().toString().trim();
            Rpass= pass2.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Se registro correctamente")){
                        Toast.makeText(insertar.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                        Intent intent=new Intent(insertar.this,login.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(insertar.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(insertar.this, "No se pudo insertar", Toast.LENGTH_SHORT).show();
                    }


                    Toast.makeText(insertar.this, response, Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(insertar.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){

                @Override
                protected Map<String,String> getParams()throws AuthFailureError{
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("nomUsuario",nom);
                    params.put("apellidos",ap);
                    params.put("correo",dir);
                    params.put("fechaNacimiento",FN);
                    params.put("datosExtraDireccion",dir);
                    params.put("nickname",nickn);
                    params.put("password",pass);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(insertar.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void login(View view){
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }
}