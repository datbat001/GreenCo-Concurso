package com.itz.isc.greenco;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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
import com.itz.isc.greenco.fragmentos.Home;
import com.itz.isc.greenco.fragmentos.Promociones;
import com.itz.isc.greenco.fragmentos.Verificacion;
import com.itz.isc.greenco.fragmentos.favoritos;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar  toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    ImageButton btnExperiencias, btnBebidas, btnIngredientes,
            btnPanaderia,btnProductos,btnCongelados,btnCarnes,
            btnMariscos,btnshop,profile;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {


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

            drawerLayout = findViewById(R.id.drawer_mainp);

            navigationView = findViewById(R.id.nav_view);


            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                    R.string.Open,R.string.Close);


            drawerLayout.addDrawerListener(drawerToggle);

            drawerToggle.syncState();

            if(savedInstanceState ==null){
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContent,
                        new Home()).commit();
                navigationView.setCheckedItem(R.id.Opcion_Favoritos);
            }





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
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
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


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()){
//                case R.id.Opcion_Favoritos:
//                {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.mainContent,
//                            new favoritos()).commit();
//                    navigationView.bringToFront();
//                    break;
//                }
//                case R.id.Opcion_Beneficios:
//                {
//                    Toast.makeText(MainActivity.this,"Beneficios",Toast.LENGTH_SHORT).show();
//                    navigationView.bringToFront();
//                    break;
//                }
//                case R.id.Opcion_Promociones:
//                {
//                    Toast.makeText(MainActivity.this,"Promociones",Toast.LENGTH_SHORT).show();
//                    navigationView.bringToFront();
//                    break;
//                }
//                case R.id.Opcion_Verificaion:
//                {
//                    Toast.makeText(MainActivity.this,"Verificacion",Toast.LENGTH_SHORT).show();
//                    navigationView.bringToFront();
//                    break;
//                }
//                case R.id.Opcion_Salir:
//                {
//                    startActivity(new Intent(getApplicationContext(),login.class));
//                    Toast.makeText(MainActivity.this,"Cerraste sesion",Toast.LENGTH_SHORT).show();
//                    navigationView.bringToFront();
//                    break;
//                }
//
//            }
//            return false;
//        }
}