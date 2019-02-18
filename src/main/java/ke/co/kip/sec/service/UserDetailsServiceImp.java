package ke.co.kip.sec.service;

import ke.co.kip.sec.entity.Authority;
import ke.co.kip.sec.entity.Permission;
import ke.co.kip.sec.entity.User;
import ke.co.kip.sec.repo.AuthorityRepo;
import ke.co.kip.sec.repo.PermissionRepo;
import ke.co.kip.sec.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
public class UserDetailsServiceImp implements UserDetailsService {

  @Autowired
  private UserRepo repo;
  @Autowired
  private AuthorityRepo authorityRepo;
  @Autowired
  private PermissionRepo permissionRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = repo.findByUsername(username);
      if(user == null){
          throw new UsernameNotFoundException(String.format("No user found with username '%s'.",username));
      } else {
          List<User> users = new ArrayList<>();
          users.add(user);
          List<Authority> authorities = authorityRepo.findByUsersIn(users);
          List<Permission> permissions = permissionRepo.findByUsersIn(users);
          UserDetails userDetails = new org.springframework.security.core.userdetails
                    .User(username,user.getPassword(),user.isEnabled(),user.isAccountNonExpired(),
                    user.isCredentialsNonExpired(),user.isAccountNonLocked(),authorities);
          return userDetails;
      }
  }

}
