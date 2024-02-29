//package pl.zajavka.infrastructure.database.petstore;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import pl.zajavka.api.dao.PetDAO;
//
//
//import java.util.Optional;
//
//@Component
//@AllArgsConstructor
//public class PetClientImpl implements PetDAO {
//
////    private WebClient webClient;
////
////    @Override
////    public Optional<Pet> getPet(Long petId) {
////    try {
////        Pet result = webClient
////                .get()
////                .uri("/pet/" + petId)
////                .retrieve()
////                .bodyToMono(Pet.class)
////                .block();
////        return Optional.ofNullable(result);
////    }
////    catch (Exception e){
////        return Optional.empty();
////    }
////    }
//    }
//
