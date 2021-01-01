package stm.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import stm.demo.model.User;
import stm.demo.model.dtos.TaskDto;
import stm.demo.model.enums.Status;
import stm.demo.model.Task;
import stm.demo.model.enums.Type;
import stm.demo.repository.TaskRepository;
import stm.demo.repository.UserRepository;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    //f
    public Task createTask(TaskDto taskDto){
        Optional<User> optUser = userRepository.findById(taskDto.getUserId());
        Task task = optUser.map(user -> new Task(taskDto.getTitle(), taskDto.getDescription(), taskDto.getType(), taskDto.getStatus(), user)).orElseGet(null);
        if(task != null) taskRepository.save(task);
        return task;
    }


    // f
//    public Task createTask(Task task) {
//        return taskRepository.save(task);
//    }


    // g
    public List<Task> selectTasks(){
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC, "dateAdded"));
    }
    // h
    public List<Task> getTaskByTytleOrStatusOrType(String title, Type type, Status status) {
        return taskRepository.findByTitleOrStatusOrType(title,type,status);
    }

    public Task findTask(int id ){
        return  taskRepository.findByTaskId(id);
    }

    // i
    public Task changeTaskStatus(int id, Status status) {
        Task task = taskRepository.findByTaskId(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }


    // j

    public void deleteTask(int id){
        taskRepository.deleteByTaskId(id);
    }
}
