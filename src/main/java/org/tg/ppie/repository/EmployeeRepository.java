package org.tg.ppie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tg.ppie.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select concat(firstName,' ',lastName) from employees where id=:employeeId" ,nativeQuery = true)
    List<Object[]> getEmployeeFullName( @Param("employeeId") Long employeeId);


}