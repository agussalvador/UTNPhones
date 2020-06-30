-- ==========================================================
--  Trabajo Practico Integrador - UTNPhones - USERS
-- ==========================================================
USE utn_phones;
/*	
* WEB - APLICACION MOBILE: Donde cada usuario consultar sus llamadas y facturas.
* BACKOFFICE: Donde se van administrar los usuarios(clientes), lineas telefonicas y tarifas.
* INFRAESTRUCTURA: Sistema donde se ingresara información de llamadas a la base de datos.
*/
/* Sistema WEB - APP MOBILE */
create user 'web'@'127.0.0.1' identified by 'w3b' ; /*'mobile'@'10.0.0.%'*/
/*Permisos*/
grant select on utn_phones.bills to 'web'@'localhost';
grant select on utn_phones.calls to 'web'@'localhost';
grant select on utn_phones.users to 'web'@'localhost';
grant select on utn_phones.telephone_lines to 'web'@'localhost';

/* Sistema BackOffice */ 
create user 'backoffice'@'127.0.0.1' identified by 'b4ck0ffic3';
/*Permisos*/




/*Sistema Infraestructura*/
create user 'infra'@'127.0.0.1' identified by '1fr43s7ruc7ur4';
/*Permisos*/
/*tambien se puede implementar utilizando el DEFIINER en el PROCEDURE sp_insert_call*/
grant insert on utn_phones.calls to 'infra'@'127.0.0.1';




/* grant select, insert, update, delete on mail.users to 'web'@'10.0.1.%';*/
/*
grant select (first_name, last_name, username, pwd, id_city), 
insert (first_name, last_name, username, pwd, id_city) , 
update (first_name, last_name, username, pwd, id_city, enabled) on mail.users to 'web'@'10.0.1.%';

grant select (city_name, id_country),
insert (city_name, id_country), 
update (city_name, id_country) on mail.cities to 'web'@'10.0.1.%';

grant select (country_name),
insert (country_name), 
update (country_name) on mail.country to 'web'@'10.0.1.%';
*/