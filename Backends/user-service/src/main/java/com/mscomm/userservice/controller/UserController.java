package com.mscomm.userservice.controller;

import lombok.AllArgsConstructor;
import com.mscomm.userservice.dto.ResponseDto;
import com.mscomm.userservice.entity.Patient;
import com.mscomm.userservice.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api")
@AllArgsConstructor
public class UserController {
	  private PatientService patientService;

	    @PostMapping("/patients")
	    public ResponseEntity<Patient> saveUser(@RequestBody Patient user){
	    	Patient savedUser = patientService.saveUser(user);
	        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	    }
//user with dept details communication
	    @GetMapping("/patient/doctor/{id}")
	    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId){
	        ResponseDto responseDto = patientService.getUser(userId);
	        return ResponseEntity.ok(responseDto);
	    }
	    @GetMapping("/patient")
	    public ResponseEntity<List<Patient>> listAllUsers(){
	        List<Patient> userList = patientService.getallProducts();
	        return ResponseEntity.ok(userList);
	    }
	    @GetMapping("/patient/{id}")
		private Patient getBooks(@PathVariable("id") long id) {
			return patientService.findById(id);
		}
}
