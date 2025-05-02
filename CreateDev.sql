CREATE USER eventomascota
IDENTIFIED BY "HolaHola123321"
DEFAULT TABLESPACE DATA
TEMPORARY TABLESPACE TEMP
QUOTA UNLIMITED ON DATA;
GRANT CREATE SESSION, RESOURCE TO eventomascota;
GRANT CREATE VIEW TO eventomascota;
GRANT CREATE ROLE TO eventomascota;
GRANT DBA TO eventomascota;
ALTER USER eventomascota
DEFAULT ROLE RESOURCE;

INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Concierto Homenaje a Violeta Parra', '10-05-2025', 'Plaza Sotomayor, Valparaíso', 'Evento musical en homenaje a Violeta Parra.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Gala Festival de Invierno', '15-06-2025', 'Quinta Vergara, Viña del Mar', 'Gala especial del festival de invierno.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Celebración Fiesta de La Tirana', '16-07-2025', 'Plaza de Armas, La Tirana', 'Fiesta tradicional de La Tirana.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Noche de Astroturismo y Estrellas', '05-08-2025', 'Valle del Elqui, Coquimbo', 'Observación de estrellas y astroturismo.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Fondas Oficiales Fiestas Patrias', '18-09-2025', 'Parque O Higgins, Santiago', 'Celebración de Fiestas Patrias en Santiago.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Clásico Colo-Colo vs U. de Chile', '12-10-2025', 'Estadio Monumental, Santiago', 'Partido de fútbol clásico nacional.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Feria de Antigüedades y Libros', '01-11-2025', 'Barrio Lastarria, Santiago', 'Feria de antigüedades y libros en Lastarria.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Concierto Orquesta Sinfónica Nacional', '25-11-2025', 'Teatro del Lago, Frutillar', 'Concierto de la Orquesta Sinfónica Nacional.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Show de Año Nuevo Torre Entel', '31-12-2025', 'Torre Entel, Santiago', 'Show de fuegos artificiales de Año Nuevo.');
INSERT INTO EVENTOS (NOMBRE, FECHA, UBICACION, DESCRIPCION) VALUES ('Noche Valdiviana - Carnaval Fluvial', '20-02-2026', 'Costanera, Valdivia', 'Carnaval fluvial tradicional de Valdivia.');

INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Rocky', 3, 'Perro', 'Bulldog Francés', 'Javiera González');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Misha', 5, 'Gato', 'Siames', 'Matías Muñoz');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Coco', 1, 'Perro', 'Quiltro', 'Catalina Rojas');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Luna', 8, 'Perro', 'Labrador', 'Benjamín Díaz');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Simba', 2, 'Gato', 'Angora', 'Sofía Soto');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Thor', 4, 'Perro', 'Pastor Alemán', 'Vicente Contreras');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Nala', 6, 'Gato', 'Ragdoll', 'Isidora Silva');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Max', 10, 'Perro', 'Golden Retriever', 'Agustín Martínez');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Kiwi', 7, 'Perro', 'Poodle Toy', 'Emilia Sepúlveda');
INSERT INTO MASCOTAS (NOMBRE, EDAD, TIPO, RAZA, DUENNO) VALUES ('Felix', 3, 'Gato', 'Doméstico Común', 'Tomás Fuentes');

INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('jgonzalez', 'Juan González', 'j.gonzalez@email.cl', 'pass123', 'Admin');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('mmunoz', 'María Muñoz', 'maria.munoz@mimail.cl', 'clave456', 'Usuario');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('crojas', 'Carlos Rojas', 'c.rojas@empresa.cl', 'segura789', 'Editor');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('ldiaz', 'Laura Díaz', 'laura.diaz@gmail.com', 'miclave0', 'Usuario');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('adminperez', 'Administrador Pérez', 'admin@sistema.gob.cl', 'adminPass!', 'Admin');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('psoto', 'Pedro Soto', 'p.soto@testing.net', 'testpass', 'Usuario');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('econtreras', 'Editor Contreras', 'editor.contreras@portalnoticias.cl', 'edit.123', 'Editor');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('ssilva', 'Soporte Silva', 'soporte.silva@entel.cl', 'support#XYZ', 'Admin');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('cmartinez', 'Cliente Martínez', 'cliente.martinez@bancoestado.cl', 'vipAccess', 'Usuario');
INSERT INTO USUARIOS (USERNAME, NOMBRE, CORREO, PASSWORD, ROL) VALUES ('nsepulveda', 'Nuevo Sepúlveda', 'nuevo.sepulveda@educa.cl', 'temporal.1', 'Usuario');

COMMIT;