package com.emptrack.empTrack.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emptrack.empTrack.Repository.EmpTrackUserRepository;
import com.emptrack.empTrack.domain.Users;

@Service
public class EmpTrackUserService implements UserDetailsService{

	private final EmpTrackUserRepository empTrackUserRepository;
	
	public EmpTrackUserService(EmpTrackUserRepository empTrackUserRepository) {
		this.empTrackUserRepository = empTrackUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = empTrackUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("유저 정보를 찾을 수 없습니다."));
		return User.withUsername(user.getUsername()).password(user.getPassword()).roles((user.getRole() ? "admin" : "employee")).build();
	}

}
