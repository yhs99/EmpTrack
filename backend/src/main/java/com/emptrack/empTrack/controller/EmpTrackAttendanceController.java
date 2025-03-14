package com.emptrack.empTrack.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emptrack.empTrack.service.EmpTrackAttendanceService;
import com.emptrack.empTrack.service.EmpTrackService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class EmpTrackAttendanceController {

	@Autowired
	private EmpTrackAttendanceService empTrackAttendanceService;
	
	@Autowired
	private EmpTrackService empTrackService;
	
	@PostMapping("/attendance")
	public ResponseEntity<?> recordAttendance(@RequestBody(required = true) Map<String, String> payload) {
		System.out.println(payload.get("uuid"));
		String uuid = payload.get("uuid");
		if(uuid.isEmpty() || uuid == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "유효하지 않은 카드 입니다."));
		}
		Map<String, Object> result = new HashMap<>();
		String message = empTrackAttendanceService.recordAttendance(uuid);
		result.put("message", message);
		result.put("name", empTrackService.getEmployeeByUuid(uuid).get().getName());
		result.put("requestDateTime", LocalDateTime.now());
		
		return ResponseEntity.ok(result);
	}
	
}
