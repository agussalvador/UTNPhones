package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityDao extends JpaRepository<City,Long> {


}
