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
import java.util.Optional;

@Repository
public interface PhoneLineDao extends JpaRepository<PhoneLine,Long> {


    @Transactional
    @Procedure(procedureName = "sp_generate_phone_line_by_dni", outputParameterName = "pIdPhone")
    Long generateNumber(@Param("pUserDni")String dni, @Param("pTypeLine") String typeLine);

    //delete
    @Transactional
    @Procedure(procedureName = "sp_suspend_phone_line", outputParameterName = "pIdPhone")
    Long  suspendPhoneLine(@Param("pIdPhoneLine") Long IdPhoneLine);

    //update
    @Transactional
    @Procedure(procedureName = "sp_active_phone_line", outputParameterName = "pIdPhone")
    Long  activePhoneLine(@Param("pIdPhoneLine") Long IdPhoneLine);




    @Query(value = "select p.* from v_phone_lines p WHERE p.dni = :dni ", nativeQuery = true)
    List<PhoneLineView>getPhoneLinesByDni(@Param("dni") String dni);

    @Query(value = "select p.* from telephone_lines p WHERE p.enabled = true", nativeQuery = true)
    List<PhoneLine> getPhoneLines();




}

