package pl.zajavka.infrastructure.petstore;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;

import pl.zajavka.controller.dao.PetDAO;



import java.util.Optional;

@Component
@AllArgsConstructor
public class PetClientImpl implements PetDAO {

    private WebClient webClient; //- wstrzyknięty bean WebClient za pomocą Dependency Injection
                                 // (jest zdefiniowany w klasie WebClientConfiguration)

    @Override
    public Optional<Pet> getPet(Long petId) {
    try {
        Pet result = webClient
                .get()// wywołuje metodę GET
                .uri("/pet/" + petId)//podaje adres zasobu, który chce wyciągnąć
                .retrieve()// to taki przycisk play
                .bodyToMono(Pet.class)// -określam w formie której klasy ma być zwrócony json z tamtej aplikacji
                .block();
        return Optional.ofNullable(result);
    }
    catch (Exception e){
        return Optional.empty();
    }
    }
    }

