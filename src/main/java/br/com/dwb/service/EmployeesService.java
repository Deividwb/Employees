package br.com.dwb.service;

import br.com.dwb.entity.Employees;
import br.com.dwb.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    EmployeesRepository employeesRepository;

    public Employees save(Employees employees){
        return employeesRepository.save(employees);
    }

    public List<Employees> list(){
        return employeesRepository.findAll();
    }

    public Optional<Employees> findById(Long id){
        return employeesRepository.findById(id);
    }

    public void deleteById(Long id){
        employeesRepository.deleteById(id);
    }
}
