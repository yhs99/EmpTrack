package com.emptrack.empTrack.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.emptrack.empTrack.domain.Attendance;
import com.emptrack.empTrack.service.EmpTrackAttendanceService;
import com.emptrack.empTrack.service.EmpTrackService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
public class EmpTrackAttendanceController {

	@Autowired
	private EmpTrackAttendanceService empTrackAttendanceService;
	
	@Autowired
	private EmpTrackService empTrackService;
	
	@PostMapping("/attendance")
	public ResponseEntity<?> recordAttendance(@RequestParam String uuid) {
		System.out.println(uuid);
		if(uuid.isEmpty() || uuid == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "유효하지 않은 UUID 입니다."));
		}
		Map<String, Object> result = new HashMap<>();
		Attendance attendance = empTrackAttendanceService.recordAttendance(uuid);
		result.put("message", "출퇴근 기록이 저장되었습니다.");
		result.put("name", empTrackService.getEmployeeByUuid(uuid).get().getName());
		result.put("requestDateTime", attendance.getAttendanceDateTime());
		
		return ResponseEntity.ok(result);
	}
	
}
