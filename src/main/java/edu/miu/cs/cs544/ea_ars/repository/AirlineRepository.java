package edu.miu.cs.cs544.ea_ars.repository;

import edu.miu.cs.cs544.ea_ars.domain.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
