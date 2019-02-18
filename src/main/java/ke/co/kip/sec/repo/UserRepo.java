package ke.co.kip.sec.repo;

import ke.co.kip.sec.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
  User findByUsername(String username);
}
