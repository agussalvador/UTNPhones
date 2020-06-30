-- ======================================================================
--   Trabajo Practico Integrador - UTNPhones - PROCEDURES AND FUNCTIONS
-- ======================================================================

USE utn_phones;
-- =================================================================
--  PHONELINES
-- =================================================================
#drop function getPhoneLineRandom;
DELIMITER $$
CREATE FUNCTION getPhoneLineRandom ()
RETURNS VARCHAR (8)
DETERMINISTIC
BEGIN
    DECLARE vRandom varchar(8);
	SELECT FLOOR(RAND()*(6999999 - 4700001)+4700000) INTO vRandom;
    RETURN vRandom;
END $$;

#drop procedure sp_generate_phone_line;
DELIMITER $$
CREATE PROCEDURE sp_generate_phone_line( IN pIdUser BIGINT, IN pTypeLine VARCHAR(10))
BEGIN
    DECLARE vPhoneNumber varchar(8);
	SET vPhoneNumber = getPhoneLineRandom();
	INSERT INTO telephone_lines (id_user, phone_number, type_line) 
    VALUE (pIdUser,vPhoneNumber, pTypeLine);
END $$;

DELIMITER $$
CREATE PROCEDURE sp_generate_phone_line_by_dni(IN pDni VARCHAR(20), IN pTypeLine VARCHAR(10), OUT pIdPhone BIGINT )
BEGIN
	DECLARE vIdUser INT;
    SELECT u.id_user INTO vIdUser FROM users u WHERE u.dni = pDni;
	CALL sp_generate_phone_line(vIdUser, pTypeLine);
    SET pIdPhone = last_insert_id();
END $$;

# eliminar, dar de baja, suspender  linea
#drop procedure sp_suspend_phone_line
DELIMITER $$
CREATE PROCEDURE sp_suspend_phone_line( IN pIdPhoneLine BIGINT)
BEGIN
	UPDATE telephone_lines 
    SET enabled = false
    WHERE id_telephone_line = pIdPhoneLine;
END $$;


DELIMITER $$
CREATE PROCEDURE sp_active_phone_line( pIdPhoneLine BIGINT, OUT pIdPhone BIGINT )
BEGIN
	UPDATE telephone_lines 
    SET enabled = true
    WHERE id_telephone_line = pIdPhoneLine;
    SET pIdPhone = pIdPhoneLine;
END $$;

-- =================================================================
--  USERS
-- =================================================================
#set autocommit = 0;
/* Este procedimiento realiza una insercion de un usuario a la tabla y le genera automaticamente una linea telefonica */
#drop  procedure sp_insert_user_client;
DELIMITER $$
CREATE PROCEDURE sp_insert_user_client( pIdCity BIGINT, pFirstname VARCHAR(50), pLastname VARCHAR(50), pDni VARCHAR(20), pPwd VARCHAR(128), pTypeLine VARCHAR(10) ,OUT pIdUser BIGINT )
BEGIN

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		SELECT MESSAGE_TEXT AS ErrorMessage; 
		ROLLBACK;
	END;

    START TRANSACTION;
		INSERT INTO users(id_city, firstname, lastname, dni, pwd, user_role) 
		VALUE (pIdCity, pFirstname, pLastname, pDni, pPwd, 'client');
		SET pIdUser = last_insert_id();
		call sp_generate_phone_line(pIdUser, pTypeLine);
	COMMIT;
END $$;

select * from users;

#call sp_insert_user_client(3, "Abel", "Acuña", "38685449", md5("1234abel"), 'mobile', @IdUser);



# UPDATE USER - *todo
#drop  procedure sp_update_user;
DELIMITER $$
CREATE PROCEDURE sp_update_user (pId_city int, pFirstname VARCHAR(50), pLastname VARCHAR(50), pDni VARCHAR(20), OUT pIdUser BIGINT)
BEGIN
	START TRANSACTION;
		UPDATE users 
		SET id_city = pId_city,
			firstname = pFirstname,
			lastname = pLastname
		 WHERE   dni = pDni;
		 SELECT id_user INTO pIdUser FROM users WHERE dni = pDni;
	COMMIT;
