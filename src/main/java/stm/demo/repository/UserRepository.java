package stm.demo.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import stm.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    @Query(value = " select * from user where user_id = ?1 or email = ?2",nativeQuery = true)
    User findByUserIdOrEmail(Integer userId, String email);


    @Query(value = " select * from user where user_id = ?1",nativeQuery = true)
    User findByUserId(Integer userId);






}
