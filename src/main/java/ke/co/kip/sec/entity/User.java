package ke.co.kip.sec.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
@Builder
public class User implements UserDetails {

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "username")
  private String username;
  @JsonIgnore
  @Column(name = "password")
  private String password;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  private String email;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Column(name = "account_non_expired")
  private boolean accountNonExpired;
  @Column(name = "account_non_locked")
  private boolean accountNonLocked;
  @Column(name = "credentials_non_expired")
  private boolean credentialsNonExpired;
  @Column(name = "enabled")
  private boolean enabled;
  @Column(name = "last_password_reset")
  private Timestamp lastPasswordResetDate;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_authority",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
  private List<Authority> authorities;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_permission",
          joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
  private Set<Permission> permissions;

  @Column(name = "password_reset_token")
  private String passwordResetToken;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public void setPassword(String password) {
    Timestamp now = Timestamp.valueOf(LocalDateTime.now());
    this.setLastPasswordResetDate(now);
    this.password = PASSWORD_ENCODER.encode(password);
  }
}
