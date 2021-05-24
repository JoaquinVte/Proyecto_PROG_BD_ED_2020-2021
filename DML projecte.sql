/* Insertamos carnets */
insert into carnet (tipo, descripcion) VALUES
('A', 'Solo motos');
insert into carnet (tipo, descripcion) VALUES
('B', 'Solo coches');
insert into carnet (tipo, descripcion) VALUES
('C', 'Furgonetas y coches');
insert into carnet (tipo, descripcion) VALUES
('D', 'Microbús y coches');
insert into carnet (tipo, descripcion) VALUES
('E', 'Cualquier vehículo');
insert into carnet (tipo, descripcion) VALUES
('Z', 'Ningún vehículo - o porque no tiene, o porque se lo han retirado');

/* Insertamos clientes */
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('12345678B', 'alexis', 'navarro máñez', 'c/ gloria fuertes, 2', '46015', 'email21@dominio.es', '14/3/1977', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('11111111C', 'monica', 'olmo soria', 'c/ constitución, 20', '46115', 'email22@dominio.es','24/1/1987', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('22222222D', 'david', 'garcía pastor', 'c/ democracia, 1', '46215', 'email23@dominio.es','7/2/1973', 'C');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('33333333E', 'ana', 'torro molla', 'c/ rodríguez de la fuente, 2', '46315', 'email24@dominio.es','14/3/1966', 'C');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('44444444F', 'raquel', 'ramos gilabert', 'c/ machado, 15', '46415', 'email31@dominio.es','29/4/1983', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('55555555G', 'miguel', 'martí sánchez', 'c/ lopez vega, 5', '46515', 'email32@dominio.es','1/5/1989', 'D');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('66666666H', 'jose', 'díaz martínez', 'c/ cavallers, 9', '46615', 'email33@dominio.es','28/6/1991', 'D');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('77777777I', 'florencio', 'higueras flores', 'c/ sant miquel, 7', '46715', 'email34@dominio.es','29/3/1999', 'E');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('88888888J', 'arcadio', 'pintado jaen', 'c/ sant jaume, 6', '46815', 'email35@dominio.es','1/1/2001', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('99999999K', 'mariano', 'rus roig', 'c/ major, 100', '46915', 'email55@dominio.es','24/12/2000', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('23456789L', 'manuel', 'moreno zarzoso', 'c/ pere, 2', '46025', 'email54@dominio.es','14/11/1977', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('34567890M', 'raúl', 'viade moya', 'c/ de les mates, 2', '46035', 'email44@dominio.es','24/10/1997', 'E');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('45678901N', 'javier', 'torres pérez', 'c/ informàtica, 43', '46045', 'email99@dominio.es','1/11/1991', 'E');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('56789012O', 'antonio', 'tur tur', 'c/ dijstra, 11', '46005', 'email69@dominio.es', '9/11/1995', 'D');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('67890123P', 'luis', 'santiago sánchez', 'c/ ada lovelace, 13', '46055', 'email61@dominio.es','11/11/1975', 'D');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('78901234Q', 'nuria', 'rodríguez ramírez', 'c/ von neumann, 17', '46065', 'email62@dominio.es','2/7/1967', 'C');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('89012345R', 'marta', 'osvaldo ostarriz', 'c/ grace murray, 19', '46075', 'email63@dominio.es','3/7/1997', 'C');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('90123456S', 'emma', 'pérez pérez', 'c/ baggage, 23', '46019', 'email3@dominio.es','4/6/1992', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('01234567T', 'carmen', 'nuncia lópez', 'c/ turing, 42', '46018', 'email2@dominio.es','5/5/1988', 'B');
insert into cliente (dni, nombre, apellidos, domicilio, CP, email, fechaNac, carnet) VALUES
('00000001W', 'eva', 'martínez valero', 'c/ pascal, 99', '46011', 'email1@dominio.es','6/4/1982', 'D');


/* Insertamos vehículos - coches*/
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('0000AAA', 39.99, 'BMW i3', 'verde', 'electrico', 200, '07/01/2021', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('0000AAA', 4, 4);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('1111AAA', 39.99, 'BMW i3', 'rojo', 'electrico', 200, '07/01/2021', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('1111AAA', 4, 4);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('2222AAA', 39.99, 'BMW i3', 'azul', 'electrico', 200, '07/01/2021', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('2222AAA', 4, 4);

insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('3333AAA', 59.99, 'Tesla s3', 'blanco', 'electrico', 900, '11/11/2020', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('3333AAA', 5, 5);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('4444AAA', 59.99, 'Tesla s3', 'negro', 'electrico', 900, '11/11/2020', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('4444AAA', 5, 5);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('5555AAA', 59.99, 'Tesla s3', 'amarillo', 'electrico', 900, '11/11/2020', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('5555AAA', 5, 5);

insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('6666AAA', 39.99, 'Renault Captur HE', 'rojo', 'hibrido enchufable', 400, '28/02/2021', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('6666AAA', 5, 4);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('7777AAA', 39.99, 'Renault Captur HE', 'verde', 'hibrido enchufable', 400, '28/02/2021', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('7777AAA', 5, 4);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('8888AAA', 39.99, 'Renault Captur HE', 'amarillo', 'hibrido enchufable', 400, '28/02/2021', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('8888AAA', 5, 4);

insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('9999AAA', 29.99, 'FIAT 500', 'azul', 'gasolina', 200, '07/01/2019', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('9999AAA', 4, 3);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('0000AAB', 29.99, 'FIAT 500', 'blanco', 'gasolina', 200, '07/01/2019', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('0000AAB', 4, 3);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('1111AAB', 29.99, 'FIAT 500', 'amarillo', 'gasolina', 200, '07/01/2019', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('1111AAB', 4, 3);

insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('2222AAB', 29.99, 'Volkswagen Polo', 'verde', 'gasolina', 300, '01/08/2018', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('2222AAB', 5, 5);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('3333AAB', 29.99, 'Volkswagen Polo', 'blanco', 'gasolina', 300, '01/08/2018', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('3333AAB', 5, 5);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('4444AAB', 29.99, 'Volkswagen Polo', 'negro', 'gasolina', 300, '01/08/2018', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('4444AAB', 5, 5);

insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('5555AAB', 49.99, 'Volkswagen Golf', 'verde', 'gasolina', 600, '07/01/2018', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('5555AAB', 5, 3);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('6666AAB', 49.99, 'Volkswagen Golf', 'rojo', 'gasolina', 600, '07/01/2019', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('6666AAB', 5, 3);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('7777AAB', 49.99, 'Volkswagen Golf', 'azul', 'gasolina', 600, '07/01/2020', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('7777AAB', 5, 5);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, cilindrada, fechaAdq, estado, carnet) values
('8888AAB', 49.99, 'Volkswagen Golf', 'negro', 'gasolina', 600, '07/01/2021', 'preparado', 'B');
insert INTO coche (matricula, numPlazas, numPuertas) values
('8888AAB', 5, 5);

/* Insertamos vehículos - furgonetas*/
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('1234BBB', 79.99, 'Mercedes Vito', 'negro', 'gasolina', '07/01/2019', 'preparado', 'C');
insert INTO furgoneta (matricula, mma) values
('1234BBB', 5000);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('2355BBB', 89.99, 'Mercedes Vito', 'blanco', 'gasolina', '07/01/2020', 'preparado', 'C');
insert INTO furgoneta (matricula, mma) values
('2355BBB', 7500);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('3991BBB', 89.99, 'Mercedes Vito', 'azul', 'gasolina', '07/01/2021', 'preparado', 'C');
insert INTO furgoneta (matricula, mma) values
('3991BBB', 7500);

insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('9999BBB', 79.99, 'Renault Kangoo ZE', 'azul', 'electrico', '01/08/2020', 'preparado', 'C');
insert INTO furgoneta (matricula, mma) values
('9999BBB', 5000);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('8888BBB', 89.99, 'Renault Kangoo ZE', 'negro', 'electrico', '01/09/2020', 'preparado', 'C');
insert INTO furgoneta (matricula, mma) values
('8888BBB', 7500);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('7777BBB', 99.99, 'Renault Kangoo ZE', 'rojo', 'electrico', '07/01/2021', 'preparado', 'C');
insert INTO furgoneta (matricula, mma) values
('7777BBB', 9000);

/* Insertamos vehículos - microbuses*/
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('0000CCC', 99.99, 'Mercedes Minibus', 'negro', 'electrico', '07/01/2021', 'preparado', 'D');
insert INTO microbus (matricula, numPlazas, medida) values
('0000CCC', 16, 15.75);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('1111CCC', 119.99, 'Mercedes Minibus Luxury', 'negro', 'diesel', '07/01/2021', 'preparado', 'D');
insert INTO microbus (matricula, numPlazas, medida) values
('1111CCC', 16, 15.75);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('2222CCC', 99.99, 'Mercedes Minibus', 'negro', 'gasolina', '07/01/2021', 'preparado', 'D');
insert INTO microbus (matricula, numPlazas, medida) values
('2222CCC', 16, 15.75);

insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('0000CCD', 89.99, 'Mercedes Microbus', 'negro', 'electrico', '07/01/2021', 'preparado', 'B');
insert INTO microbus (matricula, numPlazas, medida) values
('0000CCD', 8, 10);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('1111CCD', 99.99, 'Mercedes Microbus VIP', 'negro', 'diesel', '07/01/2021', 'preparado', 'B');
insert INTO microbus (matricula, numPlazas, medida) values
('1111CCD', 8, 10);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('2222CCD', 99.99, 'Mercedes Microbus VIP', 'negro', 'gasolina', '07/01/2021', 'preparado', 'B');
insert INTO microbus (matricula, numPlazas, medida) values
('2222CCD', 8, 10);

/* Insertamos vehículos - camiones*/
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('2222DDD', 99.99, 'Pegaso', 'blanco', 'diesel', '09/09/2020', 'preparado', 'E');
insert INTO camion (matricula, numRuedas, mma) values
('2222DDD', 8, 15000);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('3333DDD', 99.99, 'Pegaso', 'blanco', 'diesel', '09/09/2020', 'preparado', 'E');
insert INTO camion (matricula, numRuedas, mma) values
('3333DDD', 8, 15000);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('4444DDD', 99.99, 'Pegaso', 'blanco', 'diesel', '09/09/2020', 'preparado', 'E');
insert INTO camion (matricula, numRuedas, mma) values
('4444DDD', 8, 15000);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('5555DDD', 99.99, 'Iveco', 'negro', 'diesel', '09/09/2020', 'preparado', 'E');
insert INTO camion (matricula, numRuedas, mma) values
('5555DDD', 8, 15000);
insert INTO vehiculo (matricula, precioDia, marca, color, motor, fechaAdq, estado, carnet) values
('6666DDD', 99.99, 'Iveco', 'negro', 'gasolina', '09/09/2020', 'preparado', 'E');
insert INTO camion (matricula, numRuedas, mma) values
('6666DDD', 8, 15000);


/* creamos los empleados: gerentes, comerciales, administrativos, mecanicos */
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('87654321Z', 'pepa', 'navarro garcía', 'c/ pez, 14', '46185', 'pepa@mordor.es', '28/06/1957', 'gerente', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('76543210Y', 'luna', 'garcía navarro', 'c/ perro, 7', '46185', 'luna@mordor.es', '14/02/1973', 'gerente', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('65432109X', 'manuel', 'gonzález garmendia', 'c/ gato, 1', '46019', 'manuel@mordor.es', '09/09/1963', 'comercial', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('54321098W', 'joaquin', 'ferrando gabarda', 'c/ caballo, 17', '46019', 'joaquin@mordor.es', '11/11/1977', 'comercial', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('43210987V', 'vicente', 'cascales martínez', 'c/ león, 42', '46018', 'vicente@mordor.es', '09/11/1987', 'administrativo', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('32109876U', 'jose', 'hurtado fernández', 'c/ tigre, 55', '46018', 'jose@mordor.es', '10/04/1993', 'administrativo', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('21098765T', 'carmen', 'esteve gonzález', 'c/ zorro, 44', '46001', 'carmen@mordor.es', '27/04/1999', 'administrativo', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('10765432S', 'nuria', 'sanz taus', 'c/ lobo, 33', '46001', 'nuria@mordor.es', '04/08/2000', 'mecanico', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('07654321R', 'cindy', 'peris sancho', 'c/ buitre, 2', '46003', 'cindy@mordor.es', '03/07/1988', 'mecanico', encrypt_paswd.encrypt_val('1111'));
insert into empleado(DNI, nombre, apellidos, domicilio, CP, email, fechaNac, cargo, password) VALUES
('88843210Q', 'vanesa', 'codina andújar', 'c/ quebrantahuesos, 7', '46003', 'vanesa@mordor.es', '19/06/1983', 'mecanico', encrypt_paswd.encrypt_val('1111'));

/* en este momento inicial tenemos todos los vehículos preparados, con lo cual la tabla gestiónVehículo está vacía */

/* todas las facturas excepto las del mes de abril están en el histórico */
insert into factura(idFactura, fecha, importeBase, clienteId) values
(1973, '01/04/2021', 39.99*3,2);
insert into factura(idFactura, fecha, importeBase, clienteId) values
(1974, '01/04/2021', 59.99*4,1);
insert into factura(idFactura, fecha, importeBase, clienteId) values
(1975, '02/04/2021', 29.99*8,5);
insert into factura(idFactura, fecha, importeBase, clienteId) values
(1976, '02/04/2021', 49.99*3,19);

insert into alquiler(idFactura, matricula, fechaInicio, fechaFin, precio) values
(1973, '1111AAA', '01/04/2021', '03/04/2021', 39.99*3);
insert into alquiler(idFactura, matricula, fechaInicio, fechaFin, precio) values
(1974, '4444AAA', '01/04/2021', '04/04/2021', 59.99*4);
insert into alquiler(idFactura, matricula, fechaInicio, fechaFin, precio) values
(1975, '0000AAB', '02/04/2021', '05/04/2021', 29.99*4);
insert into alquiler(idFactura, matricula, fechaInicio, fechaFin, precio) values
(1975, '1111AAB', '02/04/2021', '05/04/2021', 29.99*4);
insert into alquiler(idFactura, matricula, fechaInicio, fechaFin, precio) values
(1976, '8888AAB', '02/04/2021', '04/04/2021', 49.99*3);
