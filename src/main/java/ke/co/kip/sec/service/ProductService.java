package ke.co.kip.sec.service;

import ke.co.kip.sec.entity.Product;
import ke.co.kip.sec.entity.ProductType;
import ke.co.kip.sec.repo.ProductRepo;
import ke.co.kip.sec.repo.ProductTypeRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ProductRepo repo;
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Product create(Long typeId, String name, boolean active) {
        ProductType type = productTypeRepo.getOne(typeId);
        Product product = Product.builder()
                .type(type).active(active).name(name).build();
        repo.save(product);
        return product;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Product update(Long id, Long typeId, String name, boolean active) {

        Product prod = repo.findCompById(id);
        return prod;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Product findById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Product> findByType(Long typeId) {
        return repo.findByType(typeId);
    }
}
