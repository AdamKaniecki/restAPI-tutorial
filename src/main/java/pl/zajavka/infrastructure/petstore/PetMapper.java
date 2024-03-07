package pl.zajavka.infrastructure.petstore;

import org.springframework.stereotype.Component;
import pl.zajavka.infrastructure.petstore.model.Category;

import java.util.Optional;

@Component
public class PetMapper {

//    odcinam się od wygenerowanej klasy zewnętrznej
    public Pet map(pl.zajavka.infrastructure.petstore.model.Pet pet){ //zwracam Peta, który jest mój
        return Pet.builder()
                .id(pet.getId())
                .name(pet.getName())
                .category(Optional.ofNullable(pet.getCategory()).map(Category::getName).orElse(null))
                .build();
    }

}
