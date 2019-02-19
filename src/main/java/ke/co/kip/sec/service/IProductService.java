package ke.co.kip.sec.service;

import ke.co.kip.sec.entity.Product;

import java.util.List;

public interface IProductService {

    Product create(Long typeId,String name,boolean active);
    Product update(Long id,Long typeId,String name,boolean active);
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByType(Long typeId);

}
