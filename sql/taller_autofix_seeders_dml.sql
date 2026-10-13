-- Inserción de roles iniciales
insert into rol (nombre_rol) values 
('Jefe de Taller'), 
('Mecanico'), 
('Cajero');

-- Inserción de usuarios iniciales
insert into usuario (nombre, usuario, contrasena, id_rol) values 
('Administrador Taller', 'admin', '123456', 1),
('Juan Mecanico', 'jmecanico', '123456', 2),
('Ana Cajera', 'acajera', '123456', 3);

-- Inserción de clientes de prueba
insert into cliente (nombres, apellidos, telefono, correo) values 
('Carlos', 'Pérez', '5555-1234', 'carlos.perez@email.com'),
('María', 'González', '4444-5678', 'maria.gonzalez@email.com'),
('Juan', 'López', '3333-9012', 'juan.lopez@email.com'),
('Ana', 'Martínez', '2222-3456', 'ana.martinez@email.com'),
('Luis', 'Rodríguez', '1111-7890', 'luis.rodriguez@email.com');

-- Inserción de vehículos de prueba
insert into vehiculo (placa, marca, modelo, anio, id_cliente) values 
('P-123ABC', 'Toyota', 'Corolla', 2020, 1),
('P-456XYZ', 'Honda', 'Civic', 2019, 2),
('P-789DEF', 'Mazda', '3', 2021, 3),
('P-321GHI', 'Nissan', 'Sentra', 2018, 4),
('P-654JKL', 'Hyundai', 'Elantra', 2022, 5);

-- Inserción de repuestos de prueba
insert into repuesto (nombre_repuesto, precio_unitario, stock) values 
('Filtro de Aceite', 75.00, 25),
('Pastillas de Freno', 250.00, 15),
('Bujía de Encendido', 45.00, 40),
('Correa de Distribución', 320.00, 10),
('Amortiguador Delantero', 600.00, 8);