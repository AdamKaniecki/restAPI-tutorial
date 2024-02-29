package pl.zajavka.api.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import pl.zajavka.api.dto.EmployeeDTO;
import pl.zajavka.api.dto.EmployeeMapper;
import pl.zajavka.api.dto.EmployeesDTO;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.entity.PetEntity;
import pl.zajavka.infrastructure.database.petstore.Pet;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;
import pl.zajavka.infrastructure.database.repository.PetRepository;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(EmployeesController.EMPLOYEES)
@AllArgsConstructor
public class EmployeesController {

    public static final String EMPLOYEES = "/employees";
    public static final String EMPLOYEE_ID = "/{employeeId}";
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    //    private PetDAO petDAO;
    private PetRepository petRepository;

    @GetMapping
    public EmployeesDTO allEmployees() {
        return EmployeesDTO.of(employeeRepository.findAll().stream()
                .map(employeeMapper::map)
                .toList());

    }

    @GetMapping(value = EMPLOYEE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDTO employeeDetailsAsJson(@PathVariable Integer employeeId){
        return employeeRepository.findById(employeeId)
                .map(employeeMapper::map)
                .orElseThrow(() -> new EntityNotFoundException( "Entity not found with id: [%s]".formatted(employeeId)));
    }


}
