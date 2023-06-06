package net.javaguides.springboot.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")//doc name
	private String firstName;

	@Column(name = "last_name")// specialization
	private String lastName;
	
	@Column(name = "email_id")//desig
	private String emailId;
	
	@Column(name = "doj")//date of joining
	private Date doj;
	
	@Column(name = "experience")//experience
	private String experience;
	
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String emailId,Date doj,String experience) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.doj = doj;
		this.experience = experience;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
}
