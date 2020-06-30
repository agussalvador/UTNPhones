-- =================================================================
--   Trabajo Practico Integrador - UTNPhones 
-- =================================================================

USE utn_phones;

select count(cities.id_city) from cities;

DELIMITER $$
#drop FUNCTION randomPassword;
CREATE FUNCTION randomPassword()
RETURNS VARCHAR(128)
BEGIN
	SET @chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.';
	SET @charLen = length(@chars);

	SET @randomPassword = '';

	WHILE length(@randomPassword) < 12
		DO
		SET @randomPassword = concat(@randomPassword, substring(@chars,CEILING(RAND() * @charLen),1));
	END WHILE;

	RETURN @randomPassword ;
END $$ 

DELIMITER $$
#drop FUNCTION randomDni;
CREATE FUNCTION randomDni()
RETURNS VARCHAR(8)
BEGIN
	SET @chars = '0123456789';
	SET @charLen = length(@chars);

	SET @randomDni = '';

	WHILE length(@randomDni) < 8
		DO
		SET @randomDni = concat(@randomDni, substring(@chars,CEILING(RAND() * @charLen),1));
	END WHILE;

	RETURN @randomDni ;
END $$ 


select randomDni()

DELIMITER $$
#drop FUNCTION randomName;
CREATE FUNCTION randomName()
RETURNS VARCHAR(128)
BEGIN
	SET @chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
	SET @charLen = length(@chars);

	SET @randomPassword = '';

	WHILE length(@randomPassword) < 8
		DO
		SET @randomPassword = concat(@randomPassword, substring(@chars,CEILING(RAND() * @charLen),1));
	END WHILE;

	RETURN @randomPassword ;
END $$ 



DELIMITER $$
#drop FUNCTION randomCityId;
CREATE FUNCTION randomCityId()
RETURNS BIGINT
BEGIN
	DECLARE vCountCities int;
	select count(cities.id_city) into vCountCities from cities;

	SET @randomId = FLOOR( 1 + RAND() * vCountCities);

	RETURN @randomId;
END $$ 

DELIMITER $$
#drop FUNCTION randomType;
CREATE FUNCTION randomType()
RETURNS ENUM('mobile','home')
BEGIN

DECLARE random double;
DECLARE phoneType ENUM('mobile','home');
SET random = RAND();

IF random > 0.6 THEN
	SET phoneType = 'mobile';
ELSE
	SET phoneType = 'home';
END IF;

RETURN phoneType;
END $$  


#drop procedure create_random_user
-- DELIMITER $$
-- CREATE PROCEDURE create_random_user()
-- BEGIN

-- 	declare cont int;
--     set cont = 0;
-- 		/*no anda*/	
--     while( cont >= 5 ) do        
-- 		call sp_insert_user_client(randomCityID(), randomName(), randomName(), randomDni(), randomPassword(), randomType(),@IdUser);
-- 		set cont = cont + 1;
--     end while;

-- END $$

call create_random_user();

select * from users;






select * from users u inner join telephone_lines tl on u.id_user = tl.id_user;


select * from users;

