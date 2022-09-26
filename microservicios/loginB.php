<?php

include 'conexion.php';

$usuario= $_POST["usuario"];
$password= $_POST["password"];

$sql = "select * from usuarios where correo='$usuario' and pass = '$password'";
$Ssql = "select * from usuarios where nickname='$usuario' and pass = '$password'";

$firstResult = mysqli_query($conexion,$sql);
$secondResult = mysqli_query($conexion,$Ssql);

$consulta = mysqli_fetch_array($firstResult);
$secondConsult = mysqli_fetch_array($secondResult);




if(empty($secondConsult) || empty($consulta)){
    
    echo "No se encontro ningun usuario";
            
}else{
    echo "Ingreso exitosamente";
}
?>