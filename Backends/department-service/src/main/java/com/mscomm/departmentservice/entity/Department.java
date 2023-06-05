package com.mscomm.departmentservice.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Department {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String Name;
	    private String specialisation;
	    private String experience;
	    
}
