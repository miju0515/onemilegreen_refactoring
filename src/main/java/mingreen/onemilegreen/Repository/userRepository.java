package mingreen.onemilegreen.Repository;

import mingreen.onemilegreen.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepository extends JpaRepository<User,Integer> {
    //List<User> findAll();
    User save(User user);
}
