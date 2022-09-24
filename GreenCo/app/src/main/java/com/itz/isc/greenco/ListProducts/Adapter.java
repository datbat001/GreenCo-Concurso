package com.itz.isc.greenco.ListProducts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.itz.isc.greenco.R;
import com.itz.isc.greenco.Verproducto;

import java.util.List;

public class Adapter extends RecyclerView.Adapter <Adapter.ViewHolder>
    implements View.OnClickListener
{


    private View.OnClickListener clickListener;
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
        view.setOnClickListener(view1 -> {

        });
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Bind the data

        holder.productName.setText(products.get(position).getNomProd());
        holder.productDesc.setText(products.get(position).getDescProd());
        holder.productPrice.setText(products.get(position).getPrecio());
        //holder.imageProduct.setText(products.get(position).getImageURL());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(inflater.getContext(), "Recycler click " + position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.clickListener = listener;
    }
    @Override
    public void onClick(View view) {
        if (clickListener != null){
            clickListener.onClick(view);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productName,productDesc,productPrice;
        ImageView imageProduct;
        protected String URL;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productDesc = itemView.findViewById(R.id.product_desc);
            productPrice = itemView.findViewById(R.id.product_price);
            imageProduct = itemView.findViewById(R.id.product_image);
            URL = "https://androidexd.000webhostapp.com/media/logo2.png";
            RequestQueue queue = Volley.newRequestQueue(itemView.getContext());
            ImageRequest imageRequest = new ImageRequest(URL, new Response.Listener<Bitmap>() {

                @Override
                public void onResponse(Bitmap response) {
                    imageProduct.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            queue.add(imageRequest);
        }
    }


}
