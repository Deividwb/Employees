package br.com.dwb.controllerr;

import br.com.dwb.entity.Employees;
import br.com.dwb.service.EmployeesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    @Autowired
    ModelMapper modelMapper;

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    private Employees save(@RequestBody Employees employees){
        return employeesService.save(employees);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Employees> listAll(){
       return employeesService.list();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Employees findEmployeeById(@PathVariable("id") Long id){
       return employeesService.findById(id)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void replace(@PathVariable("id") Long  id,@RequestBody Employees employees){
       employeesService.findById(id)
               .map(employeeBase-> {
                 modelMapper.map(employees,employeeBase);
                 employeesService.save(employeeBase);
                 return Void.TYPE;
               }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteEmployeeById(@PathVariable("id") Long id){
       employeesService.findById(id)
               .map(employees -> {
                   employeesService.deleteById(employees.getId());
                   return Void.TYPE;
               }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));

    }
}
