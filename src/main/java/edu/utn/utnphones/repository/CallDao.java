package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.projection.CallView;
import edu.utn.utnphones.projection.MostCalledDestinationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;

import java.time.LocalDateTime;
import java.util.List;

public interface CallDao extends JpaRepository<Call, Long> {


    @Query(value = "select dni , phone_number_origin as phoneNumberOrigin," +
            " phone_number_destination as phoneNumberDestination," +
            " city_origin as cityOrigin, city_destination as cityDestination," +
            " duration, call_date as date " +
            "from v_calls where dni = :dni", nativeQuery = true)
    List<CallView> getCallsByDni(@Param("dni") String dni) throws JpaSystemException;


    @Query(value = "select dni , phone_number_origin as phoneNumberOrigin," +
            " phone_number_destination as phoneNumberDestination," +
            " city_origin as cityOrigin, city_destination as cityDestination," +
            " duration, call_date as date from v_calls" +
            " where dni = :dni and call_date between :from and :to ", nativeQuery = true)
    List<CallView>  getCallsByUserFilterByDate (String dni, LocalDateTime from, LocalDateTime to)  throws JpaSystemException;


    @Query(value = " call sp_view_TOP10_most_called_destination_by_dni(:dni)", nativeQuery = true)
    List<MostCalledDestinationView> getTOP10MostCalledDestination(@Param("dni") String dni) throws JpaSystemException;


}
