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

delimiter //

create procedure sp_insertar_cliente(
    in p_nombres varchar(100),
    in p_apellidos varchar(100),
    in p_telefono varchar(20),
    in p_correo varchar(100)
)
begin
    insert into cliente(nombres, apellidos, telefono, correo) 
    values (p_nombres, p_apellidos, p_telefono, p_correo);
end //

create procedure sp_listar_clientes()
begin
    select * from cliente;
end //

create procedure sp_insertar_vehiculo(
    in p_placa varchar(20),
    in p_marca varchar(50),
    in p_modelo varchar(50),
    in p_anio int,
    in p_id_cliente int
)
begin
    insert into vehiculo(placa, marca, modelo, anio, id_cliente) 
    values (p_placa, p_marca, p_modelo, p_anio, p_id_cliente);
end //

create procedure sp_listar_vehiculos()
begin
    select * from vehiculo;
end //

delimiter ;
