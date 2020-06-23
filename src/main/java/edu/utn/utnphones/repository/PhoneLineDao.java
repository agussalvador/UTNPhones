package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.projection.PhoneLineView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PhoneLineDao extends JpaRepository<PhoneLine,Long> {


    @Transactional
    @Procedure(procedureName = "sp_generate_phone_line_by_dni", outputParameterName = "pIdPhone")
    public Long generateNumber(@Param("pUserDni")String dni, @Param("pTypeLine") String typeLine ) throws JpaSystemException;

    @Query(value = "select full_number as phoneNumber, type_line as typeLine from v_phone_lines where v_dni = :dni " , nativeQuery = true)
    public List<PhoneLineView> getPhoneLinesByDni(@Param("dni") String dni ) throws JpaSystemException;


}

