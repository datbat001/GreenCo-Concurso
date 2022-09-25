package com.itz.isc.greenco;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
<<<<<<< HEAD
import android.widget.TextView;
=======
import android.widget.Toast;
>>>>>>> ee994c9b4f89b86db3ae1f2b3dc1f928c0b79117

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.itz.isc.greenco.Carrito.carrito;
import com.itz.isc.greenco.LOGIN.login;
import com.itz.isc.greenco.fragmentos.Beneficios;
import com.itz.isc.greenco.fragmentos.Promociones;
import com.itz.isc.greenco.fragmentos.Verificacion;
import com.itz.isc.greenco.fragmentos.favoritos;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar  toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    ImageButton btnExperiencias, btnBebidas, btnIngredientes,
            btnPanaderia,btnProductos,btnCongelados,btnCarnes,
            btnMariscos,btnshop,profile;



<<<<<<< HEAD
    ImageButton btnExperiencias, btnBebidas, btnIngredientes,btnPanaderia,btnProductos,btnCongelados,btnCarnes,btnMariscos,btnshop;
    TextView txtXp,txtBebidas,txtIngre,txtPan,txtProd,txtCon,txtCar,txtMar;

=======
>>>>>>> ee994c9b4f89b86db3ae1f2b3dc1f928c0b79117

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

<<<<<<< HEAD
        txtXp = findViewById(R.id.txtXp);
        txtBebidas = findViewById(R.id.txtBed);
        txtIngre = findViewById(R.id.txtIng);
        txtPan = findViewById(R.id.txtpan);
        txtProd = findViewById(R.id.txtProd);
        txtCon = findViewById(R.id.txtCon);
        txtCar = findViewById(R.id.txtCar);
        txtMar = findViewById(R.id.txtMar);
        btnBebidas = findViewById(R.id.btnbeb);
        btnCarnes = findViewById(R.id.btncarnes);
        btnCongelados = findViewById(R.id.btncong);
        btnExperiencias = findViewById(R.id.btnxp);
        btnIngredientes = findViewById(R.id.btning);
        btnPanaderia = findViewById(R.id.btnpan);
        btnProductos = findViewById(R.id.btnprod);
        btnMariscos = findViewById(R.id.btnmar);
        btnshop = findViewById(R.id.btnShop);
=======
>>>>>>> ee994c9b4f89b86db3ae1f2b3dc1f928c0b79117

            btnBebidas = findViewById(R.id.btnbeb);
            btnCarnes = findViewById(R.id.btncarnes);
            btnCongelados = findViewById(R.id.btncong);
            btnExperiencias = findViewById(R.id.btnxp);
            btnIngredientes = findViewById(R.id.btning);
            btnPanaderia = findViewById(R.id.btnpan);
            btnProductos = findViewById(R.id.btnprod);
            btnMariscos = findViewById(R.id.btnmar);
            btnshop = findViewById(R.id.btnShop);
            //---------------navigation----------------------
            profile = findViewById(R.id.imageHeader);

            toolbar = findViewById(R.id.toolbar);

            drawerLayout = findViewById(R.id.drawer_main);

            navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setItemIconTintList(null);

            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                    R.string.Open,R.string.Close);


            drawerLayout.addDrawerListener(drawerToggle);

            drawerToggle.syncState();

            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new favoritos()).commit();
                navigationView.setCheckedItem(R.id.Opcion_Favoritos);
            }
<<<<<<< HEAD
        });
        btnExperiencias.setOnClickListener(view -> {
            String str = txtXp.getText().toString();
            Intent intent = new Intent(getApplicationContext(),showProductos.class);
            //findViewById(R.id.categoria).set;
            startActivity(intent);
        });
        btnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = txtBebidas.getText().toString();
                Intent intent = new Intent(getApplicationContext(),showProductos.class);
                startActivity(intent);
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

=======


            //---------------Botones del main----------------------
            btnshop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shop(view);
                }
            });
            btnExperiencias.setOnClickListener(view -> {
                String str = null;
                Intent intent = new Intent(getApplicationContext(), showProductos.class);
                intent.putExtra("Experiencias", str);
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

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        try {

        int id = item.getItemId();

        if(id == R.id.Opcion_Favoritos){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new favoritos()).commit();

        }else if (id == R.id.Opcion_Beneficios){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Beneficios()).commit();
        }
        else if (id == R.id.Opcion_Promociones){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Promociones()).commit();
        }
        else if (id == R.id.Opcion_Verificaion){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Verificacion()).commit();
        }
        else if (id == R.id.Opcion_Salir){
            startActivity(new Intent(getApplicationContext(),login.class));
            Toast.makeText(this,"Cerraste sesion",Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }



    /*public void experiencias(View view){
            String str= "";
            Intent intent = new Intent();

            startActivity(new Intent(getApplicationContext(), showProductos.class));
            intent.putExtra("Experiencias",str);
        }*/
>>>>>>> ee994c9b4f89b86db3ae1f2b3dc1f928c0b79117
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