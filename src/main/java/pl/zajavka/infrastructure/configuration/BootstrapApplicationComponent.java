package pl.zajavka.infrastructure.configuration;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.infrastructure.database.entity.EmployeeEntity;
import pl.zajavka.infrastructure.database.repository.EmployeeRepository;
import pl.zajavka.infrastructure.database.repository.PetRepository;

import java.math.BigDecimal;
@Slf4j
@Component
@AllArgsConstructor
public class BootstrapApplicationComponent implements ApplicationListener<ContextRefreshedEvent> {

    private EmployeeRepository employeeRepository;
    private PetRepository petRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final @NonNull ContextRefreshedEvent event) {
//        if(employeeRepository.findAll().size()>0){
//            return;
//        }
        petRepository.deleteAll();
        employeeRepository.deleteAll();

        employeeRepository.save(EmployeeEntity.builder()
                .name("A")
                .surname("B")
                .salary(new BigDecimal("2000.00"))
                .email("costam@com")
                .phone("24 244 244 24")

                .build());

        employeeRepository.save(EmployeeEntity.builder()
                .name("C")
                .surname("D")
                .salary(new BigDecimal("3000.00"))
                .email("costam2@com")
                .phone("25 244 244 24")
                .build());

        employeeRepository.save(EmployeeEntity.builder()
                .name("E")
                .surname("F")
                .salary(new BigDecimal("4000.00"))
                .email("costam3@com")
                .phone("26 244 244 24")
                .build());


    }
}
