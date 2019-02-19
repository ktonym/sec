package ke.co.kip.sec.service;

import ke.co.kip.sec.entity.ProductType;
import ke.co.kip.sec.repo.ProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productTypeService")
@Transactional
public class ProductTypeService implements IProductTypeService {

  @Autowired
  private ProductTypeRepo repo;

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public ProductType create(String name) {
      if (name.trim().isEmpty()){
        return null;
      } else {
        ProductType productType = ProductType.builder().name(name).build();
        repo.save(productType);
        return productType;
      }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public ProductType update(Long id, String name) {

      return null;
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public List<ProductType> findAll() {
      return repo.findAll();
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
  public ProductType findById(Long id) {
      return repo.findById(id).get();
  }
}
