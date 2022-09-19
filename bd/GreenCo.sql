Create database GreenCo_gps;

use GreenCo_gps;

create table Categorias(
   idCategoria int not null auto_increment,
   nomCategoria char (64) not null unique,
   primary key(idCategoria)
);


CREATE TABLE Productos (
	idProducto INT NOT NULL AUTO_INCREMENT,
	nomProd char(64),
	-- precio float, ESTE VALOR TIENE QUE SER CALCULADO, POR QUE ES PROVISTO POR MULTIPLES PROVEEDORES ADEMÁS DEBERÁS DE MOSTRAR UN RANGO DE PRECIOS
	descripcion varchar(512),	
	existencia int comment "ES LA SUMA DE TODOS LOS PRODUCTOS",
	PRIMARY KEY (IdProducto)
);


create table Tags(
   idTag int not null auto_increment,
   idCategoria int not null,
   nomTag char(16) not null unique,
   primary key (idTag),
   foreign key (idCategoria) references Categorias (idCategoria)
);

create table TagsProds(
   idProducto int not null ,
   idTag int not null,
   primary key(idProducto, idTag),
   foreign key (idProducto) references Productos (idProducto),
   foreign key (idTag) references Tags (idTag)
);


CREATE TABLE Estados (
    idEstado INT NOT NULL AUTO_INCREMENT,
    nomEstado CHAR(64) NOT NULL unique,
    PRIMARY KEY (idEstado)
);

create table Municipios(
   idMunicipio int not null auto_increment,
   idEstado int not null,
   nomMunicipio char(128),
   primary key(idMunicipio, idEstado),
   foreign key (idEstado) references Estados (idEstado)   
);

create table Asentamientos(
   idAsentamiento int not null auto_increment,
   idMunicipio int not null,
   nomAsentamiento char(128),
   primary key(idAsentamiento, idMunicipio),
   foreign key (idMunicipio) references Municipios (idMunicipio)   
);

CREATE TABLE Colonias (
    idColonia INT NOT NULL AUTO_INCREMENT,
    idAsentamiento INT NOT NULL,
    nomColonia CHAR(128) NOT NULL,
    PRIMARY KEY (idColonia , idAsentamiento),
    FOREIGN KEY (idAsentamiento) REFERENCES Asentamientos (idAsentamiento)
);

CREATE TABLE Calles (
    idCalle INT NOT NULL AUTO_INCREMENT,
    idColonia int,
    nomCalle CHAR(128) NOT NULL,
    PRIMARY KEY (idCalle, idColonia),
    foreign key (idColonia) references Colonias (idColonia)
);

/*
Ejemplo Json:

{
"edificio": "2",
"piso": "2",
"lote": "3",
"Entre calle 1": "tlacoapan",
"Entre calle 2": "temoaya",
"tipoDeVivienda": {
"condominio": "False",
"vivienda": "True",
"residencial": "False",
"fraccionamiento": "False"
}
}

*/

CREATE TABLE Usuarios(
    idUsuario INT NOT NULL AUTO_INCREMENT,
    nickname char(64) not null unique comment 'ES EL NOMBRE DE USUARIO PARA INGRESAR P.EJ. GOLOSA69',
    nomUsuario char(64) not null,
    apellidos varchar(128),
    fechaNacimiento DATE,
    rutaFotoUsuario VARCHAR(2048),
    idCalle int,
    num varchar(16),
    datosExtraDireccion json,	
    PRIMARY KEY (idUsuario),
    FOREIGN KEY (idCalle)
        REFERENCES Calles (idCalle)
);

CREATE TABLE RolesDeUsuarios(
	idRol int auto_increment,
    rolUsuario char(12) comment "usuario, vendMin, tienda, admin, superadmin",    
	PRIMARY KEY (idRol)
);

create table UsuariosRoles(
   idRol int not null,
   idUsuario int not null,
   primary key (idRol, idUsuario),
   foreign key (idRol) references RolesDeUsuarios (idRol),
   foreign key (idUsuario) references Usuarios (idUsuario)
);

create table FormasDeContacto(
   idContacto int, 
   formaDeContacto char(128),
   TipoContacto char(16) comment "Tel, Cel, Mail, Twit, Face...",
   primary key (idContacto, formaDeContacto),
   foreign key (idContacto) references Usuarios (idUsuario)
);

create table FotosProds(
   idProducto int not null,
   rutaFotoProducto varchar(2048),
   fechaSubida datetime,
   validada boolean,
   publica boolean comment "SI NO ES PÚBLICA ENTONCES NECESITA UN DUEÑO",
   idPropietario int comment "PERMITE SUBIR FOTOS PERSONALIZADA PARA UN PRODUCTO QUE ESTÁ DADO DE ALTA Y SÓLO APARECERÁ EN LA TIENDA DEL USUARIO",
   foreign key (idProducto) references Productos(idProducto),
   foreign key (idPropietario) references Usuarios (idUsuario)
);

create table DatosFacturacion(   
   idDatosFacturacion int auto_increment,
   idCliente int,
   RFC char (13),
   razonSocial varchar (512),
   datosDelCliente bool comment "Utiliza los datos de direccion del cliente",
   idCalle int,
   num varchar(16),
   datosExtraDireccion json,	   
   primary key (idDatosFacturacion),
   key(idCliente,RFC),
   foreign key (idCliente) references Usuarios (idUsuario)
);

create table TagsUsuarios(
   idUsuario int not null ,
   idTag int not null,
   primary key(idUsuario, idTag),
   foreign key (idUsuario) references Usuarios(idUsuario),
   foreign key (idTag) references Tags (idTag)
);


