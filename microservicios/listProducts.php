<?php

include 'conexion.php';

$sql="SELECT * FROM Productos ";
$result = mysqli_query($conexion, $sql);

while($row = $result-> fetch_array()){
    $consulta[] = array_map('utf8_encode',$row);
}
echo json_encode($consulta);
//print_r($consulta);
$result -> close();
?>