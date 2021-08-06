ALTER TABLE usuario AUTO_INCREMENT=1;
ALTER TABLE privilegios AUTO_INCREMENT=1;
ALTER TABLE configuracion AUTO_INCREMENT=1;

insert into privilegios values (0,'Crear');
insert into privilegios values (0,'Modificar');
insert into privilegios values (0,'Eliminar');
insert into privilegios values (0,'Ventas');

insert into usuario values(0,'User System','sa','sa',1);

insert into usuario_privilegios values (1,1);
insert into usuario_privilegios values (1,2);
insert into usuario_privilegios values (1,3);
insert into usuario_privilegios values (1,4);

insert into configuracion values(0,1);
