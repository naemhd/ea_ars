package edu.miu.cs.cs544.ea_ars.repository;

import edu.miu.cs.cs544.ea_ars.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}
