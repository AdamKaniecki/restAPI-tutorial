package pl.zajavka.infrastructure.petstore;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;

import pl.zajavka.controller.dao.PetDAO;
import pl.zajavka.infrastructure.petstore.api.PetApi;


import java.util.Optional;
// implementacja klasy z zewnętrznego API które otrzymałem w kontrakcie
@Component
@AllArgsConstructor
public class PetClientImpl implements PetDAO {

    private final PetApi petApi;
    private final PetMapper petMapper;

    //    nie muszę teraz nigdzie podawać endpointu dzięki generatorowi API(na podstawie kontraktu)
    //    robię to po to by mieć większą kontrolę nad obiektami w kodzie bo jeśli dostawca zrobi aktualizację to obiekty
    //    zmieniałyby się u mnie a tak jestem zabezpieczony
    @Override
    public Optional<Pet> getPet(Long petId) {
        try {
            final var available
                    = petApi.findPetsByStatusWithHttpInfo("available").block()
                    .getBody();
            return Optional.ofNullable(petApi.getPetById(petId).block())
                    .map(petMapper::map);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

