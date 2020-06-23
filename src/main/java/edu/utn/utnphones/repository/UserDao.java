package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.projection.ClientView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    @Procedure(procedureName = "sp_insert_user_client", outputParameterName = "pIdUser")
    Long addClient(@Param("pIdCity") Long idCity , @Param("pFirstname") String firstname, @Param("pLastname") String lastname, @Param("pDni") String dni, @Param("pPwd") String password, @Param("pTypeLine") String typeLine ) throws JpaSystemException;

    @Query(value = "SELECT u FROM User u WHERE u.dni = :dni and u.password = :pwd")
    User getUserByDni(@Param("dni") String dni, @Param("pwd") String password)throws JpaSystemException;

    @Query(value = "SELECT u FROM User u WHERE u.dni = :dni and u.enabled = 1")
    User getUserByDni(@Param("dni") String dni) throws JpaSystemException;

    @Query(value = "SELECT dni , full_name as fullNumber, city_province  as cityAndProvince, count_phone_lines as countPhoneLines FROM v_clients WHERE dni =:dni ", nativeQuery = true)
    ClientView getClientByDni(@Param("dni")String dni)throws JpaSystemException;

    @Query(value = "SELECT dni, full_name as fullNumber, city_province as cityAndProvince, count_phone_lines as countPhoneLines FROM v_clients " , nativeQuery = true)
    List<ClientView> getAllClients()throws JpaSystemException;





}


