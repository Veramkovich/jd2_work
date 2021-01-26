package it.academy.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

enum Status {
    NEW, UPDATED, DELETED
}

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
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String personId;

    @Column(name = "P_NAME", length = 1000)
    private String name;

    @Column(name = "P_SEC_NAME", length = 1000)
    private String secondName;

    @Column(name = "P_BIRTH_DATE")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    @OrderColumn(name = "C_ORDER")
    private String[] comments;

    @OneToOne
    @JoinColumn(name = "SHOP_USER_ID")
    private ShopUser shopUser;
}

