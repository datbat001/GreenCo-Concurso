package com.itz.isc.greenco.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.itz.isc.greenco.R;
import com.itz.isc.greenco.models.Carrito;

import java.util.List;

public class carritoAdapter extends RecyclerView.Adapter<carritoAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Carrito> carrito;
    private carritoAdapter.onItemClickListener mListener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(carritoAdapter.onItemClickListener listener){
        mListener = listener;
    }

    public carritoAdapter(Context ctx, List<Carrito> carrito){
        this.inflater = LayoutInflater.from(ctx);
        this.carrito = carrito;
    }

    @NonNull
    @Override
    public carritoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_carrito,parent,false);

        return new carritoAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull carritoAdapter.ViewHolder holder, int position) {
        //Bind the data

        holder.productName.setText(carrito.get(position).getNomProd());
        holder.productDesc.setText(carrito.get(position).getDescProd());
        holder.productPrice.setText(carrito.get(position).getPrecio());
        //holder.imageProduct.setText(products.get(position).getImageURL());

    }

    @Override
    public int getItemCount() {
        return carrito.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView productName,productDesc,productPrice;
        ImageView imageProduct;
        protected String URL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.nombreCarrito);
            productDesc = itemView.findViewById(R.id.desCarrito);
            productPrice = itemView.findViewById(R.id.precProdveritem);
            imageProduct = itemView.findViewById(R.id.imgCarrito);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
