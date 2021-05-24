/*
drop table gestionVehiculo;
drop table coche;
drop table microbus;
drop table furgoneta;
drop table camion;
drop table alquiler;
drop table vehiculo;
drop table factura;
drop table cliente;
drop table empleado;
drop table carnet;
*/
create table carnet (
	tipo varchar2(1) not null,
	descripcion varchar2(100),
	constraint pk_carnets primary key(tipo)    
);

create table cliente (
	idCliente number generated by default on null as identity,
	dni varchar2(10) unique not null,
	nombre varchar2(60) not null,
	apellidos varchar2(100) not null,
	domicilio varchar2(100),
	CP varchar2(10),
	email varchar2(50) not null,
	fechaNac date not null,
	carnet varchar2(1) not null,
	foto blob,
	changedTS timestamp default (current_timestamp) not null ,
	changedBy varchar2(20) default ('initial') not null,
	constraint pk_cliente primary key(idCliente),
    constraint cliente_carnet foreign key (carnet) references carnet(tipo)
);

create table vehiculo (
	matricula varchar2(10) not null,
	precioDia number(9,2) not null,
	marca varchar2(30) not null,
	descripcion varchar2(255),
	color varchar2(15) not null,
	motor varchar2(20) not null,
	cilindrada number,
	fechaAdq date not null,
	estado varchar2(20) not null,
	carnet varchar2(1) not null,
    changedTS timestamp default (current_timestamp) not null ,
	changedBy varchar2(20) default ('initial') not null,
	constraint pk_vehiculo primary key(matricula),
	constraint vehiculo_carnet foreign key (carnet) references carnet(tipo)
);

create table factura (
    idFactura number generated by default on null as identity,
    fecha date not null,
    importeBase number(9,2) not null,
    importeIVA number(9,2),
    clienteId number not null,
    changedTS timestamp default (current_timestamp) not null ,
	changedBy varchar2(20) default ('initial') not null,
    constraint pk_factura primary key(idFactura),
    constraint factura_cliente foreign key (clienteId) references cliente(idCliente)
);

create table alquiler(
    idAlquiler number generated by default on null as identity,
    idFactura number not null,
    matricula varchar2(10) not null, 
    fechaInicio date not null,
    fechaFin date not null,
    precio number(9,2),
    changedTS timestamp default (current_timestamp) not null ,
	changedBy varchar2(20) default ('initial') not null,
    constraint pk_alquiler primary key(idAlquiler,idFactura),
    constraint alquiler_factura foreign key(idFactura) references factura(idFactura),
    constraint alquiler_vehiculo foreign key(matricula) references vehiculo(matricula)
);


create table empleado (
    idEmpleado number generated by default on null as identity,
    DNI varchar2(10) unique not null,
    nombre varchar2(60) not null,
	apellidos varchar2(100) not null,
	domicilio varchar2(100),
	CP varchar2(10),
	email varchar2(50) not null,
	fechaNac date not null,
	cargo varchar2(30) not null,
	password raw(32) not null,
    changedTS timestamp default (current_timestamp) not null ,
	changedBy varchar2(20) default ('initial') not null,
    constraint pk_empleado primary key(idEmpleado)
);

create table gestionVehiculo (
    idEmpleado number,
    matricula varchar2(10),
    constraint pk_gestionc primary key(idEmpleado,matricula),
    constraint gestionc_empleado foreign key (idEmpleado) references empleado(idEmpleado),
    constraint gestionc_vehiculo foreign key (matricula) references vehiculo(matricula)
);

create table coche (
    matricula varchar2(10),
    numPlazas number(3) not null,
    numPuertas number(3) not null,
    constraint pk_coche primary key(matricula),
    constraint coche_vehiculo foreign key(matricula) references vehiculo(matricula)
);

create table microbus (
    matricula varchar2(10),
    numPlazas number(3) not null,
    medida number(9,2) not null,
    constraint pk_microbus primary key(matricula),
    constraint microbus_vehiculo foreign key(matricula) references vehiculo(matricula)
);

create table furgoneta (
    matricula varchar2(10),
    mma number(7),
    constraint pk_furgoneta primary key(matricula),
    constraint furgoneta_vehiculo foreign key(matricula) references vehiculo(matricula)
);

create table camion (
    matricula varchar2(10),
    numRuedas number(2),
    mma number(7),
    constraint pk_camion primary key(matricula),
    constraint camion_vehiculo foreign key(matricula) references vehiculo(matricula)
);

create global temporary table tmp_estructura (
      id_transaccion number, 
      origen_datos varchar2(4000),
      n1 number,
      n2 number, 
      n3 number, 
      n4 number, 
      n5 number,
      n6 number,
      n7 number,
      n8 number,
      n9 number,
      n10 number,
      c1 varchar2(4000),
      c2 varchar2(4000),
      c3 varchar2(4000),
      c4 varchar2(4000), 
      c5 varchar2(4000),
      c6 varchar2(4000),
      c7 varchar2(4000),
      c8 varchar2(4000),
      c9 varchar2(4000),
      c10 varchar2(4000)
) on commit preserve rows;
