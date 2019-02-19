package ke.co.kip.sec.service;

import ke.co.kip.sec.entity.ProductType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductTypeService {

    ProductType create(String name);
    ProductType update(Long id,String name);
    List<ProductType> findAll();
    ProductType findById(Long id);

}
