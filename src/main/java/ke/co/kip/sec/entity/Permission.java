package ke.co.kip.sec.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERMISSION",uniqueConstraints = {@UniqueConstraint(columnNames = {"name","authority_limit"})})
public class Permission implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "authority_limit")
    private Long authorityLimit;
    @ManyToOne(optional = false)
    private Authority authority;
    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    @Override
    public String getAuthority() {
      return name;
    }

}
