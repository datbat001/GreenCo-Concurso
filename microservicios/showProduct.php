<?php

include 'conexion.php';

$Id= $_GET["Id"];

$sql="SELECT * FROM `Productos` WHERE idProducto = '$Id'";

$result = mysqli_query($conexion,$sql);

while($row = $result -> fetch_array()){
    $consulta[] = array_map('utf8_encode',$row);
}
echo json_encode($consulta);

$result -> close();

?>