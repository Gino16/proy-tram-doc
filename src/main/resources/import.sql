#Insertando puestos
insert into puestos (nombre) values ('Director de EPISI'), ('Rector de la UNS'), ('Vicerrectora Académica'), ('Estudiante'), ('Empresa'), ('Bienestar Universitario');

#Insertando personas
insert into personas (nombre, email, dni_ruc, cod_estudiante, id_puesto) values ('Gino Ascencio', 'gino@hotmail.com', '73360326', '0201714002', 4), ('Carlos Guerra Cordero', 'guerra@outlook.es', '56232165', '', 1), ('Sixto Diaz Tello', 'sixto@uns.edu.pe', '91823745', '', 2), ('Patrick Borka Li', 'borja@hotmail.com', '92839401', '0201714005', 4), ('Lia Salazar Soto', 'vrad@uns.edu.pe', '23912394' , '', 3);

#Insertando tipos de archivos
insert into tipo_archivos (nombre) values ('FUT'), ('Consolidado de notas'), ('Certificado de estudios'), ('Consolidado de matricula'), ('Constancia juridica'),('Otros');

#Insertando tipos de solicitudes
insert into tipo_solicitudes (nombre) values ('Asignación jurado de tesis'), ('Aprobacion de proyecto de tesis'), ('Asigacion jurado de bachiller'), ('Resolucion de expedito'), ('Examen de Suficiencia'), ('Reserva de Matricula'), ('Carpeta de titulo profesional de Sistemas');

#Insertando estados
insert into estados(nombre) values ('Registrado'),('Recepcionado'),('Aprobado'),('Rechazado'),('Archivado');

insert into usuarios(username, password, dni_ruc) values ('secre', '$2a$10$2FOqYiuljg2xyJg0UPiKl.0tg0jPu8tpKz42uAoT8AtH/HWzitlhC','56232165'), ('rector', '$2a$10$hjBE.ip9NXwt9iq//vxmqOJdpwrN5/xLpiTESt9JsaiHV7C36SYXC', '91823745'), ('vrad', '$2a$10$7xzSF1NOHlvt9D1GyqrAyeuJkQn4xMCGDLOx7tlh2eisRJaL/QgAi', '23912394');

insert into roles(id_usuario, nombre) values (1, 'ROLE_USER'), (1, 'ROLE_INVITED'), (2, 'ROLE_INVITED'), (3,'ROLE_INVITED');