END $$;



select * from users;
select * from v_phone_lines;

#DELETE
#drop  procedure sp_delete_user;
DELIMITER $$
CREATE PROCEDURE sp_delete_user (pDni VARCHAR(20))
BEGIN
	DECLARE vIdUser BIGINT;
	START TRANSACTION;
		UPDATE users 
		SET users.enabled = 0
		WHERE users.dni = pDni;
		
		SELECT u.id_user INTO vIdUser FROM users u WHERE u.dni = pDni;
		
		UPDATE telephone_lines
		SET telephone_lines.enabled = 0
		WHERE telephone_lines.id_user = vIdUser;
	COMMIT;
END $$;

-- =================================================================
--  TARIFFS
-- =================================================================

#CREATE
#drop  procedure sp_insert_tariff;
DELIMITER $$
CREATE PROCEDURE sp_insert_tariff (pId_city_origin BIGINT, pId_city_destination BIGINT, pCost_price DOUBLE, pPrice DOUBLE )
BEGIN
    START TRANSACTION;
        insert into tariffs(id_city_origin, id_city_destination, cost_price, price) 
            VALUES (pId_city_origin, pId_city_destination, pCost_price, pPrice);
        if(pId_city_origin != pId_city_destination)then
        insert into tariffs(id_city_origin, id_city_destination, cost_price, price) 
            VALUES (pId_city_destination,pId_city_origin, pCost_price, pPrice);
        end if;
    COMMIT;
END $$;

#UPDATE
DELIMITER $$
#drop  procedure sp_update_tariff;
CREATE PROCEDURE sp_update_tariff (pId_city_origin int, pId_city_destination int, pCost_price DOUBLE, pPrice DOUBLE)
BEGIN
    START TRANSACTION;
        update tariffs 
        set cost_price = pCost_price,
            price = pPrice
        WHERE id_city_origin = pId_city_origin and id_city_destination = pId_city_destination;

        if(pId_city_origin != pId_city_destination)then
        update tariffs 
        set cost_price = pCost_price,
            price = pPrice
        WHERE id_city_origin = pId_city_destination and id_city_destination = pId_city_origin;
        end if;
    COMMIT;
END $$;
    
#DELETE
DELIMITER $$
#drop  procedure sp_delete_tariff;
CREATE PROCEDURE sp_delete_tariff (pId_city_origin int, pId_city_destination int)
BEGIN
    START TRANSACTION;
        DELETE FROM tariffs
        WHERE id_city_origin = pId_city_origin and id_city_destination = pId_city_destination;
        if(pId_city_origin != pId_city_destination)then
        DELETE FROM tariffs
        WHERE id_city_origin = pId_city_destination and id_city_destination = pId_city_origin;
        end if;
    COMMIT;
END $$



-- =================================================================
--  CALLS
-- =================================================================

