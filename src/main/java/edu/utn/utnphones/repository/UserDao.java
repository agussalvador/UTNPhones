package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    @Procedure(procedureName = "sp_insert_user_client", outputParameterName = "pIdUser")
    Long addClient(@Param("pIdCity") Long idCity , @Param("pFirstname") String firstname, @Param("pLastname") String lastname, @Param("pDni") String dni, @Param("pTypeLine") String typeLine );

    @Query(value = "SELECT u FROM User u WHERE u.dni = :dni and u.password = :pwd")
    User getUserByDniAndPwd(@Param("dni") String dni, @Param("pwd") String password);

    @Query(value = "SELECT u FROM User u WHERE u.dni = :dni")
    Optional<User> findByDni(String dni);

    @Query(value = "SELECT u.* FROM users u WHERE u.user_role = 'client' and u.enabled = true" , nativeQuery = true)
    List<User> findAllClients();

}


