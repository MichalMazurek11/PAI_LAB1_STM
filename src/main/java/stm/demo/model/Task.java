package stm.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stm.demo.model.enums.Status;
import stm.demo.model.enums.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data            // auto-generowanie getters/setters/toString
@AllArgsConstructor // auto-generacja konstruktora z wszystkimi polami w argymencie
@NoArgsConstructor  // auto-generacja kontruktora bezargumentowego
//@Getter          // auto-generowanie getters
//@Setter          // auto-generowanie Setters
//@ToString          // auto-generowanie metody toString()
@Entity
public class Task {
    @Id                                                 // klucz główny
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto inkrementacja dot tylko tabelki
    private int taskId;

    private String title;

    private String descrpition;

    @Column(name = "date_added")
    private LocalDateTime dateAdded = LocalDateTime.now() ;

    @Enumerated(value = EnumType.STRING)        // EnumType.ORDINAL - przechowuje indeks obiektów enum
    // EnumType.STRING  - przechowuje nazwy obiektów enum
    private Type type;

    @Enumerated(value = EnumType.STRING)        // EnumType.ORDINAL - przechowuje indeks obiektów enum
    // EnumType.STRING  - przechowuje nazwy obiektów enum
    private Status status;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private User user;




}
