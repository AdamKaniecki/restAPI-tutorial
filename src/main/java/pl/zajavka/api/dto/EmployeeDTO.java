package pl.zajavka.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With

public class EmployeeDTO {

    private Integer employeeId;
    private String name;
    private String surname;
    private BigDecimal salary;
    private String phone;
    private String email;

}
