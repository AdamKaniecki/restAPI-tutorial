package pl.zajavka.controller.api;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.zajavka.controller.dto.EmployeeDTO;
import pl.zajavka.controller.dto.EmployeeMapper;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;
import pl.zajavka.util.EmployeeDtoFixtures;
import pl.zajavka.util.EmployeeEntityFixtures;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EmployeesControllerMockitoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeesController employeesController;

    @Test
    void thatRetrievingEmployeeDetailsWorkCorrectly() {
//        given
        Integer employeeId = 10;

        EmployeeEntity employeeEntity = EmployeeEntityFixtures.someEmployee1();

        Mockito.when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        Mockito.when(employeeMapper.map(employeeEntity)).thenReturn(EmployeeDtoFixtures.someEmployeeDto1());
//        when

        EmployeeDTO result = employeesController.employeeDetailsAsJson(employeeId);// wywołanie tej metody z palca

//        then
        assertThat(result).isEqualTo(EmployeeDtoFixtures.someEmployeeDto1());

    }


}