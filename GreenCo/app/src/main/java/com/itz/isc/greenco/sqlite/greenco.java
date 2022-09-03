package com.itz.isc.greenco.sqlite;

/**
 * Clase que establece los nombres a usar en la base de datos
 * **/
public class greenco {

    interface Categorias{
        String IdCategoria = "IdCategoria";
        String NomCategoria = "NomCategoria";
    }
    interface Productos {
        String IdProducto = "IdProducto";
        String NomProducto = "NomProducto";
        String Precio = "Precio";
        String Descripcion = "Descripcion";
        String Existencia = "Existencia";
    }
    interface Estados {
        String IdEstado = "IdEstado";
        String NomEstadp = "NomEstado";

    }
    interface Municipios{
        String IdMunicipio = "IdMunicipio";
    }
    interface ColumnasCabeceraPedido {
        String ID = "id";
        String FECHA = "fecha";
        String ID_CLIENTE = "id_cliente";
        String ID_FORMA_PAGO = "id_forma_pago";
    }
}
