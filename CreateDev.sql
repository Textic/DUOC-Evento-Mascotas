CREATE USER dev IDENTIFIED BY "Test123321_";
GRANT CREATE SESSION TO dev;
GRANT RESOURCE TO dev;
ALTER USER dev DEFAULT TABLESPACE USERS;
ALTER USER dev TEMPORARY TABLESPACE TEMP;
ALTER USER dev QUOTA UNLIMITED ON USERS;

INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('10-05-2025', 'Plaza Sotomayor, Valparaíso', 'Concierto Homenaje a Violeta Parra');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('15-06-2025', 'Quinta Vergara, Viña del Mar', 'Gala Festival de Invierno');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('16-07-2025', 'Plaza de Armas, La Tirana', 'Celebración Fiesta de La Tirana');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('05-08-2025', 'Valle del Elqui, Coquimbo', 'Noche de Astroturismo y Estrellas');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('18-09-2025', 'Parque O Higgins, Santiago', 'Fondas Oficiales Fiestas Patrias');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('12-10-2025', 'Estadio Monumental, Santiago', 'Clásico Colo-Colo vs U. de Chile');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('01-11-2025', 'Barrio Lastarria, Santiago', 'Feria de Antigüedades y Libros');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('25-11-2025', 'Teatro del Lago, Frutillar', 'Concierto Orquesta Sinfónica Nacional');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('31-12-2025', 'Torre Entel, Santiago', 'Show de Año Nuevo Torre Entel');
INSERT INTO DEV.EVENTOS (FECHA, LUGAR, NOMBRE) VALUES ('20-02-2026', 'Costanera, Valdivia', 'Noche Valdiviana - Carnaval Fluvial');

INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Javiera González', 3, 'Rocky', 'Bulldog Francés', 'Perro');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Matías Muñoz', 5, 'Misha', 'Siames', 'Gato');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Catalina Rojas', 1, 'Coco', 'Quiltro', 'Perro'); -- Usando 'Quiltro' para mestizo
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Benjamín Díaz', 8, 'Luna', 'Labrador', 'Perro');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Sofía Soto', 2, 'Simba', 'Angora', 'Gato');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Vicente Contreras', 4, 'Thor', 'Pastor Alemán', 'Perro');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Isidora Silva', 6, 'Nala', 'Ragdoll', 'Gato');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Agustín Martínez', 10, 'Max', 'Golden Retriever', 'Perro');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Emilia Sepúlveda', 7, 'Kiwi', 'Poodle Toy', 'Perro');
INSERT INTO DEV.MASCOTAS (DUENNO, EDAD, NOMBRE, RAZA, TIPO) VALUES ('Tomás Fuentes', 3, 'Felix', 'Doméstico Común', 'Gato'); -- Usando 'Doméstico Común'

INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('j.gonzalez@email.cl', 'Juan González', 'pass123', 'Admin');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('maria.munoz@mimail.cl', 'María Muñoz', 'clave456', 'Usuario');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('c.rojas@empresa.cl', 'Carlos Rojas', 'segura789', 'Editor');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('laura.diaz@gmail.com', 'Laura Díaz', 'miclave0', 'Usuario'); -- Mantenemos algunos genéricos
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('admin@sistema.gob.cl', 'Administrador Pérez', 'adminPass!', 'Admin');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('p.soto@testing.net', 'Pedro Soto', 'testpass', 'Usuario');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('editor.contreras@portalnoticias.cl', 'Editor Contreras', 'edit.123', 'Editor');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('soporte.silva@entel.cl', 'Soporte Silva', 'support#XYZ', 'Admin');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('cliente.martinez@bancoestado.cl', 'Cliente Martínez', 'vipAccess', 'Usuario');
INSERT INTO DEV.USUARIOS (CORREO, NOMBRE, PASSWORD, ROL) VALUES ('nuevo.sepulveda@educa.cl', 'Nuevo Sepúlveda', 'temporal.1', 'Usuario');

COMMIT;