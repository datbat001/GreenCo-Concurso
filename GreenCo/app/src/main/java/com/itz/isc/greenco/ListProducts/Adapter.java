package com.itz.isc.greenco.ListProducts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itz.isc.greenco.R;
import com.itz.isc.greenco.Verproducto;

import java.util.List;

public class Adapter extends RecyclerView.Adapter <Adapter.ViewHolder>{


    LayoutInflater inflater;
    List<Products> products;

    public Adapter(Context ctx, List<Products> products){
        this.inflater = LayoutInflater.from(ctx);
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Bind the data
        holder.productName.setText(products.get(position).getNomProd());
        holder.productDesc.setText(products.get(position).getDescProd());
        holder.productPrice.setText(products.get(position).getPrecio());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productName,productDesc,productPrice;
        Button carrito;
        ImageView imageProduct;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productDesc = itemView.findViewById(R.id.product_desc);
            productPrice = itemView.findViewById(R.id.product_price);
            carrito = itemView.findViewById(R.id.btnShop);
            //imageProduct = itemView.findViewById(R.id.btnShop);
        }
    }
}
