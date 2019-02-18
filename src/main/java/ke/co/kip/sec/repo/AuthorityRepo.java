package ke.co.kip.sec.repo;

import ke.co.kip.sec.entity.Authority;
import ke.co.kip.sec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepo extends JpaRepository<Authority,Long> {

  List<Authority> findByUsersIn(List<User> users);
  List<Authority> findByUsersIn(User users);

  Authority findByName(String name);
}
