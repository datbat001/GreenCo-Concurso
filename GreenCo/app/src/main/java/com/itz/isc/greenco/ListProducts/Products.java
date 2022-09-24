package com.itz.isc.greenco.ListProducts;

public class Products {

    private String nomProd;
    private String descProd;
    private String precio;
    private String imageURL;

    public Products(){}

    public Products(String nomProd,String descProd,String precio,String imageURL){
        this.nomProd = nomProd;
        this.descProd = descProd;
        this.precio = precio;
        this.imageURL = imageURL;
    }

    public String getImageURL() { return imageURL; }

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
