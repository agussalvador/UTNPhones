-- ==========================================================
--  Trabajo Practico Integrador - UTNPhones - VIEWS
-- ==========================================================
USE utn_phones;

-- =================================================================
--                         PHONELINES
-- =================================================================

#drop view v_phone_lines
CREATE VIEW v_phone_lines
AS
SELECT 	u.dni,
		tl.id_telephone_line as idPhoneLine, 
		concat(c.area_code,"-",tl.phone_number ) as phoneNumber,
		tl.type_line as typeLine,
        tl.enabled 
FROM telephone_lines tl 
INNER JOIN users u ON tl.id_user = u.id_user
INNER JOIN cities c ON u.id_city = c.id_city ;

select * from v_phone_lines;

-- =================================================================
--                            CLIENTS
-- =================================================================
#drop view v_clients;
CREATE VIEW v_clients
AS
SELECT 
		u.dni,
		concat(u.firstname,", ",u.lastname) as full_name,
        concat(c.city_name,", ",p.province_name) as city_province,
        count(tl.id_user) as count_phone_lines

FROM users u 
INNER JOIN cities c ON u.id_city = c.id_city
INNER JOIN provinces p ON p.id_province = c.id_province
INNER JOIN telephone_lines tl ON u.id_user = tl.id_user
WHERE u.user_role = 'client' and u.enabled = 1
GROUP BY (u.dni);

SELECT * FROM v_clients;
SELECT * FROM v_clients WHERE dni = "43148877" ;

-- =================================================================
--                            BILLS
-- =================================================================

select * from bills;

/* backoffice - get bills by dni user */
select b.* , u.dni from bills b
inner join telephone_lines tl on b.id_telephone_line = tl.id_telephone_line
inner join users u on tl.id_user = u.id_user
where dni = "41258963";


/*client - get bills by dni user between from to*/
select b.* from bills b
inner join telephone_lines tl on b.id_telephone_line = tl.id_telephone_line
where tl.id_user = 12 and  b.date_bill between "2019-05-01" and "2020-06-29" ;

-- =================================================================
--                             CALLS
-- =================================================================
#drop view v_calls
CREATE VIEW v_calls
AS
SELECT 
		u.dni,
		concat(co.area_code,"-",tlo.phone_number ) as phoneNumberOrigin,
        concat(cd.area_code,"-",tld.phone_number ) as phoneNumberDestination,
        co.city_name as cityOrigin,
        cd.city_name as cityDestination,
        cl.call_price as totalPrice,
        cl.duration,
        cl.call_date as date
        
FROM calls cl
INNER JOIN telephone_lines tlo ON cl.id_telephone_origin = tlo.id_telephone_line
INNER JOIN telephone_lines tld ON cl.id_telephone_destination = tld.id_telephone_line
INNER JOIN cities co ON cl.id_city_origin = co.id_city
INNER JOIN cities cd ON cl.id_city_destination = cd.id_city
INNER JOIN users u ON tlo.id_user = u.id_user
WHERE tlo.enabled = 1  ;

select dni , phoneNumberOrigin, phoneNumberDestination, cityOrigin, cityDestination, 
            duration, date, totalPrice from v_calls where dni = "43148877";


select * from v_calls where dni = 43143355;
select * from users;
select * from calls;
# llamadas por rango de fechas
select * from v_calls 
where dni = 43143355 and call_date between "2020-01-30" and "2020-02-30" ;


/*  TOP 10 destination most called by user.dni */
select cd.* from calls cl 
inner join cities cd on cl.id_city_destination = cd.id_city
inner join telephone_lines tlo on cl.id_telephone_origin = tlo.id_telephone_line
where tlo.id_user = 3
group by cd.id_city
order by count(cd.id_city ) desc 
limit 10;

-- =================================================================
--                             INDEX
-- =================================================================

create index idx_calls on calls (call_date) using btree; 

explain SELECT 
		u.dni,
		concat(co.area_code,"-",tlo.phone_number ) as phoneNumberOrigin,
        concat(cd.area_code,"-",tld.phone_number ) as phoneNumberDestination,
        co.city_name as cityOrigin,
        cd.city_name as cityDestination,
        cl.call_price as totalPrice,
        cl.duration,
        cl.call_date as date
        
FROM calls cl
INNER JOIN telephone_lines tlo ON cl.id_telephone_origin = tlo.id_telephone_line
INNER JOIN telephone_lines tld ON cl.id_telephone_destination = tld.id_telephone_line
INNER JOIN cities co ON cl.id_city_origin = co.id_city
INNER JOIN cities cd ON cl.id_city_destination = cd.id_city
INNER JOIN users u ON tlo.id_user = u.id_user
WHERE tlo.enabled = 1 and dni = 43143355 and call_date between "2020-01-30" and "2020-02-30"  ;



/*Parcial*/
	select 		
		dni,
		phone_number_destination as phoneLineDestination ,
		city_destination as cityDestination,
		call_date as dateCall
	from v_calls 
	where dni = 43143355
	
	group by (call_date)
	order by call_date desc
	limit 3;



































