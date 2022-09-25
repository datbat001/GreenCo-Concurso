<?php

include 'conexion.php';
$nomUsuario= $_POST["nomUsuario"];
$apellidos= $_POST["apellidos"];
$correo= $_POST["correo"];
$fechaNacimiento= $_POST["fechaNacimiento"];
$datosExtraDireccion= $_POST["datosExtraDireccion"];
$nickname= $_POST["nickname"];
$password= $_POST["password"];

$Ssql = "select nickname from usuarios where nickname='".$nickname."'";
$sql = "insert into usuarios(nombre,apellidos,correo,fechaNacimiento,datosExtraDireccion,nickname,password) values('".$nomUsuario."','".$apellidos."','".$correo."','".$fechaNacimiento."','".$datosExtraDireccion."','".$nickname."','".$password."')";

$resultados = mysqli_query($conexion,$Ssql);

while($consulta = mysqli_fetch_array($resultados))
{
    foreach($consulta as $dato)
    {
        
        if($dato == $nickname)
        {

        echo "Ya existe un usuario con ese nombre";

        }else{

        
            $result = mysqli_query($conexion,$sql);
            
            
            if($result){
                echo "Datos insertados";
            }
            else{
                echo "No se pudieron insertar";
            }
            
        }
        
    }
}

?>