package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u WHERE u.dni = :dni and u.password = :pwd")
    public User getByDni(@Param("dni") String dni, @Param("pwd") String password);

}


