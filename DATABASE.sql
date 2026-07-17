CREATE DATABASE ejemplo_java;

USE ejemplo_java;

create table Categoria(
	id int primary key auto_increment,
	nombre varchar(50) not null,
	descripcion varchar(150) not null
);

create table Productos(
	id int primary key auto_increment,
	nombre varchar(50) not null,
	descripcion varchar(150) not null,
	categoria_id int not null, 
	stock int not null,
	precio_compra double not null,
	precio_venta double not null,
	foreign key (categoria_id) references Categoria(id)
);
