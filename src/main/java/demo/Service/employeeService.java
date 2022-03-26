package demo.Service;

import demo.entity.Employees;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface employeeService {

    public List<Employees> findAll();

    @Transactional(readOnly = true)
    Page<Employees> findAll(Pageable pageable);

    public void Save(Employees employee);

    public Employees findEmployee(Long id);

    public void Delete(Long id);
}
