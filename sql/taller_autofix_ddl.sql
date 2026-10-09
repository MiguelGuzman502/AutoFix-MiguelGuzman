create database if not exists db_taller_autofix_2026;
use db_taller_autofix_2026;

create table rol (
    id_rol int auto_increment primary key,
    nombre_rol varchar(50) not null
);

create table usuario (
    id_usuario int auto_increment primary key,
    nombre varchar(100) not null,
    usuario varchar(50) not null unique,
    contrasenia varchar(255) not null,
    id_rol int not null,
    constraint fk_usuario_rol foreign key (id_rol) references rol(id_rol) on delete cascade on update cascade
);

create table cliente (
    id_cliente int auto_increment primary key,
    nombres varchar(100) not null,
    apellidos varchar(100) not null,
    telefono varchar(20) not null,
    correo varchar(100)
);

create table vehiculo (
    id_vehiculo int auto_increment primary key,
    placa varchar(20) not null unique,
    marca varchar(50) not null,
    modelo varchar(50) not null,
    anio int not null,
    id_cliente int not null,
    constraint fk_vehiculo_cliente foreign key (id_cliente) references cliente(id_cliente) on delete cascade on update cascade
);

create table mecanico (
    id_mecanico int auto_increment primary key,
    nombres varchar(100) not null,
    apellidos varchar(100) not null,
    especialidad varchar(100) not null,
    telefono varchar(20) not null
);

create table orden_trabajo (
    id_orden int auto_increment primary key,
    fecha_ingreso datetime not null,
    kilometraje decimal(10,2) not null,
    descripcion_problema text not null,
    estado varchar(30) not null,
    id_vehiculo int not null,
    id_mecanico int not null,
    constraint fk_orden_vehiculo foreign key (id_vehiculo) references vehiculo(id_vehiculo) on delete cascade on update cascade,
    constraint fk_orden_mecanico foreign key (id_mecanico) references mecanico(id_mecanico) on delete cascade on update cascade
);

create table repuesto (
    id_repuesto int auto_increment primary key,
    nombre_repuesto varchar(100) not null,
    precio_unitario decimal(10,2) not null,
    stock int not null
);

create table detalle_repuesto (
    id_detalle int auto_increment primary key,
    id_orden int not null,
    id_repuesto int not null,
    cantidad int not null,
    subtotal decimal(10,2) not null,
    constraint fk_detalle_orden foreign key (id_orden) references orden_trabajo(id_orden) on delete cascade on update cascade,
    constraint fk_detalle_repuesto foreign key (id_repuesto) references repuesto(id_repuesto) on delete cascade on update cascade
);