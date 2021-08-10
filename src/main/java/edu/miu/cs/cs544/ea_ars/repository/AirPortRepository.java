package edu.miu.cs.cs544.ea_ars.repository;

import edu.miu.cs.cs544.ea_ars.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirPortRepository extends JpaRepository<Airport,Long> {

}
