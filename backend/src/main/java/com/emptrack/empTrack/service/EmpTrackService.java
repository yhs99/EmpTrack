package com.emptrack.empTrack.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emptrack.empTrack.Repository.EmpTrackRepository;
import com.emptrack.empTrack.domain.Employees;

@Service
public class EmpTrackService {

	private final EmpTrackRepository empTrackRepository;

	public EmpTrackService(EmpTrackRepository empTrackRepository) {
		this.empTrackRepository = empTrackRepository;
	}
	
	// 모든 직원 조회
    public List<Employees> getAllEmployees() {
        return empTrackRepository.findAll();
    }

    // 직원 등록 (UUID 자동 생성)
    public Employees createEmployee(Employees employee) {
        return empTrackRepository.save(employee);
    }

    // 특정 직원 조회 (empNo 기준)
    public Optional<Employees> getEmployeeById(Integer empNo) {
        return empTrackRepository.findById(empNo);
    }

    // 특정 직원 조회 (UUID 기준)
    public Optional<Employees> getEmployeeByUuid(String uuid) {
        return empTrackRepository.findByUuid(uuid);
    }

    // 직원 삭제
    public void deleteEmployee(Integer empNo) {
    	empTrackRepository.deleteById(empNo);
    }
    
    // 직원 근무 여부 수정
    @Transactional
    public int patchEmployeeStatus(Integer empNo, Boolean status) {
    	if(empTrackRepository.findById(empNo).isEmpty()) {
    		throw new IllegalArgumentException("유효하지 않은 직원입니다.");
    	}
    	return empTrackRepository.updateEmployeeStatus(empNo, status);
    }
}
