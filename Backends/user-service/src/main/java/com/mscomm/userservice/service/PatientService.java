package com.mscomm.userservice.service;
import com.mscomm.userservice.entity.*;
import java.util.List;
import com.mscomm.userservice.dto.ResponseDto;
public interface PatientService {
	Patient saveUser(Patient user);
    ResponseDto getUser(Long userId);
	List<Patient> getallProducts();
	Patient findById(long id);
}
