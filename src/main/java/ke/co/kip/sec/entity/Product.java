package ke.co.kip.sec.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name",unique = true, nullable = false)
  private String name;
  @Column(name = "active")
  private boolean active;
  @ManyToOne(fetch = FetchType.LAZY)
  private ProductType type;
}
