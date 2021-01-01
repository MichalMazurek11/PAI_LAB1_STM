package stm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stm.demo.model.dtos.TaskDto;
import stm.demo.model.enums.Status;
import stm.demo.model.Task;
import stm.demo.model.enums.Type;
import stm.demo.model.User;
import stm.demo.service.TaskService;
import stm.demo.service.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class MainController {



    private UserService userService;
    private TaskService taskService;

    @Autowired
    public MainController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    //a dodanie nowego użytkownika
    @PostMapping("/users/registration")
    public User registerUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {

        return userService.insertUser(new User(name, lastName, email, password, false, LocalDateTime.now()));
    }

    // b wyświetlenie wszystkich użytkowników
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.selectUsers();
    }

    //c metoda zwracająca użytkownika wyszukanego na podstawie klucza
    //głównego lub adresu email
    @GetMapping("users/IdOrEmail")
    public User getUserByIdOrEmail(Integer userId,String email){

        return userService.getUserByEmailOrId(userId,email);
    }

    //d zmiana statusu użytkowniaka
    @PutMapping("/users/status/id={userId}")
    public boolean changeUserStatus(
            @PathVariable("userId") int userId
    ) {
        return userService.changeUserStatus(userId);
    }

    //e usuniecie użytkownika
    @DeleteMapping("/users/delete")
    public boolean deleteUserById(
            @RequestParam("userId") int userId
    ){

        return userService.deleteUserById(userId);
    }


    @PostMapping("/task/create")
    public String createTask(@ModelAttribute("taskDto") @Valid TaskDto taskDto, BindingResult bindingResult)
    {
        if(!userService.findById(taskDto.getUserId()).isPresent()){
            return "Invalid user id";
        }
        if(bindingResult.hasErrors()){
            return "Validation Errors: \n" + bindingResult.getFieldErrors().stream()
                    .map(err -> err.getField() + " : " + err.getDefaultMessage())
                    .collect(Collectors.joining("\n"));
        }
        if(taskDto.getType() == null){
            return "Empty task type";
        }
        if(taskDto.getStatus() == null){
            return "Empty status";
        }

         taskService.createTask(taskDto);
        return  "OK";
    }


    //f utworzenie zadania przez użytkownika
//    @PostMapping("/tasks/create/id={userId}")
//    public Task createTask(
//            @PathVariable("userId") int userId,
//
//            @RequestParam("title") String title,
//            @RequestParam("description") String description,
//            @RequestParam("type") Type type,
//            @RequestParam("status") Status status
//
//    ){
//
//       User user = userService.findUser(userId);
//
//        return taskService.createTask(new Task(title,description,LocalDateTime.now(),type,status,user));
//    }

    //g
    @GetMapping("/tasks/date")
    public List<Task> getAllTasks(){
        return taskService.selectTasks();
    }

    //h
    @GetMapping("tasks/TitleOrStatusOrType")
    public List<Task> getByTitleOrStatusOrType(String title, Type type, Status status){
        return taskService.getTaskByTytleOrStatusOrType(title,type,status);
    }


    //i
    @PutMapping("/task/status/id={taskId}")
    public Task changeTaskStatus(
            @PathVariable("taskId") int taskId,
             @RequestParam("status") Status status
    ) {
        return taskService.changeTaskStatus(taskId,status);
    }

    //j
    @DeleteMapping("/task/delete/id={taskId}")
    public  void deleteTaskById(
            @RequestParam("taskId") int taskId
    ){

       taskService.deleteTask(taskId);
    }
}
