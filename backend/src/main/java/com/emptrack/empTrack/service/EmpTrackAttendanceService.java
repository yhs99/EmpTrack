package com.emptrack.empTrack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emptrack.empTrack.Repository.EmpTrackAttendanceRepository;
import com.emptrack.empTrack.Repository.EmpTrackRepository;
import com.emptrack.empTrack.domain.Attendance;
import com.emptrack.empTrack.domain.AttendanceType;
import com.emptrack.empTrack.domain.Employees;
import com.emptrack.empTrack.dto.AttendanceResponse;

@Service
public class EmpTrackAttendanceService {

	private final EmpTrackAttendanceRepository empTrackAttendanceRepository;
	private final EmpTrackRepository empTrackRepository;
	
	public EmpTrackAttendanceService(EmpTrackAttendanceRepository empTrackAttendanceRepository,
									 EmpTrackRepository empTrackRepository) {
		this.empTrackAttendanceRepository = empTrackAttendanceRepository;
		this.empTrackRepository = empTrackRepository;
	}
	
	public String recordAttendance(String uuid) {
		LocalDateTime now = LocalDateTime.now();
		LocalDate today = now.toLocalDate();
		Optional<Employees> employee = empTrackRepository.findByUuid(uuid);
		
		if (employee.isEmpty()) {
			throw new IllegalArgumentException("유효하지 않은 직원 카드입니다. 직원을 등록해주세요.");
		}
		
		Integer empNo = employee.get().getEmpNo();
		
		List<Attendance> todayRecords = empTrackAttendanceRepository.findByEmpNoAndDate(empNo, today);
		
		if(todayRecords.isEmpty()) {
			Attendance attendance = new Attendance();
			attendance.setEmpNo(empNo);
			attendance.setAttendanceType(AttendanceType.출근);
			empTrackAttendanceRepository.save(attendance);
			return "출근 기록이 저장되었습니다.";
		} else {
			Attendance lastRecord = todayRecords.get(todayRecords.size()-1);
			LocalDateTime lastCheckInTime = lastRecord.getAttendanceDateTime();
			
			if(lastCheckInTime.plusHours(1).isBefore(now)) {
				Attendance checkout = new Attendance();
				checkout.setEmpNo(empNo);
				checkout.setAttendanceType(AttendanceType.퇴근);
				empTrackAttendanceRepository.save(checkout);
				return "퇴근 기록이 저장되었습니다.";
			}else {
				return "이미 출근 기록이 있습니다. 한 시간 이후 다시 시도하세요.";
			}
		}
	}
	
	public List<AttendanceResponse> searchAttendanceByOptions(String uuid, String name, LocalDateTime startDate, LocalDateTime endDate) {
		return empTrackAttendanceRepository.searchAttendanceByOptions(uuid, name, startDate, endDate);
	}
}
