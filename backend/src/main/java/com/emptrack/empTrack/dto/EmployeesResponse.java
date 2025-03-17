package com.emptrack.empTrack.dto;

import java.time.LocalDate;

import com.emptrack.empTrack.domain.Employees;

public record EmployeesResponse(Integer empNo,
								String uuid,
								String name,
								LocalDate hireDate,
								Boolean status) {
	
	public static EmployeesResponse from(Employees employee) {
		return new EmployeesResponse(employee.getEmpNo(),
									 employee.getUuid(),
									 employee.getName(),
									 employee.getHireDate(),
									 employee.getStatus());
	}
}
