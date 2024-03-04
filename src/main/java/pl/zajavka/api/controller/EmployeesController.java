package pl.zajavka.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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
import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EmployeesController.EMPLOYEES)
@AllArgsConstructor
public class EmployeesController {

    public static final String EMPLOYEES = "/employees";
    public static final String EMPLOYEE_ID = "/{employeeId}";
    private static final String EMPLOYEE_ID_RESULT = "/%s";
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
    public EmployeeDTO employeeDetailsAsJson(@PathVariable Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .map(employeeMapper::map)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: [%s]".formatted(employeeId)));
    }

    @PostMapping
//    ResponseEntity stosuje się by ustawić STATUS ODPOWIEDZI na ten endpoint
    public ResponseEntity<EmployeeDTO> addEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO
    ) {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name(employeeDTO.getName())
                .surname(employeeDTO.getSurname())
                .salary(employeeDTO.getSalary())
                .phone(employeeDTO.getPhone())
                .email(employeeDTO.getEmail())
                .build();
        EmployeeEntity employeeCreated = employeeRepository.save(employeeEntity);
//        status created zwraca 201, i tu jest utworzony adres/path pod jakim ten zasób został utworzony
        return ResponseEntity.created(URI.create(EMPLOYEES + EMPLOYEE_ID_RESULT.formatted(employeeCreated.getEmployeeId())))
                .build();
    }

//curl dodający employee:
//curl -i -H "Content-Type: application/json" -X POST http://localhost:8600/zajavka/employees -d "{\"name\": \"Nowy\",\"surname\": \"Ziomek\",\"salary\": 15322.00,\"phone\": \"+48 555 555 555\",\"email\": \"nowy@poczta.com\"}"

//czyli ten kod za -d "{\#####"\} to jest wiadomość którą doklejamy do naszego żądania POST czyli
//    w tym przypadku jest to odniesienie do:     @RequestBody EmployeeDTO employeeDTO

//    jak nie napiszę nagłówka(-H "Content-Type: application/json") to dostanę odpowiedź Unsupported Mediaq Type
//    bo Serwer nie będzie wiedział w jakim formacie to dostał i zwróci status 415- czyli status który mówi o tym
//    że źle złożyłem zapytanie

//    jeśli wprowadzimy dane niezgodne z walidacją otrzymamy BAD REQUEST


//    trzeba ustawić ścieżkę:
    @PutMapping(EMPLOYEE_ID)
    public ResponseEntity<?> updateEmployee(
        @PathVariable Integer employeeId,
        @Valid @RequestBody  EmployeeDTO employeeDTO
    ){
            EmployeeEntity existingEmployee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Not found entity for Employee with id: [%s]".formatted(employeeId)));
       existingEmployee.setName(employeeDTO.getName());
       existingEmployee.setSurname(employeeDTO.getSurname());
       existingEmployee.setEmail(employeeDTO.getEmail());
       existingEmployee.setPhone(employeeDTO.getPhone());
       existingEmployee.setSalary(employeeDTO.getSalary());
       employeeRepository.save(existingEmployee);

       return ResponseEntity
               .ok()
               .build();
        }

// curl aktualizujący dane:
// curl -i -H "Content-Type: application/json" -X PUT http://localhost:8600/zajavka/employees/25 -d "{\"name\": \"Nowy\",\"surname\": \"Ziomczyslaw\",\"salary\": 15322.00,\"phone\": \"+48 555 555 555\",\"email\": \"nowy@poczta.com\"}"
// trzeba przekazać w ścieżce odpowiednie id

@DeleteMapping(EMPLOYEE_ID)
public ResponseEntity<?>deleteEmployee(
        @PathVariable Integer employeeId
){

    EmployeeEntity existingEmployee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Not found entity for Employee with id: [%s]".formatted(employeeId)));
employeeRepository.deleteById(existingEmployee.getEmployeeId());

    return ResponseEntity.noContent().build();
}
//curl na usunięcie employee:
//curl -i -X DELETE http://localhost:8600/zajavka/employees/28


    }

