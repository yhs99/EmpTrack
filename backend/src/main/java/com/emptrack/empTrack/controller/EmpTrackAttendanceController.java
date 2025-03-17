package com.emptrack.empTrack.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emptrack.empTrack.dto.AttendanceResponse;
import com.emptrack.empTrack.service.EmpTrackAttendanceService;
import com.emptrack.empTrack.service.EmpTrackService;


@RestController
@RequestMapping("/api/v1")
public class EmpTrackAttendanceController {

	private final EmpTrackAttendanceService empTrackAttendanceService;
	private final EmpTrackService empTrackService;
	
	public EmpTrackAttendanceController(EmpTrackAttendanceService empTrackAttendanceService,
										EmpTrackService empTrackService) {
		this.empTrackAttendanceService = empTrackAttendanceService;
		this.empTrackService = empTrackService;
	}
	
	@PostMapping("/attendance")
	public ResponseEntity<Map<String, ?>> recordAttendance(@RequestBody(required = true) Map<String, String> payload) {
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
	
	@GetMapping("/attendance")
	public ResponseEntity<List<AttendanceResponse>> searchAttendanceRecordByOptions(@RequestParam(required = false) String uuid,
																			@RequestParam(required = false) String name,
																			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
																			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate) {
		
		LocalDateTime startDateTime = (startDate != null) ? startDate.atStartOfDay() : null;
		LocalDateTime endDateTime = (endDate != null) ? endDate.atTime(23, 59, 59) : null;
		return ResponseEntity.ok(empTrackAttendanceService.searchAttendanceByOptions(uuid, name, startDateTime, endDateTime));
	}
	
}
