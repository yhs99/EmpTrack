package com.emptrack.empTrack.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emptrack.empTrack.domain.Employees;
import com.emptrack.empTrack.dto.EmployeesResponse;
import com.emptrack.empTrack.service.EmpTrackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class EmpTrackController {

	private final EmpTrackService empTrackService;
	
	public EmpTrackController(EmpTrackService empTrackService) {
		this.empTrackService = empTrackService;
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employees>> getEmployees() {
		return ResponseEntity.ok(empTrackService.getAllEmployees());
	}

	@GetMapping("/employees/empNo/{empNo}")
	public ResponseEntity<Optional<Employees>> getEmployeeByEmpNo(@PathVariable Integer empNo) {
		System.out.println(empNo);
		return ResponseEntity.ok(empTrackService.getEmployeeById(empNo));
	}
	
	@GetMapping("/employees/uuid/{uuid}")
	public ResponseEntity<Optional<Employees>> getEmployeeByUuid(@PathVariable String uuid) {
		System.out.println(uuid);
		return ResponseEntity.ok(empTrackService.getEmployeeByUuid(uuid));
	}
	
	@PostMapping("/employees")
	public ResponseEntity<EmployeesResponse> createEmployee(@RequestBody Employees employee) {
		return ResponseEntity.ok(EmployeesResponse.from(empTrackService.createEmployee(employee)));
	}
	
	@PatchMapping("/employees/{empNo}")
	public ResponseEntity<?> patchEmployeeStatus(@PathVariable Integer empNo, @RequestBody(required = true) Map<String, Boolean> payload) {
		Boolean status = payload.get("status");
		empTrackService.patchEmployeeStatus(empNo, status);
		return ResponseEntity.ok(Map.of("message", "변경되었습니다."));
	}
}
