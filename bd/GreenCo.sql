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
	descripcion varchar(128),	
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
    rolUsuario char(5) comment "usuario, vendMin, tienda, admin, superadmin",    
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
   tipoContacto char(4) comment "Tel, Cel, Mail, Twit, Face...",
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
INSERT INTO greenco_gps.productos (nomProd,descripcion,existencia)
values ('Aderezo','Aderezo de 200Gr.',3);
insert into Productos (nomProd, descripcion, existencia) 
values ('Pepper - Green, Chili', 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 48);
insert into Productos (nomProd, descripcion, existencia) 
values ('Rappini - Andy Boy', 'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', 97);
insert into Productos (nomProd, descripcion, existencia) 
values ('Potatoes - Yukon Gold 5 Oz', 'Aliquam erat volutpat. In congue. Etiam justo.', 98);
insert into Productos (nomProd, descripcion, existencia) 
values ('Cilantro / Coriander - Fresh', 'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', 29);
insert into Productos (nomProd, descripcion, existencia) 
values ('Beef Striploin Aaa', 'Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat.', 85);
insert into Productos (nomProd, descripcion, existencia) 
values ('Tumeric', 'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', 24);
insert into Productos (nomProd, descripcion, existencia) 
values ('Swiss Chard', 'Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo.', 52);
insert into Productos (nomProd, descripcion, existencia) 
values ('Crackers - Graham', 'Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque.', 15);
insert into Productos (nomProd, descripcion, existencia) 
values ('Canada Dry', 'Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 21);
insert into Productos (nomProd, descripcion, existencia) 
values ('Sour Puss Sour Apple', 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 23);
insert into Productos (nomProd, descripcion, existencia) 
values ('Aspic - Amber', 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', 29);
insert into Productos (nomProd, descripcion, existencia) 
values ('Gingerale - Diet - Schweppes', 'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla.', 91);
insert into Productos (nomProd, descripcion, existencia) 
values ('Muffin Orange Individual', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.', 88);
insert into Productos (nomProd, descripcion, existencia) 
values ('Cookie - Dough Variety', 'In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt.', 97);
insert into Productos (nomProd, descripcion, existencia) 
values ('Sugar - Brown, Individual', 'Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum.', 80);
insert into Productos (nomProd, descripcion, existencia) 
values ('Trueblue - Blueberry 12x473ml', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis.', 31);
insert into Productos (nomProd, descripcion, existencia) 
values ('Nut - Hazelnut, Ground, Natural', 'Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor.', 18);
insert into Productos (nomProd, descripcion, existencia) 
values ('Jicama', 'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', 84);
insert into Productos (nomProd, descripcion, existencia) 
values ('Sea Bass - Fillets', 'Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 21);
insert into Productos (nomProd, descripcion, existencia) 
values ('Yogurt - Strawberry, 175 Gr', 'Phasellus sit amet erat. Nulla tempus.', 37);
insert into Productos (nomProd, descripcion, existencia) 
values ('Flour - Strong Pizza', 'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.', 68);
insert into Productos (nomProd, descripcion, existencia) 
values ('Plums - Red', 'Duis aliquam convallis nunc.', 56);
insert into Productos (nomProd, descripcion, existencia) 
values ('Potatoes - Parissienne', 'Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus.', 61);
insert into Productos (nomProd, descripcion, existencia) 
values ('Crab - Blue, Frozen', 'Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.', 58);
insert into Productos (nomProd, descripcion, existencia) 
values ('Pickle - Dill', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.', 83);
insert into Productos (nomProd, descripcion, existencia) 
values ('Oil - Avocado', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique.', 24);
insert into Productos (nomProd, descripcion, existencia) 
values ('Tea - English Breakfast', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum.', 65);
insert into Productos (nomProd, descripcion, existencia) 
values ('Sugar - Individual Portions', 'Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.', 48);
insert into Productos (nomProd, descripcion, existencia) 
values ('Muffin Mix - Corn Harvest', 'Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', 34);
insert into Productos (nomProd, descripcion, existencia) 
values ('Cake - Miini Cheesecake Cherry', 'Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi.', 28);
insert into Productos (nomProd, descripcion, existencia) 
values ('Oven Mitts 17 Inch', 'Nulla facilisi.', 1);
insert into Productos (nomProd, descripcion, existencia) 
values ('Wine - Acient Coast Caberne', 'Fusce consequat.', 27);
insert into Productos (nomProd, descripcion, existencia) 
values ('Glass - Wine, Plastic, Clear 5 Oz', 'Morbi porttitor lorem id ligula.', 91);
insert into Productos (nomProd, descripcion, existencia) 
values ('Pop - Club Soda Can', 'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.', 27);
insert into Productos (nomProd, descripcion, existencia) 
values ('Clam - Cherrystone', 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.', 1);
insert into Productos (nomProd, descripcion, existencia) 
values ('Wine - Pinot Grigio Collavini', 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor.', 95);
insert into Productos (nomProd, descripcion, existencia) 
values ('Table Cloth 144x90 White', 'Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum.', 45);
insert into Productos (nomProd, descripcion, existencia) 
values ('Canada Dry', 'Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', 35);
insert into Productos (nomProd, descripcion, existencia) 
values ('Banana Turning', 'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', 23);
insert into Productos (nomProd, descripcion, existencia) 
values ('Quail - Jumbo Boneless', 'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', 18);
insert into Productos (nomProd, descripcion, existencia) 
values ('Puree - Blackcurrant', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 27);
insert into Productos (nomProd, descripcion, existencia) 
values ('Wine - Chateau Timberlay', 'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 72);
insert into Productos (nomProd, descripcion, existencia) 
values ('Mushroom - Shitake, Fresh', 'Donec vitae nisi.', 4);
insert into Productos (nomProd, descripcion, existencia) 
values ('Bread Cranberry Foccacia', 'In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl.', 56);
insert into Productos (nomProd, descripcion, existencia) 
values ('Appetizer - Asian Shrimp Roll', 'Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.', 5);
insert into Productos (nomProd, descripcion, existencia) 
values ('Pasta - Shells, Medium, Dry', 'Duis bibendum. Morbi non quam nec dui luctus rutrum.', 47);
insert into Productos (nomProd, descripcion, existencia) 
values ('Foil - Round Foil', 'Mauris lacinia sapien quis libero.', 54);
insert into Productos (nomProd, descripcion, existencia) 
values ('Bread - Dark Rye, Loaf', 'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices.', 9);
insert into Productos (nomProd, descripcion, existencia) 
values ('Crab - Blue, Frozen', 'Nulla ut erat id mauris vulputate elementum.', 56);
insert into Productos (nomProd, descripcion, existencia) 
values ('Temperature Recording Station', 'Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', 60);
insert into Productos (nomProd, descripcion, existencia) 
values ('Lettuce - Boston Bib', 'Fusce posuere felis sed lacus. Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl.', 43);
insert into Productos (nomProd, descripcion, existencia) 
values ('Beer - Moosehead', 'Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.', 52);
insert into Productos (nomProd, descripcion, existencia) 
values ('Vodka - Lemon, Absolut', 'Phasellus sit amet erat.', 53);
insert into Productos (nomProd, descripcion, existencia) 
values ('Butter - Unsalted', 'Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula.', 94);
insert into Productos (nomProd, descripcion, existencia) 
values ('Tomatoes - Diced, Canned', 'Nulla ut erat id mauris vulputate elementum. Nullam varius.', 16);
insert into Productos (nomProd, descripcion, existencia) 
values ('Loquat', 'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum.', 56);
insert into Productos (nomProd, descripcion, existencia) 
values ('Uniform Linen Charge', 'Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante.', 7);
insert into Productos (nomProd, descripcion, existencia) 
values ('Asparagus - Frozen', 'Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.', 21);
insert into Productos (nomProd, descripcion, existencia) 
values ('Crackers Cheez It', 'Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.', 64);
insert into Productos (nomProd, descripcion, existencia) 
values ('Wine - Red, Pinot Noir, Chateau', 'Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.', 49);
insert into Productos (nomProd, descripcion, existencia) 
values ('Potatoes - Instant, Mashed', 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', 57);
insert into Productos (nomProd, descripcion, existencia) 
values ('Soup - Campbells, Chix Gumbo', 'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem. Sed sagittis. Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci. Nullam molestie nibh in lectus.', 62);
insert into Productos (nomProd, descripcion, existencia) 
values ('Pasta - Gnocchi, Potato', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 32);
insert into Productos (nomProd, descripcion, existencia) 
values ('Tortillas - Flour, 12', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.', 97);
insert into Productos (nomProd, descripcion, existencia)
values ('Towels - Paper / Kraft', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat.', 64);
insert into Productos (nomProd, descripcion, existencia) 
values ('Salmon - Atlantic, No Skin', 'Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia. Aenean sit amet justo.', 75);
insert into Productos (nomProd, descripcion, existencia) 
values ('Dr. Pepper - 355ml', 'Curabitur convallis.', 22);
insert into Productos (nomProd, descripcion, existencia) 
values ('Veal - Inside', 'Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus.', 53);
insert into Productos (nomProd, descripcion, existencia) 
values ('Sea Urchin', 'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem.', 99);
insert into Productos (nomProd, descripcion, existencia) 
values ('Magnotta - Bel Paese White', 'Praesent blandit. Nam nulla.', 97);
insert into Productos (nomProd, descripcion, existencia) 
values ('Energy - Boo - Koo', 'In eleifend quam a odio. In hac habitasse platea dictumst.', 56);
insert into Productos (nomProd, descripcion, existencia) 
values ('Bread - Multigrain', 'Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.', 96);
insert into Productos (nomProd, descripcion, existencia) 
values ('Boogies', 'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 36);
insert into Productos (nomProd, descripcion, existencia) 
values ('Beef - Rib Roast, Cap On', 'In hac habitasse platea dictumst.', 77);
insert into Productos (nomProd, descripcion, existencia) 
values ('Beef - Ground, Extra Lean, Fresh', 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 34);
insert into Productos (nomProd, descripcion, existencia) 
values ('Snails - Large Canned', 'Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci.', 94);
insert into Productos (nomProd, descripcion, existencia) 
values ('Sea Bass - Whole', 'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus.', 9);
insert into Productos (nomProd, descripcion, existencia) 
values ('Shrimp - 150 - 250', 'Nulla mollis molestie lorem.', 4);
insert into Productos (nomProd, descripcion, existencia) 
values ('Soup - Campbells Chicken', 'Duis at velit eu est congue elementum. In hac habitasse platea dictumst.', 52);
insert into Productos (nomProd, descripcion, existencia) 
values ('Snapple Lemon Tea', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', 67);
insert into Productos (nomProd, descripcion, existencia) 
values ('Lamb - Racks, Frenched', 'Morbi porttitor lorem id ligula.', 86);
insert into Productos (nomProd, descripcion, existencia) 
values ('Cheese - Le Cheve Noir', 'Mauris ullamcorper purus sit amet nulla.', 11);
insert into Productos (nomProd, descripcion, existencia) 
values ('Mangoes', 'Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti.', 71);
insert into Productos (nomProd, descripcion, existencia) 
values ('Doilies - 8, Paper', 'Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem.', 23);
insert into Productos (nomProd, descripcion, existencia) 
values ('Cumin - Ground', 'Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo.', 82);
insert into Productos (nomProd, descripcion, existencia) 
values ('Bagel - Everything', 'Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio.', 61);
insert into Productos (nomProd, descripcion, existencia) 
values ('Lettuce - Romaine', 'Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla.', 64);
insert into Productos (nomProd, descripcion, existencia) 
values ('Ham - Virginia', 'In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue.', 72);
insert into Productos (nomProd, descripcion, existencia) 
values ('Cherries - Maraschino,jar', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.', 29);
insert into Productos (nomProd, descripcion, existencia) 
values ('Beans - Navy, Dry', 'Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 91);
insert into Productos (nomProd, descripcion, existencia) 
values ('Energy Drink - Franks Original', 'Vivamus vestibulum sagittis sapien.', 78);
insert into Productos (nomProd, descripcion, existencia) 
values ('Beets - Candy Cane, Organic', 'Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius.', 66);
insert into Productos (nomProd, descripcion, existencia) 
values ('Pork - Back, Short Cut, Boneless', 'Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis.', 25);
insert into Productos (nomProd, descripcion, existencia) 
values ('Apple - Delicious, Red', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.', 4);
insert into Productos (nomProd, descripcion, existencia) 
values ('Bulgar', 'Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 3);
insert into Productos (nomProd, descripcion, existencia) 
values ('Ginger - Pickled', 'Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.', 85);
insert into Productos (nomProd, descripcion, existencia) 
values ('Cocoa Powder - Dutched', 'Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.', 20);
insert into Productos (nomProd, descripcion, existencia) 
values ('Chef Hat 20cm', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum.', 9);
insert into Productos (nomProd, descripcion, existencia) 
values ('Veal - Eye Of Round', 'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique.', 48);
insert into Productos (nomProd, descripcion, existencia) 
values ('Chips - Assorted', 'Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui.', 47);

SELECT * FROM Tags;
create table Tags(
   idTag int not null auto_increment,
   idCategoria int not null,
   nomTag char(16) not null unique,
   primary key (idTag),
   foreign key (idCategoria) references Categorias (idCategoria)
);
/*INSERTS DE LA TABLA TAGS*/
INSERT INTO greenco_gps.tags (idCategoria,nomTag)
values (2,'orgánico');

SELECT * FROM RolesDeUsuarios;
/*INSERS DE LA TABLA ROLES DE USUARIO*/
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('usuario');
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('vendMin');
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('tienda');
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('admin');
INSERT INTO RolesDeUsuarios (rolUsuario) VALUES ('superAdmin');
--  </Inserts>
