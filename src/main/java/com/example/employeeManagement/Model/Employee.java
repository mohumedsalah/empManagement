package com.example.employeeManagement.Model;

import com.example.employeeManagement.bo.Status;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	private String email;

	private Integer age;

	@Enumerated(EnumType.STRING)
	private Status status;

}
