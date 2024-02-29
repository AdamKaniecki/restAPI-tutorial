package pl.zajavka.api.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor (staticName = "of")
@NoArgsConstructor
@With
public class EmployeesDTO {

    private List<EmployeeDTO> employees;
}
