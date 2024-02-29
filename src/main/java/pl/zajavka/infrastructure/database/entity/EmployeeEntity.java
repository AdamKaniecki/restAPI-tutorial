package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@With
@Builder
@Entity
@ToString(of = {"name","surname","email" })
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="employeeId")
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "employee")
    private Set<PetEntity> pets;




}