/* INFRAESTRUCTURA :  Ingresar llamada a la base de datos */
#drop procedure sp_insert_call
DELIMITER $$
CREATE PROCEDURE sp_insert_call( pPhoneNumberOrigin varchar(25), pPhoneNumberDestination varchar(25), pDuration int, pDate datetime , out pIdCall int)
BEGIN 
    # traer nmero de ciudad
    # calcular tarifa
	DECLARE vAreaCodeOrigin varchar(5);
    DECLARE vAreaCodeDestination varchar(5);
    
    DECLARE vIdCityOrigin int;
    DECLARE vIdCityDestination int;
    
    DECLARE vIdPhoneNumberOrigin int;
    DECLARE vIdPhoneNumberDestination int;
    
    DECLARE vTariffPrice double;
    DECLARE vTariffCostPrice double;
    DECLARE vTotalPrice double;
    
    START TRANSACTION;
    #Get area codes from numbers
	set vAreaCodeOrigin = f_getAreaCode(pPhoneNumberOrigin);
    set vAreaCodeDestination = f_getAreaCode(pPhoneNumberDestination);
    
    #Get id City by area code
    set vIdCityOrigin = f_getIdCityByAreaCode(vAreaCodeOrigin);
    set vIdCityDestination = f_getIdCityByAreaCode(vAreaCodeDestination);
	
    #Get id phone number from phone number
    SELECT id_telephone_line INTO vIdPhoneNumberOrigin FROM telephone_lines WHERE phone_number = f_getPhoneLineFromCompleteNumber(pPhoneNumberOrigin);
    SELECT id_telephone_line INTO vIdPhoneNumberDestination FROM telephone_lines WHERE phone_number = f_getPhoneLineFromCompleteNumber(pPhoneNumberDestination);
    
    #Get price and cost price by id city origin and id city destination
    set vTariffPrice = f_getTariffPriceByCity(vIdCityOrigin,vIdCityDestination);
	set vTariffCostPrice = f_getTariffCostPriceByCity(vIdCityOrigin,vIdCityDestination);
    set vTotalPrice = vTariffPrice * pDuration;
    
    INSERT INTO calls (id_telephone_origin, id_telephone_destination, id_city_origin, id_city_destination,id_bill,
		duration, cost_price, call_price, call_date) 
    VALUE (vIdPhoneNumberOrigin, vIdPhoneNumberDestination, vIdCityOrigin, vIdCityDestination, null, pDuration, 
		vTariffCostPrice, vTotalPrice, pDate);
	SET pIdCall = last_insert_id();
    
    COMMIT;
END
$$

-- =================================================================
--  FUNCTIONS
-- =================================================================



/*Verifica que el string solo contenga enteros*/
CREATE FUNCTION isNumeric (word varchar(1024)) 
RETURNS tinyint deterministic
RETURN word REGEXP '^-?[0-9]+$';

/*Verifica que el string no contenga enteros*/
CREATE FUNCTION conteinsNumbers ( word varchar(1024)) 
RETURNS tinyint deterministic
RETURN word REGEXP '-?[0-9]';


#drop function f_getAreaCode
# hacer una funcion para obtener el codigo de area de un numero */
DELIMITER $$
CREATE FUNCTION f_getAreaCode (pPhoneNumber varchar(25))
returns varchar(5)
deterministic
BEGIN
	declare vAreaCode varchar(5);
    set vAreaCode = substring(pPhoneNumber,1,4);
	return vAreaCode;
END
$$
/*select f_getAreaCode('0011-5634979');*/

#drop function f_getIdCityByAreaCode
#hacer funcion que ingrese numero y me retorne el id ciudad -> obtener tarifa*/
DELIMITER $$
CREATE FUNCTION f_getIdCityByAreaCode (pCodeArea varchar(5))
returns int
deterministic
BEGIN
	DECLARE vIdCity int;
	
	SELECT c.id_city INTO vIdCity 
    FROM cities c
    WHERE c.area_code = pCodeArea;
    
   return vIdCity;
END
$$
/*select f_getIdCityByAreaCode ('0223');
select * from cities;*/

#drop function f_getPhoneLineFromCompleteNumber
# hacer una funcion para obtener el numero de telefono sin el codigo de area*/
DELIMITER $$
CREATE FUNCTION f_getPhoneLineFromCompleteNumber (pPhoneNumber varchar(25))
returns varchar(20)
deterministic
BEGIN
	declare vPhoneNumber varchar(20);
    set vPhoneNumber = substring(pPhoneNumber,5,8);
	return vPhoneNumber;
END
$$

#select f_getPhoneLineFromCompleteNumber ('00115283734');


#drop function f_getTariffPriceByCity
#hacer funcion que ingrese 2 id city y obtenga tarifa*/
DELIMITER $$
CREATE FUNCTION f_getTariffPriceByCity (pIdCityOrigin int,pIdCityDestination int)
returns double
BEGIN
	DECLARE vTariffPrice double;
	
	SELECT t.price INTO vTariffPrice 
    FROM tariffs t
    WHERE t.id_city_origin = pIdCityOrigin and t.id_city_destination = pIdCityDestination;
    
   return vTariffPrice;
