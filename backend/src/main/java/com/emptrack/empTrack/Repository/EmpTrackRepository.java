package com.emptrack.empTrack.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emptrack.empTrack.domain.Employees;

@Repository
public interface EmpTrackRepository extends JpaRepository<Employees, Integer>{
    Optional<Employees> findByUuid(String uuid);
}
