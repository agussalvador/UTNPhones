package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityDao extends JpaRepository<City,Long> {

    @Query(value = "select cd.* from calls cl " +
            "inner join cities cd on cl.id_city_destination = cd.id_city " +
            "inner join telephone_lines tlo on cl.id_telephone_origin = tlo.id_telephone_line " +
            "where tlo.id_user = :id " +
            "group by cd.id_city " +
            "order by count(cd.id_city ) desc " +
            "limit 10 ", nativeQuery = true)
    List<City> getTOP10MostCalledDestination(@Param("id") Long id);

}
