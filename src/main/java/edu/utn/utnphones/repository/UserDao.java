package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {


    @Query(value = "SELECT u FROM User u WHERE u.dni = :dni and u.password = :pwd")
    public User getUserByDni(@Param("dni") String dni, @Param("pwd") String password)throws JpaSystemException;

    @Query(value = "SELECT u FROM User u WHERE u.dni = :dni and u.enabled = 1")
    public User getUserByDni(@Param("dni") String dni) throws JpaSystemException;

    @Query(value = "SELECT u FROM User u WHERE u.enabled = 1 and u.role = 'client' ")
    public List<User> getAll()throws JpaSystemException;



}


