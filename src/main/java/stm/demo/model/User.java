package stm.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data            // auto-generowanie getters/setters/toString
@AllArgsConstructor // auto-generacja konstruktora z wszystkimi polami w argymencie
@NoArgsConstructor  // auto-generacja kontruktora bezargumentowego
//@Getter          // auto-generowanie getters
//@Setter          // auto-generowanie Setters
//@ToString          // auto-generowanie metody toString()
@Entity
public class User {


    @Id                                                 // klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto inkrementacja dot tylko tabelki
    private int userId;

    private String name;

    @Column(name = "lastName")
    private String lastName;

    private String email;

    private String password;

    private boolean status = false;

    @Column(name = "registration_time")
    private LocalDateTime registrationDateTime = LocalDateTime.now() ;

    public User(int userId) {
        this.userId = userId;
    }

    public User(String name, String lastName, String email, String password, boolean status, LocalDateTime registrationDateTime) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.registrationDateTime = registrationDateTime;
    }
}
