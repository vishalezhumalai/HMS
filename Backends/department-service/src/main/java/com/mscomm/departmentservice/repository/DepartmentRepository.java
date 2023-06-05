package com.mscomm.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mscomm.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
