package edu.miu.cs.cs544.ea_ars.repository;


import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
