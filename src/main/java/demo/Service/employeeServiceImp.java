package demo.Service;

import demo.Repository.employeeRepository;
import demo.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class employeeServiceImp implements employeeService{

    @Autowired
    private employeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employees> findAll() {
        return (List<Employees>) employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Employees> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void Save(Employees employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public Employees findEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void Delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
