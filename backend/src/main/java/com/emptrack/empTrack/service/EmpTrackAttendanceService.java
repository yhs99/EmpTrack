package com.emptrack.empTrack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emptrack.empTrack.Repository.EmpTrackAttendanceRepository;
import com.emptrack.empTrack.Repository.EmpTrackRepository;
import com.emptrack.empTrack.domain.Attendance;
import com.emptrack.empTrack.domain.Employees;

@Service
public class EmpTrackAttendanceService {

	@Autowired
	private EmpTrackAttendanceRepository empTrackAttendanceRepository;
	@Autowired
	private EmpTrackRepository empTrackRepository;
	
	public Attendance recordAttendance(String uuid) {
		Optional<Employees> employee = empTrackRepository.findByUuid(uuid);
		System.out.println("uuid == " + uuid);
		System.out.println(employee.get().toString());
		if (employee.isEmpty()) {
			throw new IllegalArgumentException("유효하지 않은 직원입니다.");
		}
		
		Attendance attendance = new Attendance();
		attendance.setEmpNo(employee.get().getEmpNo());
		return empTrackAttendanceRepository.save(attendance);
	}
}
