package ke.co.kip.sec.service;

import ke.co.kip.sec.entity.Authority;
import ke.co.kip.sec.entity.User;
import ke.co.kip.sec.repo.AuthorityRepo;
import ke.co.kip.sec.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DbInit implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private UserRepo userRepo;
  @Autowired
  private AuthorityRepo authorityRepo;

  boolean alreadySetup = false;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
      if(alreadySetup) return;

      User admin = createUserIfNotFound("admin");
      Authority role_admin = createAuthorityIfNotFound("ROLE_ADMIN");
      Authority role_user = createAuthorityIfNotFound("ROLE_USER");
      addUserIfNotInRole(admin,role_admin);
      addUserIfNotInRole(admin,role_user);
      alreadySetup = true;
  }

  @Transactional
  public User createUserIfNotFound(String username) {
      User user = userRepo.findByUsername(username);
      if(user==null){
          user = User.builder()
                  .username(username)
                  .email("ktonym@gmail.com")
                  .firstName("admin")
                  .lastName("admin")
                  .enabled(true)
                  .accountNonExpired(true)
                  .accountNonLocked(true)
                  .credentialsNonExpired(true)
                  .phoneNumber("0725766814").build();
          user.setPassword("123");
          userRepo.save(user);
      }
      return user;
  }

  @Transactional
  public Authority createAuthorityIfNotFound(String name) {
      Authority auth = authorityRepo.findByName(name);
      if(auth==null){
          auth = Authority.builder().name(name).build();
          authorityRepo.save(auth);
      }
      return auth;
  }

  @Transactional
  public void addUserIfNotInRole(User user,Authority authority){

      List<Authority> userAuths = authorityRepo.findByUsersIn(user);

      if(userAuths.contains(authority)){
      }else {
          userAuths.add(authority);
          user.setAuthorities(userAuths);
          userRepo.save(user);
      }

  }

}
