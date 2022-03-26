package demo.Repository;

import demo.entity.Employees;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface employeeRepository extends PagingAndSortingRepository<Employees, Long> {
}
