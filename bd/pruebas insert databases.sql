create database prueba;

use prueba;

select * from tabla1;
create table tabla1 (
	idtabla int not null auto_increment, 
    nombre char(50), 
    numero int,
    primary key (idtabla)
);

insert into tabla1 (nombre, numero) values ('david',2);
insert into tabla1 (nombre, numero) values ('alejandro',3);
insert into tabla1 (nombre, numero) values ('dominguez',4);
insert into tabla1 (nombre, numero) values ('valerio',5);

create database prueba2;

use prueba2;

create table tabla2(
	idtabla2 int  primary key not null auto_increment,
    nombre char(50),
    numero int
);

select * from tabla2;

INSERT INTO prueba2.tabla2 (nombre, numero) 
SELECT nombre, numero FROM prueba.tabla1;
