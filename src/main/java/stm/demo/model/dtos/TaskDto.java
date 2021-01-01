package stm.demo.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import stm.demo.model.enums.Status;
import stm.demo.model.enums.Type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@AllArgsConstructor
@Data
public class TaskDto {

    //    @NotNull      // sprawdza czy wartość przekazywanego obiektu nie jest null
//    @NotEmpty     // sprawdza czy wartośc przekazywanego obiektu nie jest ""
//    @NotBlank     // kombinacja dwóch powyższych adnotacji
    @NotBlank(message = "Title must be not null")
    @Size(min = 5, max = 200, message = "Title must contain at least {min} to {max} characters")
    private String title;
    @NotBlank(message = "Description must be not null")
    @Size(min = 255, message = "Content must contain at least {min} characters")
    private String description;

    private Type type;
    private Status status;
    private int userId;



}
