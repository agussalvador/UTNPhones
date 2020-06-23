package edu.utn.utnphones.repository;

import edu.utn.utnphones.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDao extends JpaRepository<Bill, Long> {



}
