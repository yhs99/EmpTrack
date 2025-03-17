package com.emptrack.empTrack.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emptrack.empTrack.domain.Employees;

@Repository
public interface EmpTrackRepository extends JpaRepository<Employees, Integer>{
    Optional<Employees> findByUuid(String uuid);
    
    @Transactional
    @Modifying
    @Query("UPDATE Employees employee "+
    	   "SET employee.status = :status "+
    	   "WHERE employee.empNo = :empNo")
    int updateEmployeeStatus(Integer empNo, Boolean status);
}
