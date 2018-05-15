spool scriptProducto.log
grant all privileges to tor identified by root;
conn tor/root
--prompt Dropeamos y creamos el Tablespace
--spool, prompt (Son comandos Auxiliares del SQLPLUS)
-- start, o @nombre.sql // Ejecutamos Script
--drop tablespace TB_DATOS including contents and datafiles;
--CREATE TABLESPACE TB_DATOS 
--datafile 'C:\oraclexe\app\oracle\oradata\XE\TB_DATOS.DBF' 
--SIZE 1M REUSE AUTOEXTEND ON;
--configuracion del terminal para escriba bien las tablas en Windows
SET LINESIZE 32000;
SET PAGESIZE 40000;
SET LONG 50000;
--de esta manera se corrige que escriba los header en 2 lineas
--Creamos una tabla tipo
create table tipo(
	nombre VARCHAR(35),
	porcentaje float,
	constraints pkTipo primary key (nombre)
);
--creamos una tabla Producto y asociamos la variable tipo con el id de la tabla Tipo
create table producto(
	codigo int,
	nombre varchar(35),
	precio float,
	importado number(1,0),
	tipo VARCHAR(35),
	constraints pkProducto primary key (codigo),
	constraints fkProducto foreign key (tipo) references tipo(nombre)
); 
--Insertamos valores a tipo
insert into tipo ( nombre, porcentaje) values ('Canasta Basica',7.0);
insert into tipo ( nombre, porcentaje) values ('Popular',13.0);
insert into tipo ( nombre, porcentaje) values ('Suntuario', 23.5);
--Muestra los valores de la tabla tipo
select * from tipo;
--insertamos valores a Producto
insert into producto (codigo,nombre,precio,importado,tipo) values(1,'Arroz',1700,0,'Canasta Basica');
insert into producto (codigo,nombre,precio,importado,tipo) values(2,'Frijoles',1200,0,'Canasta Basica');
insert into producto (codigo,nombre,precio,importado,tipo) values(3,'Leche',700,0,'Canasta Basica');
insert into producto (codigo,nombre,precio,importado,tipo) values(4,'Cereal',2100,0,'Popular');
insert into producto (codigo,nombre,precio,importado,tipo) values(5,'Pan Cuadrado',1600,0,'Popular');
insert into producto (codigo,nombre,precio,importado,tipo) values(6,'Sopa',300,0,'Popular');
insert into producto (codigo,nombre,precio,importado,tipo) values(7,'Chocolate',1000,1,'Popular');
insert into producto (codigo,nombre,precio,importado,tipo) values(8,'Oro Comestible',5000,1,'Suntuario');
insert into producto (codigo,nombre,precio,importado,tipo) values(9,'Caviar',6000,1,'Suntuario');
--Consultamos todo lo que tiene la tabla
select * from producto;

--Procedimientos Almacenados


--Recibe todos los datos necesarios para ingresar un producto
CREATE OR REPLACE PROCEDURE insertarProducto(
                            codigo IN producto.codigo%TYPE,
                            nombre IN producto.nombre%TYPE,
                            precio IN producto.precio%TYPE,
                            importado IN producto.importado%TYPE,
                            tipo IN producto.tipo%TYPE
)
AS
BEGIN
	insert into producto (codigo,nombre,precio,importado,tipo) 
                values (codigo,nombre,precio,importado,tipo);
END;
/
--Llamamos al procedimiento y vemos como afecta la tabla
--insertando un registro 10 a diferencia que teniamos 9
call insertarProducto(10,'Prueba',100.0,0,'Popular');
select * from producto;

--Modifica el Producto segun su codigo, recibe todos los datos
CREATE OR REPLACE PROCEDURE modificarProducto(
                            codigoIN IN producto.codigo%TYPE,
                            nombreIN IN producto.nombre%TYPE,
                            precioIN IN producto.precio%TYPE,
                            importadoIN IN producto.importado%TYPE,
                            tipoIN IN producto.tipo%TYPE
)
AS
BEGIN
	UPDATE producto 
  SET nombre=nombreIN,precio=precioIN,importado=importadoIN,tipo=tipoIN
  WHERE codigo=codigoIN;
END;
/
--Llamamos al procedimiento y vemos como afecta la tabla
--cambiando el nombre y el precio en el registro 10
call modificarProducto(10,'Prueba Cambio',100.3,0,'Popular');
select * from producto;