CREATE TABLE Carrito(
	idCarrito INT NOT NULL AUTO_INCREMENT,
    idCliente int,
    idDatosFacturacion int,
	fechaVenta DATETIME,
	-- impuesto FLOAT, viola la quinta FN
	-- Total FLOAT comment "Por la fluctuación de precios",
	estadoVenta char(10) not null default "comprando" comment "retenido, en revisión, incompleta, en progreso, pagado, cancelado, envianda, denegado, entregado",
    estadoEnvio char(10) NOT NULL DEFAULT 'enBodega' comment "Empacando, Enviando, EnCamino, Llegando, Recibido, Cancelado, Retornado, Disputa",    
    formaDePago char(5) comment "credito, debito, trans, deposito",    
	PRIMARY KEY (idCarrito, idCliente),    
    foreign key (idDatosFacturacion) references DatosFacturacion (idDatosFacturacion),
	FOREIGN KEY (idCliente) REFERENCES Usuarios(idUsuario)
);

create table ProductosEnElCarrito(
   idProducto int,
   idCarrito int,
   costoVenta float,
   cantidad float,
   descuento float comment "porcentaje de descuento",
   primary key(idProducto, idCarrito),
   foreign key (idProducto) references Productos (idProducto),
   foreign key (idCarrito) references Carrito(idCarrito)
);


create table TagsCarrito(
   idCarrito int not null ,
   idTag int not null,
   primary key(idCarrito, idTag),
   foreign key (idCarrito) references Carrito (idCarrito),
   foreign key (idTag) references Tags (idTag)
);


create table InventarioProveedores(
   idProdProv int not null auto_increment,
   idProveedor int not null,
   idProducto int not null,
   costo float not null,
   primary key (idProdProv),
   key (idProveedor,idProducto),
   foreign key (idProveedor) references Usuarios(idUsuario),
   foreign key (idProducto) references Productos (idProducto)
);

create table PreciosProds(
   idProdProv int not null,
   precio float,
   fechaActualizacion datetime,
   validada boolean,
   primary key(idProdProv, fechaActualizacion) comment "EL COSTO ES EL ÚLTIMO QUE SE ACTUALIZÓ", 
   foreign key (idProdProv) references InventarioProveedores(idProdProv)
);



/*
describe usuarios;
/*31/marzo vistas/
create view usuarios_registro as select idUsuario,
NombreUser,Apellido from usuarios where Direccion is null;

create view carrito_prod_guard as select cantidad 
from ProductosEnElCarrito as ProdC inner join productos
as P on ProdC.idProducto= P.idProducto inner join carrito 
as C on ProdC.idCarrito= C.idCarrito where cantidad=10;

create view prod_org_mariscos as select nombrePro, precio
from productos where precio > 50; 
*/


-- <Inserts> 

select * from categorias;
/*INSERTS DE LA TABLA CATEGORIA*/
insert into categorias (nomCategoria)
values ('Servicios');
insert into categorias (nomCategoria)
values ('Ingredientes');
insert into categorias (nomCategoria)
values ('Bedidas');
insert into categorias (nomCategoria)
values ('Panadería');
insert into categorias (nomCategoria)
values ('Productos');
insert into categorias (nomCategoria)
values ('Congelados');
insert into categorias (nomCategoria)
values ('Carnes');
insert into categorias (nomCategoria)
values ('Mariscos');

SELECT * FROM Productos;
/*INSERTS DE LA TABLA PRODUCTOS*/
INSERT INTO productos  (nomProd,descripcion,existencia) VALUES ('Pan Artesanal','Pan hecho artesanalmente',4);
INSERT INTO productos  (nomProd,descripcion,existencia) VALUES ('Miel Artesanal','Miel Hecha por Apicultores locales',30);
INSERT INTO productos  (nomProd,descripcion,existencia) VALUES ('Jitomate','Jitomate recolectado por Agricultores locales',50);
INSERT INTO productos  (nomProd,descripcion,existencia) VALUES ('Salsa Artesanal','Salsa casera hecha artesanalmente',100);


SELECT * FROM Tags;

/*INSERTS DE LA TABLA TAGS*/
INSERT INTO greenco_gps.tags (idCategoria,nomTag)
values (2,'orgánico');

SELECT * FROM RolesDeUsuarios;
select * from usuariosroles;
select * from usuarios;
/*INSERS DE LA TABLA ROLES DE USUARIO*/
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('usuario');
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('productor');
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('admin');

-- INSERT DE 1 USUARIO ADMIN
INSERT INTO usuarios (nickname,nomUsuario,apellidos,fechaNacimiento,rutaFotoUsuario,num) 
values ('admin','Administrador de greenco','apellidos','2022-09-18','ruta.png','1234567891');

-- INSERT EL ROL DEL USUARIO ADMIN
INSERT INTO usuariosroles values (3,1);

select * from inventarioproveedores;
INSERT INTO inventarioproveedores (idProveedor,idProducto,costo)values (1,1,45);
INSERT INTO inventarioproveedores (idProveedor,idProducto,costo)values (1,2,100);
INSERT INTO inventarioproveedores (idProveedor,idProducto,costo)values (1,3,15.5);
INSERT INTO inventarioproveedores (idProveedor,idProducto,costo)values (1,4,49.99);

/*create table PreciosProds(
   idProdProv int not null,
   precio float,
   fechaActualizacion datetime,
   validada boolean,
   primary key(idProdProv, fechaActualizacion) comment "EL COSTO ES EL ÚLTIMO QUE SE ACTUALIZÓ", 
   foreign key (idProdProv) references InventarioProveedores(idProdProv)
);*/
select * from preciosProds;
INSERT into preciosProds (idProdProv,precio,fechaActualizacion,validada) values (1,45,current_timestamp(),true);

--  </Inserts>