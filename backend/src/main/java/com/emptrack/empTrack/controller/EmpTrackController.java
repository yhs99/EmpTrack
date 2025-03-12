package com.emptrack.empTrack.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.emptrack.empTrack.domain.Employees;
import com.emptrack.empTrack.service.EmpTrackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class EmpTrackController {

	@Autowired
	private EmpTrackService empTrackService;
	
	@GetMapping("/employees")
	public ResponseEntity<Object> getEmployees() {
		return ResponseEntity.ok(empTrackService.getAllEmployees());
	}

	@GetMapping("/employees/empNo/{empNo}")
	public Optional<Employees> getEmployeeByEmpNo(@PathVariable Integer empNo) {
		System.out.println(empNo);
		return empTrackService.getEmployeeById(empNo);
	}
	
	@GetMapping("/employees/uuid/{uuid}")
	public Optional<Employees> getEmployeeByUuid(@PathVariable String uuid) {
		System.out.println(uuid);
		return empTrackService.getEmployeeByUuid(uuid);
	}
	
	@PostMapping("/employees")
	public Employees createEmployee(@RequestBody Employees employee) {
		System.out.println(employee);
		return empTrackService.createEmployee(employee);
	}
	
	
}
