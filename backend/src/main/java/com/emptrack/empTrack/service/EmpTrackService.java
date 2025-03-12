package com.emptrack.empTrack.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emptrack.empTrack.Repository.EmpTrackRepository;
import com.emptrack.empTrack.domain.Employees;

@Service
public class EmpTrackService {

	@Autowired
	private EmpTrackRepository employeesRepository;
	
	// 모든 직원 조회
    public List<Employees> getAllEmployees() {
        return employeesRepository.findAll();
    }

    // 직원 등록 (UUID 자동 생성)
    public Employees createEmployee(Employees employee) {
        employee.setUuid(UUID.randomUUID().toString()); // UUID 자동 생성
        System.out.println(employee);
        return employeesRepository.save(employee);
    }

    // 특정 직원 조회 (empNo 기준)
    public Optional<Employees> getEmployeeById(Integer empNo) {
        return employeesRepository.findById(empNo);
    }

    // 특정 직원 조회 (UUID 기준)
    public Optional<Employees> getEmployeeByUuid(String uuid) {
        return employeesRepository.findByUuid(uuid);
    }

    // 직원 삭제
    public void deleteEmployee(Integer empNo) {
    	employeesRepository.deleteById(empNo);
    }
}
