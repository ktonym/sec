package ke.co.kip.sec.repo;

import ke.co.kip.sec.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.type WHERE p.id = :id")
    Product findCompById(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.type = (SELECT t FROM ProductType t WHERE t.id = :typeId)")
    List<Product> findByType(Long typeId);
}
