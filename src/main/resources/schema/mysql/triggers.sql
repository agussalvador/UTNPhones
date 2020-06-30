-- =================================================================
--   Trabajo Practico Integrador - UTNPhones - TRIGGERS
-- =================================================================

USE utn_phones;

-- =================================================================
--  USERS		
-- =================================================================


/*Verifica que el dni contenga solo numeros, y que el nombre y apellido no contenga numeros.*/
#drop trigger TBI_user;
DELIMITER $$
CREATE TRIGGER TBI_user BEFORE INSERT ON users FOR EACH ROW 
BEGIN
	if( !IsNumeric(new.dni))then
		signal sqlstate '10001' 
		SET MESSAGE_TEXT = 'El dni tiene un formato incorrecto', 
		MYSQL_ERRNO = 4;
	end if;

	if(conteinsNumbers(new.firstname)) then
		signal sqlstate '10001' 
		SET MESSAGE_TEXT = 'El nombre tiene formato incorrecto', 
		MYSQL_ERRNO = 5;
	end if;

	if(conteinsNumbers(new.lastname)) then
		signal sqlstate '10001' 
		SET MESSAGE_TEXT = 'El apellido tiene formato incorrecto', 
		MYSQL_ERRNO = 6;
	end if;
END $$ ;




-- =================================================================
--  TARIFFS
-- =================================================================



/*Verifica que el precio de costo sea menor al precio cuando insertamos.*/
DELIMITER $$
#drop trigger TBI_tariff;
CREATE TRIGGER TBI_tariff BEFORE INSERT ON tariffs FOR EACH ROW 
BEGIN
	if(new.cost_price > new.price)then
		signal sqlstate '10001' 
		SET MESSAGE_TEXT = 'El costo no puede ser mayor al precio', 
		MYSQL_ERRNO = 2;
	end if;
END $$;

/*Verifica que no se inserte una tarifa que ya existe.*/
#drop trigger TBI_tariff_exist;
DELIMITER $$
CREATE TRIGGER TBI_tariff_exist BEFORE INSERT ON tariffs FOR EACH ROW 
BEGIN
	declare vIdTariff int;
	if(select id_tariff from tariffs where id_city_origin = new.id_city_origin and id_city_destination = new.id_city_destination)then
		signal sqlstate '10001' 
		SET MESSAGE_TEXT = 'No se puede agregar una tarifa que ya existe', 
		MYSQL_ERRNO = 1;
	end if;
END $$;

call sp_insert_tariffs(1,4,3,5);

select * from v_tariffs;
delete from tariffs where id_tariff = 5;


/*Verifica que el precio de costo sea menor al precio cuando updateamos.*/
DELIMITER $$
#drop trigger TBU_tariff;
CREATE TRIGGER TBU_tariff BEFORE UPDATE ON tariffs FOR EACH ROW 
BEGIN
	if(new.cost_price > new.price)then
		signal sqlstate '10001' 
		SET MESSAGE_TEXT = 'El costo no puede ser mayor al precio', 
		MYSQL_ERRNO = 3;
	end if;
END $$


select*from calls;

select * from bills;