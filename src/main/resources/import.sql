#Insertando puestos
insert into puestos (nombre) values ('Director de EPISI'), ('Rector de la UNS'), ('Vicerrector Académico'), ('Estudiante');

#Insertando personas
insert into personas (nombre, email, dni_ruc, cod_estudiante, id_puesto) values ('Gino Ascencio', 'gino@hotmail.com', '73360326', '0201714002', 4), ('Carlos Guerra Cordero', 'guerra@outlook.es', '56232165', '', 1);

#Insertando tipos de archivos
insert into tipo_archivos (nombre) values ('FUT'), ('Consolidado de notas'), ('Certificado de estudios');

#Insertando tipos de solicitudes
insert into tipo_solicitudes (nombre) values ('Asignación jurado de tesis'), ('Aprobacion de tesis'), ('Asigacion jurado de bachiller');

