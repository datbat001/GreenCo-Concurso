package com.itz.isc.greenco.sqlite;

import java.sql.Date;

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
        String Id_Estado = "Id_Estado";
        String NomMunicipio = "NomMunicipio";

    }
    interface Asentamientos{
        String IdAsentamiento = "IdAsentamientos";
        String Id_Municipio = "Id_Municipio";
        String NomAsentamiento = "NomAsentamiento";

    }
    interface Colonias{
        String IdColonia = "IdColonia";
        String Id_Asentamiento = "Id_Asentamiento";
        String NomColonia = "NomColonia";
    }
    interface Calles{
        String IdCalle = "IdCalle";
        String Id_Colonia = "Id_Colonia";
        String NomCalle = "NomCalle";
    }
   interface Usuarios{
        String IdUsuario = "IdUsuario";
        String Nickname = "Nickname";
        String NomUsuario = "NomUsuario";
        String ApPaterno = "ApPaterno";
        String ApMaterno = "ApMaterno";
        String FechaNac = "FechaNac";
        String RutaFoto = "RutaFotoUsuario";
        String Id_calle = "Id_calle";
        String num = "num";
        String DE = "DatosExtra";
   }
   interface RolesDeUsuarios{
        String IdRol = "IdRol";
        String RolUsuario = "RolUsuario";
   }
   interface FotosProds{
        String Id_Producto = "Id_Producto";
        String RutaFotoProd = "RutaFotoProd";
        String FechaSubida = "FechaSubida";
   }
}
