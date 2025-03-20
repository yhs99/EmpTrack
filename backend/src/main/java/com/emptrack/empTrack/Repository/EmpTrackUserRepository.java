package com.emptrack.empTrack.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emptrack.empTrack.domain.Users;

@Repository
public interface EmpTrackUserRepository extends JpaRepository<Users, Integer>{
	Optional<Users> findByUsername(String username);
}
