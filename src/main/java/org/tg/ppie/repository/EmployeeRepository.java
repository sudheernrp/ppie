package org.tg.ppie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tg.ppie.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}