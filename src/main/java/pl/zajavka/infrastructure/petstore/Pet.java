package pl.zajavka.infrastructure.petstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//tworzę tą klasę na podstawie wybranych pól które otrzymałem w jsonie po wywołaniu metody GET na tamtym API
public class  Pet {
    private Long id;
    private String name;
    private String category;
}
