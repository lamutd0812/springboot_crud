package huulam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import huulam.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
