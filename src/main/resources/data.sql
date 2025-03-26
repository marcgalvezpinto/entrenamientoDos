-- Archivo de datos iniciales para la base de datos H2
-- Este archivo se ejecuta automáticamente al iniciar la aplicación cuando
-- spring.jpa.hibernate.ddl-auto está configurado como 'create' o 'create-drop'

-- Inserción de productos de ejemplo
INSERT INTO personas (nombre, apellido, documento_identidad, fecha_nacimiento, correo, telefono, direccion, created_at)
VALUES
('Juan', 'Pérez', '12345678-9', '1990-05-15', 'juan.perez@example.com', '987654321', 'Calle 1, Ciudad', CURRENT_TIMESTAMP()),
('Ana', 'Gómez', '98765432-1', '1985-03-10', 'ana.gomez@example.com', '912345678', 'Calle 2, Ciudad', CURRENT_TIMESTAMP()),
('Carlos', 'López', '19283746-5', '1978-07-22', 'carlos.lopez@example.com', '923456789', 'Calle 3, Ciudad', CURRENT_TIMESTAMP()),
('María', 'Rodríguez', '87654321-0', '1995-09-14', 'maria.rodriguez@example.com', '934567891', 'Calle 4, Ciudad', CURRENT_TIMESTAMP()),
('Pedro', 'Ramírez', '12348765-4', '1992-11-30', 'pedro.ramirez@example.com', '945678912', 'Calle 5, Ciudad', CURRENT_TIMESTAMP()),
('Sofía', 'Martínez', '54321987-6', '1988-01-05', 'sofia.martinez@example.com', '956789123', 'Calle 6, Ciudad', CURRENT_TIMESTAMP()),
('Jorge', 'Fernández', '67890123-8', '1975-04-12', 'jorge.fernandez@example.com', '967891234', 'Calle 7, Ciudad', CURRENT_TIMESTAMP()),
('Laura', 'Hernández', '34567890-1', '1999-12-25', 'laura.hernandez@example.com', '978912345', 'Calle 8, Ciudad', CURRENT_TIMESTAMP()),
('Diego', 'Torres', '78901234-2', '1983-06-08', 'diego.torres@example.com', '989123456', 'Calle 9, Ciudad', CURRENT_TIMESTAMP()),
('Elena', 'Ríos', '90123456-3', '2000-08-19', 'elena.rios@example.com', '990123456', 'Calle 10, Ciudad', CURRENT_TIMESTAMP());