END
$$
/*select f_getTariffPriceByCity (2,4 )
select * from tariffs;*/


#drop function f_getTariffCostPriceByCity
#hacer funcion que ingrese 2 id city y obtenga el costo de tarifa*/
DELIMITER $$
CREATE FUNCTION f_getTariffCostPriceByCity (pIdCityOrigin int,pIdCityDestination int)
returns double
BEGIN
	DECLARE vTariffCostPrice double;
	
	SELECT t.cost_price INTO vTariffCostPrice 
    FROM tariffs t
    WHERE t.id_city_origin = pIdCityOrigin and t.id_city_destination = pIdCityDestination;
    
   return vTariffCostPrice;
END
$$


-- =================================================================
--  BILLS
-- =================================================================

#EVENTO para crear la factura una vez por mes.
#DROP EVENT insertion_bills;
SET GLOBAL event_scheduler = ON;
CREATE EVENT insertion_bills
ON SCHEDULE EVERY 1 MONTH STARTS '2020-06-01 09:30:00'
ON COMPLETION PRESERVE
DO
call sp_create_all_bill()

#Store procedure que crea todas las facturas 
#drop procedure sp_create_all_bill;
DELIMITER $$
CREATE PROCEDURE sp_create_all_bill()
BEGIN
	declare vId_phone_line int;
    #Declaro el cursos para recorrer todas las lineas telefonicas
	declare phone_lines_cursor cursor for 
    select id_telephone_line from telephone_lines tl;
    
    #Declaración de un manejador de error tipo NOT FOUND
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET @done = TRUE;

	#Se abre el cursor. Al abrir el cursor este sitúa un puntero a la primera fila del resultado de la consulta.
	OPEN phone_lines_cursor;
    #Empieza el bucle de lectura
	loop1: LOOP
	#Se guarda el resultado en la variable, hay una variable y un campo en el SELECT de la declaración del cursor
	FETCH phone_lines_cursor INTO vId_phone_line;
    #Llamo al store procedure para crear la factura de esa linea telefonica
    call sp_create_bill(vId_phone_line);

	#Se sale del bucle cuando no hay elementos por recorrer
	IF @done THEN
		LEAVE loop1;
	END IF;
	END LOOP loop1;
	#Se cierra el cursor
	CLOSE phone_lines_cursor;
    
END $$;

#drop procedure sp_create_bill;
DELIMITER $$
CREATE PROCEDURE sp_create_bill( pId_telephone_line int)
BEGIN
    DECLARE vCount_calls int;
    DECLARE vCost_price double;
    DECLARE vTotal_price double;
    DECLARE vDate_bill double;
    DECLARE vExpirate_date double;
       
    #Cuento la cantidad de llamadas de esa linea que estan sin facturar
    select count(*) into vCount_calls from calls c where c.id_telephone_origin = pId_telephone_line and c.billed = 0 ;
    
    IF vCount_calls > 0 then
		#Calculo los costos y las fechas
		select sum(cost_price*duration) into vCost_price from calls c where billed = 0 and c.id_telephone_origin = pId_telephone_line;
        select sum(call_price) into vTotal_price from calls c where c.billed = 0 and c.id_telephone_origin = pId_telephone_line;
        set vDate_bill = now();
        set vExpirate_date = DATE_ADD(NOW(),INTERVAL 15 DAY);
		
        #Inserto la nueva factura
		INSERT INTO bills (id_telephone_line, count_calls, cost_price, total_price, date_bill, expirate_date) 
		VALUE (pId_telephone_line, vCount_calls, vCost_price, vTotal_price, vDate_bill, vExpirate_date);
        
        #Actualizo las llamadas que no estaban facturadas y le asigno la factura creada
        UPDATE calls 
		SET id_bill = last_insert_id(),
			billed = 1
		WHERE id_telephone_origin = pId_telephone_line and billed = 0;
    END IF;

END $$;

