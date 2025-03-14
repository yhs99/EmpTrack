package com.emptrack.empTrack.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emptrack.empTrack.domain.Attendance;

@Repository
public interface EmpTrackAttendanceRepository extends JpaRepository<Attendance, Integer>{
	Optional<Attendance> findByEmpNo(Integer empNo);
	
	@Query(value = "SELECT attendance FROM Attendance attendance WHERE attendance.empNo = :empNo AND DATE(attendance.attendanceDateTime) = :date ORDER BY attendance.attendanceDateTime ASC")
	List<Attendance> findByEmpNoAndDate(@Param("empNo") Integer empNo,@Param("date") LocalDate date);
}
