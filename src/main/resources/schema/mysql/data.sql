-- ===================================================
--   Trabajo Practico Integrador - UTNPhones
-- ===================================================

USE utn_phones;

-- ==========================================================
--  Insert Data
-- ==========================================================
/*Insert provinces*/
INSERT into provinces(province_name) values('Buenos Aires'),('Catamarca'),('Chaco'),('Chubut'),
('Cordoba'),('Corrientes'),('Entre Rios'),('Formosa'),('Jujuy'),('La Pampa'),('La Rioja'),
('Mendoza'),('Misiones'),('Neuquen'),('Rio Negro'),('Salta'),('San Juan'),('San Luis'),
('Santa Cruz'),('Santa Fe'),('Santiago Del Estero'),('Tierra Del Fuego'),('Tucuman');

/*Insert cities*/
/* link de codigos de area https://es.wikipedia.org/wiki/N%C3%BAmeros_telef%C3%B3nicos_en_Argentina´*/
INSERT into cities(id_province,city_name,area_code) values
(1,'Ciudad Autonoma de Buenos Aires','0011'),(1,'Merlo','0220'),(1,'La Plata','0221'),(1,'Mar del Plata','0223'),
(1,'Pilar','0230'),(1,'Junin','0236'),(1,'Moreno','0237'),(1,'Tandil','0249'),(12,'San Rafael','0260'),
(12,'Mendoza','0261'),(12,'San Martin','0263'),(17,'San Juan','0264'),(18,'San Luis','0266'),
(4,'Trelew','0280'),(1,'Bahia Blanca','0291'),(15,'Bariloche','0294'),(4,'Comodoro Rivadavia','0297'),
(15,'General Roca','0298'),(14,'Neuquen','0299'),(1,'San Nicolas de los Arroyos','0336'),
(20,'Rosario','0341'),(20,'Santa Fe','0342'),(7,'Parana','0343'),(7,'Concordia','0345'),(1,'Belen de Escobar','0348'),
(5,'Cordoba','0351'),(5,'Villa Maria','0353'),(5,'Rio Cuarto','0358'),(3,'Resistencia','0362'),
(3,'Roque Saenz Peña','0364'),(8,'Formosa','0370'),(13,'Posadas','0376'),(6,'Corrientes','0379'),
(11,'La Rioja','0380'),(23,'San Miguel de Tucuman','0381'),(2,'San Fernando del Valle de Catamarca','0383'),
(21,'Santiago del Estero','0385'),(16,'Salta','0387'),(9,'San Salvador de Jujuy','0388'),(1,'Gonzales Catan','2202'),
(1,'Magdalena','2221'),(1,'Brandsen','2223'),(1,'Glew','2224'),(1,'Alejandro Korn','2225'),(1,'Cañuelas','2226'),
(1,'Lobos','2227'),(1,'Juan Maria Gutierrez','2229'),(1,'Chascomus','2241'),(1,'Lezama','2242'),
(1,'General Belgrano','2243'),(1,'Las Flores','2244'),(1,'Dolores','2245'),(1,'Santa Teresita','2246'),
(1,'San Clemente del Tuyu','2252'),(1,'Pinamar','2254'),(1,'Villa Gesell','2255'),(1,'Mar de Ajo','2257'),
(1,'Loberia','2261'),(1,'Necochea','2262'),(1,'Nicanor Olivera','2264'),(1,'Coronel Vidal','2265'),(1,'Balcarce','2266'),
(1,'General Juan Madariaga','2267'),(1,'Maipu','2268'),(1,'San Miguel del Monte','2271'),(1,'Juan Jose Almeyra','2272'),
(1,'Carmen de Areco','2273'),(1,'Carlos Spegazzini','2274'),(1,'Azul','2281'),(1,'Tapalque','2283'),(1,'Olavarria','2284'),
(1,'General La Madrid','2286'),(1,'Miramar ','2291'),(1,'Benito Juarez','2292'),(1,'Ayacucho','2296'),(1,'Rauch','2297'),
(10,'General Pico','2302'),(1,'San Carlos de Bolivar','2314'),(1,'Daireaux','2316'),(1,'Nueve de Julio','2317'),
(1,'Jose C Paz','2320'),(1,'Lujan','2323'),(1,'Mercedes','2324'),(1,'San Andres de Giles','2325'),(1,'San Antonio de Areco','2326'),
(10,'Realico','2331'),(10,'Quemu Quemu','2333'),(10,'Eduardo Castex','2334'),(10,'Caleufu','2335'),(5,'Huinca Renanco','2336'),
(1,'America','2337'),(10,'Victorica','2338'),(1,'Bragado','2342'),(1,'Norberto de la Riestra','2343'),(1,'Saladillo','2344'),
(1,'25 de Mayo','2345'),(1,'Chivilcoy','2346'),(1,'Chacabuco','2352'),(1,'General Arenales','2353'),(1,'Vedia','2354'),
(1,'Lincoln','2355'),(1,'General Pinto','2356'),(1,'Carlos Tejedor','2357'),(1,'Los Toldos','2358'),(1,'Trenque Lauquen','2392'),
(1,'Salazar','2393'),(1,'Tres Lomas','2394'),(12,'Tunuyan','2622'),(12,'Uspallata','2624'),(1,'General Alvear','2625'),
(12,'La Paz','2626'),(17,'Villa San Agustin','2646'),(17,'San Jose de Jachal','2647'),(17,'Calingasta','2648'),
(17,'San Francisco de Monte de Oro','2651'),(17,'La Toma','2655'),(17,'Tilisarao','2656'),(17,'Villa Mercedes','2657'),
(17,'Buena Esperenza','2658'),(22,'Ushuia','2209'),(19,'Rio Turbio','2902'),(4,'Rio Mayo','2903'),(15,'Viedma','2920'),
(15,'Rio Colorado','2931'),(15,'San Antonio Oeste','2934'),(15,'Ingeniero Jacobacci ','2940'),(14,'Zapala','2942'),
(4,'Esquel','2945'),(15,'Choele Choel','2946'),(14,'Chos Malal','2948'),(10,'General Acha','2952'),
(10,'Macachin','2953'),(10,'Santa Rosa','2954'),(19,'Puerto San Julian','2962'),(19,'Perito Moreno','2963'),
(22,'Rio Grande','2964'),(19,'Rio Gallegos','2966'),(14,'San Martin de los Andes','2972'),(20,'Rufino','3382'),
(5,'Laboulaye','3385'),(5,'Buchardo','3387'),(1,'General Villegas','3388'),(20,'Villa Constitucion','3400'),
(20,'El Trebol','3401'),(20,'Arroyo Seco','3402'),(20,'San Carlos Centro','3404'),(7,'Nogoya','3435'),(7,'Victoria','3436'),
(7,'La Paz','3437'),(7,'Bovril','3438'),(7,'Concepcion del Uruguay','3442'),(7,'Gualeguay','3444'),(7,'Rosario del Tala','3445'),
(7,'Gualeguaychu','3446'),(7,'Colon','3447'),(7,'Federal','3454'),(5,'Cruz Alta','3467'),(5,'Corral de Bustos','3468'),
(5,'Marcos Juarez','3472'),(5,'Dean Funes','3521'),(5,'Villa de Maria','3522'),(5,'Jesus Maria','3525'),(5,'Villa Carlos Paz','3541'),
(8,'Ingeniero Juarez','3711'),(8,'Las Lomitas','3715'),(8,'Comandante Fontana','3716'),(3,'Charadai','3721'),
(3,'General Jose de San Martin','3725'),(3,'Charata','3731'),(13,'Bernardo de Yrigoyen','3741'),(13,'El Dorado','3751'),
(13,'Obera','3755'),(13,'Puerto Iguazu','3757'),(6,'Paso de los Libres','3772'),(6,'Mercedes','3773'),(6,'Goya','3777'),
(6,'Saladas','3782'),(11,'Chepes','3821'),(11,'Chilecito','3825'),(11,'Chamical','3826'),(11,'Aimogasta','3827'),
(2,'Recreo','3832'),(2,'Andalgala','3835'),(2,'Tinogasta','3837'),(2,'Santa Maria','3838'),(21,'Monte Quemado','3841'),
(21,'quimili','3843'),(21,'Loreto','3845'),(21,'Termas de Rio Hondo','3858'),(23,'Trancas','3862'),(23,'Monteros','3863'),
(23,'Concepcion','3865'),(23,'Tafi del Valle','3867'),(16,'Cafayate','3868'),(16,'Tartagal','3873'),(16,'Oran','3878'),
(16,'San Jose de Metan','3876'),(9,'La Quiaca','3885'),(9,'Humahuaca','3887'),(9,'Libertador General San Martin','3886'),
(9,'Graneros','3891');

insert into users (id_city, firstname, lastname, dni, pwd, enabled, user_role )
value (4, "Marcelo", "Gallardo", "25000999", md5("admin") ,1, "Employee");

select  * from cities where id_province = 5;

