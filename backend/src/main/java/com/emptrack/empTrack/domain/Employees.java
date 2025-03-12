package com.emptrack.empTrack.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employees {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empNo")
	private Integer empNo;
	
	@Column(name = "uuid", nullable = true, unique = true, length = 255)
    private String uuid; // UUID (고유 식별자)
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	@Column(name = "hireDate", nullable = true)
	private LocalDate hireDate = LocalDate.now();
	
	@Column(name = "status", nullable = true)
	private Boolean status = true;
	
	
}