--eliminar por codigo, recibe unicamente un parametro
CREATE OR REPLACE PROCEDURE eliminarProducto(codigoIN IN producto.codigo%TYPE)
AS
BEGIN
	delete from producto where codigo=codigoIN;
END;
/
--vemos como afecta la tabla eliminando el registro 10
call eliminarProducto(10);
select * from producto;


--esto es necesario para el funcionamiento de los cursores
CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;
/

--Lista todos los productos de la tabla, igual a un select pero en funcion.
CREATE OR REPLACE function listaProductos
return Types.ref_cursor 
as 
        producto_cursor types.ref_cursor; 
begin 
  open producto_cursor for 
       select producto.codigo, producto.nombre, producto.precio, producto.importado, producto.tipo, tipo.porcentaje, (producto.precio/100*tipo.porcentaje) as impuesto, (producto.precio+(producto.precio/100*tipo.porcentaje)+(producto.importado*(producto.precio/100*tipo.porcentaje)*0.50)) as preciofinal
       FROM producto, tipo where producto.tipo=tipo.nombre;
return producto_cursor;
end;
/

select listaProductos from dual;

--Busca un producto segun su tipo: 1 canasta basica, 2 popular, 3 suntuario
CREATE OR REPLACE FUNCTION buscarProductoTipo(tipoIN IN producto.tipo%TYPE)
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
BEGIN 
  OPEN producto_cursor FOR 
       select producto.codigo, producto.nombre, producto.precio, producto.importado, producto.tipo, tipo.porcentaje, (producto.precio/100*tipo.porcentaje) as impuesto, (producto.precio+(producto.precio/100*tipo.porcentaje)+(producto.importado*(producto.precio/100*tipo.porcentaje)*0.50)) as preciofinal
       FROM producto, tipo where producto.tipo=tipo.nombre and tipo=tipoIN; 
RETURN producto_cursor; 
END;
/
select buscarProductoTipo('Popular') from dual;


--Esta funcion busca los nombres similares al ingresado como parametro, ejemplo buscamos 'pan' y encuentra los registros que digan 'pan'
CREATE OR REPLACE FUNCTION buscarProductoNombre(nombreIN IN producto.nombre%TYPE)
RETURN Types.ref_cursor 
AS 
        producto_cursor types.ref_cursor; 
		v_nombre producto.nombre%TYPE;
BEGIN 
  v_nombre := '%'||UPPER(nombreIN)||'%';
  OPEN producto_cursor FOR 
       select producto.codigo, producto.nombre, producto.precio, producto.importado, producto.tipo, tipo.porcentaje, (producto.precio/100*tipo.porcentaje) as impuesto, (producto.precio+(producto.precio/100*tipo.porcentaje)+(producto.importado*(producto.precio/100*tipo.porcentaje)*0.50)) as preciofinal
       FROM producto, tipo where producto.tipo=tipo.nombre and upper(producto.nombre) like v_nombre; 
RETURN producto_cursor; 
END;
/
--Encontraremos el registro Pan Cuadrado
select buscarProductoNombre('pan') from dual;

--devuelve los tipos para uso en un Dropdown de ser necesario para elegir en la interfaz
CREATE OR REPLACE function listaTipos
return Types.ref_cursor 
as 
        tipo_cursor types.ref_cursor; 
begin 
  open tipo_cursor for 
       select nombre, porcentaje
       FROM tipo;
return tipo_cursor;
end; 
/

select listaTipos from dual;

CREATE OR REPLACE FUNCTION buscarProductoCodigo(codigoIN IN producto.codigo%TYPE)
RETURN Types.ref_cursor
AS
	producto_cursor types.ref_cursor;
	v_codigo producto.codigo%TYPE;
BEGIN
	OPEN producto_cursor FOR
		select producto.codigo, producto.nombre, producto.precio, producto.importado, producto.tipo, tipo.porcentaje, (producto.precio/100*tipo.porcentaje) as impuesto, (producto.precio+(producto.precio/100*tipo.porcentaje)+(producto.importado*(producto.precio/100*tipo.porcentaje)*0.50)) as preciofinal
       FROM producto, tipo where producto.tipo=tipo.nombre and producto.codigo = codigoIN;
RETURN producto_cursor;
END;
/

select buscarProductoCodigo(4) from dual;


--DROP FUNCTION LISTATIPOS;
--drop function buscarProductoNombre;
--drop function buscarProductoTipo;
--drop function listaProductos;
--drop procedure eliminarProducto;
--drop PROCEDURE modificarProducto;
--drop procedure insertarProducto;
--drop table producto;
--drop table tipo;


