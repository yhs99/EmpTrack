package com.emptrack.empTrack.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emptrack.empTrack.domain.Attendance;

@Repository
public interface EmpTrackAttendanceRepository extends JpaRepository<Attendance, Integer>{
	Optional<Attendance> findByEmpNo(Integer empNo);
}
