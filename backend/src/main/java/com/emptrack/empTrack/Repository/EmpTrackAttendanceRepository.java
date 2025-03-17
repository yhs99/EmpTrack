package com.emptrack.empTrack.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emptrack.empTrack.domain.Attendance;
import com.emptrack.empTrack.dto.AttendanceResponse;

@Repository
public interface EmpTrackAttendanceRepository extends JpaRepository<Attendance, Integer>{
	Optional<Attendance> findByEmpNo(Integer empNo);
	
	@Query(value = "SELECT attendance FROM Attendance attendance WHERE attendance.empNo = :empNo AND DATE(attendance.attendanceDateTime) = :date ORDER BY attendance.attendanceDateTime ASC")
	List<Attendance> findByEmpNoAndDate(@Param("empNo") Integer empNo,
										@Param("date") LocalDate date);
	
	@Query("""
			SELECT new com.emptrack.empTrack.dto.AttendanceResponse(employee.name, attendance.id, attendance.empNo, attendance.attendanceDateTime, attendance.attendanceType) 
			FROM Attendance attendance 
			JOIN Employees employee ON attendance.empNo = employee.empNo 
			WHERE 1=1
			AND (:uuid IS NULL OR employee.uuid = :uuid) 
			AND (:name IS NULL OR employee.name LIKE %:name%) 
			AND (:startDate IS NULL OR attendance.attendanceDateTime >= :startDate) 
			AND (:endDate IS NULL OR attendance.attendanceDateTime <= :endDate) 
			ORDER BY attendance.attendanceDateTime DESC
			""")
	List<AttendanceResponse> searchAttendanceByOptions(
			String uuid, String name,
			LocalDateTime startDate, LocalDateTime endDate);
	
}
