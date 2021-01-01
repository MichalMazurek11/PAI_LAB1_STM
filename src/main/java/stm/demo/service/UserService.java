package stm.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stm.demo.model.Task;
import stm.demo.model.User;
import stm.demo.repository.TaskRepository;
import stm.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    // a utworzenie użytkownika
    public User insertUser(User user){

            return userRepository.save(user);
        }
    // b wyswietlenie wszystkich użytkowników
    public List<User> selectUsers(){
        return userRepository.findAll();
    }

    // c metoda zwracająca użytkownika wyszukanego na podstawie klucza
    //głównego lub adresu email
    public User getUserByEmailOrId(Integer userId,String email) {
        return userRepository.findByUserIdOrEmail(userId,email);
    }


    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    public User findUser(int id ){
        return  userRepository.findByUserId(id);
    }


    // Szukanie Usera po ID
    public Optional<User> getUserById(int userId){
        return userRepository.findById(userId);
    }


    //d zmiana statusu użytkowniaka
    public boolean changeUserStatus(int userId){

        Optional<User> userOptional = getUserById(userId);
        if(userOptional.isPresent()){
            User userToActivate = userOptional.get();

            if(userToActivate.isStatus()){
            userToActivate.setStatus(false);
            }else{
                userToActivate.setStatus(true);
            }
            userRepository.save(userToActivate);     // działa jak update gdy dotyczy istniejącego w repo obiektu
        }
        return userOptional.get().isStatus();
    }

    //e usuniecie użytkownika
    @Transactional
    public boolean deleteUserById(Integer id){
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent()) {
            for (Task task : taskRepository.findTasksByUserId(id)) {
                taskRepository.delete(task);
            }
            userRepository.delete(optUser.get());
            return true;
        }
        return false;
    }



}
