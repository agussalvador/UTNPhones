package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface TariffDao extends JpaRepository <Tariff, Integer> {

    /*CREATE*/
    @Procedure(procedureName = "sp_insert_tariff")
    public void create(@Param("pId_city_origin") Long idCityOrigin,
                         @Param("pId_city_destination") Long idCityDestination,
                         @Param("pCost_price") Double costPrice,
                         @Param("pPrice") Double price);

    /*UPDATE*/
    @Procedure(procedureName = "sp_update_tariff")
    public void update(@Param("pId_city_origin") Long idCityOrigin,
                       @Param("pId_city_destination") Long idCityDestination,
                       @Param("pCost_price") Double costPrice,
                       @Param("pPrice") Double price);

    /*DELETE*/
    @Procedure(procedureName = "sp_delete_tariff")
    public  void delete(@Param("pId_city_origin") Integer idCityOrigin,
                        @Param("pId_city_destination") Integer idCityDestination);

}
