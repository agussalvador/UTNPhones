package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City,Long> {
/*
    @Query(value = "SELECT c FROM City c WHERE c.id_city = :id")
    Optional<City> findById(@Param("id") Long cityId);
*/

}
