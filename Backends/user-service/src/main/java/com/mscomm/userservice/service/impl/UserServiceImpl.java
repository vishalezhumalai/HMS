package com.mscomm.userservice.service.impl;
import com.mscomm.userservice.dto.ResponseDto;
import com.mscomm.userservice.entity.Patient;
import com.mscomm.userservice.service.*;
import com.mscomm.userservice.repository.*;
import org.springframework.web.client.RestTemplate;
import com.mscomm.userservice.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor

public class UserServiceImpl implements PatientService {

	 private PatientRepository userRepository;
	    private RestTemplate restTemplate;
	@Override
	public Patient saveUser(Patient user) {
		return userRepository.save(user);
	}

	@Override
	public ResponseDto getUser(Long userId) {

       ResponseDto responseDto = new ResponseDto();
       Patient user = userRepository.findById(userId).get();
       UserDto userDto = mapToUser(user);

       ResponseEntity<DepartmentDto> responseEntity = restTemplate
               .getForEntity("http://localhost:8082/api/blogs/" + user.getId(),
               DepartmentDto.class);
///////////for docker change localhost to container name
       DepartmentDto departmentDto = responseEntity.getBody();

       System.out.println(responseEntity.getStatusCode());

       responseDto.setUser(userDto);
       responseDto.setDepartment(departmentDto);

       return responseDto;
	}
	@Override
	public List<Patient> getallProducts()
	{
		 List<Patient> reports = new ArrayList<>();
		 userRepository.findAll().forEach(reports::add);
	     return reports;
	}
	private UserDto mapToUser(Patient user){
       UserDto userDto = new UserDto();
       userDto.setId(user.getId());
       userDto.setFirstName(user.getFirstName());
       userDto.setLastName(user.getLastName());
       userDto.setEmail(user.getEmail());
       userDto.setAge(user.getAge());
       userDto.setBloodgrp(user.getBloodgrp());
       return userDto;
   }

	@Override
	public Patient findById(long id) {
		return  userRepository.findById(id).get();
	}


}
