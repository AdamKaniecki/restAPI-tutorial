package pl.zajavka.util;

import lombok.experimental.UtilityClass;
import pl.zajavka.controller.dto.EmployeeDTO;
import pl.zajavka.controller.dto.PetDTO;

import java.math.BigDecimal;

@UtilityClass
public class EmployeeDtoFixtures {

    public static EmployeeDTO someEmployeeDto1(){
        return EmployeeDTO.builder()
                .name("Agnieszka")
                .surname("Zajavkowa")
                .salary(new BigDecimal("5230.00"))
                .phone("+48 666 545 666")
                .email("zajavka@zajavka.pl")
                .build();
    }

    public static PetDTO somePet1(){
        return PetDTO.builder()
                .petId(1)
                .petSoreId(4L)
                .name("lion")
                .category("dog")
                .build();
    }
}
