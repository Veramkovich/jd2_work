package it.academy.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "T_PERSON")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Person {

    @Id
    @Column(name = "P_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "P_NAME", length = 1000)
    private String name;

    @Column(name = "P_SEC_NAME", length = 1000)
    private String secondName;

    @Column(name = "P_BIRTH_DATE")
    private Date dateOfBirth;

}

