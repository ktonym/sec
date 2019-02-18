package ke.co.kip.sec.repo;

import ke.co.kip.sec.entity.Authority;
import ke.co.kip.sec.entity.Permission;
import ke.co.kip.sec.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepo extends JpaRepository<Permission,Long> {
  List<Permission> findByAuthorityAndUsersIn(Authority authority, List<User> users);
  List<Permission> findByUsersIn(List<User> users);
}
