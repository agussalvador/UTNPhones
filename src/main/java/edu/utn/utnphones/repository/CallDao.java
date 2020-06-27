package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.projection.CallView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CallDao extends JpaRepository<Call, Long> {

    @Procedure( procedureName = "sp_insert_call", outputParameterName = "pIdCall")
    Long saveCall( @Param("pPhoneNumberOrigin") String phoneLineOrigin, @Param("pPhoneNumberDestination") String phoneLineDestination , @Param("pDuration") Integer duration,  @Param("pDate") Date date );


    @Query(value = "select dni , phoneNumberOrigin, phoneNumberDestination, cityOrigin, cityDestination, " +
            "duration, date, totalPrice from v_calls where dni = :dni", nativeQuery = true)
    List<CallView> getCallsByDni(@Param("dni") String dni);

    @Query(value = "select dni , phoneNumberOrigin, phoneNumberDestination, cityOrigin, cityDestination, " +
            "duration, date, totalPrice from v_calls where dni = :dni and date between :from and :to ", nativeQuery = true)
    List<CallView>  getCallsByUserFilterByDate (String dni, LocalDateTime from, LocalDateTime to);


    /*Parcial - Laborarotio V -  01-06-2020  */
    /*
    * Profesor  21:16
    *   @Abel Acuña Endpoint que devuelva: las ultimas 3 lineas que llamo una persona X
    * */
    @Query(value = " select \t\t\n" +
            "\t\tdni,\n" +
            "\t\tphoneLineDestination ,\n" +
            "\t\tcityDestination,\n" +
            "\t\tdate\n" +
            "\t\tfrom v_calls \n" +
            "\t\twhere dni = :dni\n" +
            "\t\tgroup by (date)\n" +
            "\t\torder by date desc\n" +
            "\t\tlimit 3\n", nativeQuery = true)
    List<CallView> getLast3CallsByDni(@Param("dni") String dni );

}
