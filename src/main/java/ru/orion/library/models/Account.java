package ru.orion.library.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import ru.orion.library.enums.AccountRole;
import ru.orion.library.enums.AccountStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity()
@Table(name = "account")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "reservationList")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "account" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String hashPassword;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRole role;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

}
