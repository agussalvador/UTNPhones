package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneLineDao extends JpaRepository<PhoneLine,Integer> {


    @Procedure("sp_generate_phone_line_by_dni")
    public void generateNumber(@Param("pUserDni")String dni, @Param("pTypeLine") String typeLine ) throws JpaSystemException;

}

