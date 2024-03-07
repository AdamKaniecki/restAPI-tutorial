package pl.zajavka.controller.dto;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.entity.PetEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T14:28:11+0100",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO map(EmployeeEntity employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.employeeId( employeeEntity.getEmployeeId() );
        employeeDTO.name( employeeEntity.getName() );
        employeeDTO.surname( employeeEntity.getSurname() );
        employeeDTO.salary( employeeEntity.getSalary() );
        employeeDTO.phone( employeeEntity.getPhone() );
        employeeDTO.email( employeeEntity.getEmail() );
        employeeDTO.pets( petEntitySetToPetDTOSet( employeeEntity.getPets() ) );

        return employeeDTO.build();
    }

    protected PetDTO petEntityToPetDTO(PetEntity petEntity) {
        if ( petEntity == null ) {
            return null;
        }

        PetDTO.PetDTOBuilder petDTO = PetDTO.builder();

        petDTO.petId( petEntity.getPetId() );
        petDTO.name( petEntity.getName() );
        petDTO.category( petEntity.getCategory() );

        return petDTO.build();
    }

    protected Set<PetDTO> petEntitySetToPetDTOSet(Set<PetEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<PetDTO> set1 = new LinkedHashSet<PetDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PetEntity petEntity : set ) {
            set1.add( petEntityToPetDTO( petEntity ) );
        }

        return set1;
    }
}
