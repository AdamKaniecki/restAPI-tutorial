package pl.zajavka.controller.dao;

import pl.zajavka.infrastructure.petstore.Pet;

import java.util.Optional;

public interface PetDAO {

    Optional<Pet> getPet(final Long petId);
}
