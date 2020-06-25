package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.Tariff;
import edu.utn.utnphones.dto.TariffRequestDto;
import edu.utn.utnphones.projection.ClientView;
import edu.utn.utnphones.projection.TariffView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;

import java.util.List;

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

    /*READ*/
    @Query(value = "SELECT * FROM v_tariffs " , nativeQuery = true)
    List<TariffView> getAllTariffs()throws JpaSystemException;

}
