<?php

include 'conexion.php';
$nomUsuario= $_POST["nomUsuario"];
$apellidos= $_POST["apellidos"];
$correo= $_POST["correo"];
$fechaNacimiento= $_POST["fechaNacimiento"];
$datosExtraDireccion= $_POST["datosExtraDireccion"];
$nickname= $_POST["nickname"];
$password= $_POST["password"];

$Ssql = "select nickname from usuarios where nickname='$nickname'";
$sql = "insert into usuarios(nomUsuario,apellidos,correo,fechaNacimiento,datosExtraDireccion,nickname,pass) values('$nomUsuario','$apellidos','$correo','$fechaNacimiento','$datosExtraDireccion','$nickname','$password')";

$resultados = mysqli_query($conexion,$Ssql);
$consulta = mysqli_fetch_array($resultados);

    
    if(empty($consulta)){
            try{
                mysqli_query($conexion,$sql);
                
            }catch(Exception $e){
                echo 'Excepción capturada: ',  $e->getMessage();
                echo "No se pudo insertar";
            }
            echo "Se registro correctamente";
            
    }else{
        echo "Ya existe un usuario con ese nombre";
    }
       
        
    


?>