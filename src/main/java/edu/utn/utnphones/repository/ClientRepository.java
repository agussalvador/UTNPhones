package edu.utn.utnphones.repository;

import edu.utn.utnphones.projection.ClientCallProjection;
import edu.utn.utnphones.projection.LastCalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Create User with PhoneLine
    @Procedure(procedureName = "sp_insert_user_client")
    public void addClient(@Param("pIdCity") Integer idCity, @Param("pFirstname") String firstname, @Param("pLastname") String lastname, @Param("pDni") String dni, @Param("pPwd") String pwd, @Param("pTypeLine") String typeLine );


    /*Parcial  06-01-2020  */
    @Query(value = "select \tdni,\n" +
            "\t\t\tphone_number_destination as phoneLineDestination,\n" +
            "\t\t\tcity_destination as cityDestination, \n" +
            "\t\t\tcall_date as dateCall\n" +
            "\tfrom v_calls \n" +
            "\twhere dni = :dni \n" +
            "\tgroup by (call_date)\n" +
            "\torder by call_date desc\n" +
            "\tlimit 3;", nativeQuery = true)
    public List<LastCalls> getLast3CallsByDni(@Param("dni") String dni );




    // Read Most Destination Called
    /* Simulacro Parcial - endpoint : nombre, apellido, dni y destino mas llamado(ciudad y/o numero)   */
    @Query(value = "\tselect \tdni,\n" +
            "\t\t\tfull_name as fullName,\n" +
            "\t\t\tcity_origin as cityOrigin,\n" +
            "\t\t\tphone_number_origin as phoneLineOrigin,\n" +
            "\t\t\tphone_number_destination as phoneLineDestination,\n" +
            "\t\t\tcity_destination as cityDestination, \n" +
            "\t\t\tcount(phone_number_destination) as countCalls\n" +
            "\tfrom v_calls \n" +
            "\twhere dni = :dni\n" +
            "\tgroup by (phoneLineDestination)\n" +
            "\torder by countCalls desc\n" +
            "\tlimit 1; ", nativeQuery = true)
    public ClientCallProjection getMostCalledDestinationByDni(@Param("dni") String dni );


    @Query(value = "\tselect \tdni,\n" +
            "\t\t\tfull_name as fullName,\n" +
            "\t\t\tcity_origin as cityOrigin,\n" +
            "\t\t\tphone_number_origin as phoneLineOrigin,\n" +
            "\t\t\tphone_number_destination as phoneLineDestination,\n" +
            "\t\t\tcity_destination as cityDestination, \n" +
            "\t\t\tcount(phone_number_destination) as countCalls\n" +
            "\tfrom v_calls \n" +
            "\twhere dni = :dni\n" +
            "\tgroup by (phoneLineDestination)\n" +
            "\torder by countCalls desc\n" +
            "\tlimit 10; ", nativeQuery = true)
    public List<ClientCallProjection> getTOP10MostCalledDestinationByDni(@Param("dni") String dni );

    //todo Update Client


    //todo Delete Client





}
