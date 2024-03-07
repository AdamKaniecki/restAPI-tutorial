package pl.zajavka.controller.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@With
public class PetDTO {
    private Integer petId;
    private Long petSoreId;
    private String name;
    private String category;
}
