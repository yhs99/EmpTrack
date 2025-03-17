package com.emptrack.empTrack.dto;

import java.time.LocalDateTime;

import com.emptrack.empTrack.domain.AttendanceType;

public record AttendanceResponse(String name, Integer id, Integer empNo,
								 LocalDateTime attendanceDateTime, AttendanceType attendanceType) {

}