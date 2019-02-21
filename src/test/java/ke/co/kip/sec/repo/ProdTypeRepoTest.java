package ke.co.kip.sec.repo;

import ke.co.kip.sec.entity.Product;
import ke.co.kip.sec.entity.ProductType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProdTypeRepoTest {
//    @Autowired
//    private TestEntityManager entityManager;
    @Autowired
    private ProductTypeRepo repo;

    @Autowired
    private ProductRepo prodRepo;
    List<ProductType> list = new ArrayList<>();

    @Test
    public void saveProdType(){
        ProductType type = ProductType.builder().name("SME").build();
        repo.saveAndFlush(type);
        Assert.assertTrue("Product Type should not be null",type.getId()!=null);
    }

    @Before
    public void setupProdType(){
        //List<ProductType> list = new ArrayList<>();
        ProductType type1 = ProductType.builder().name("Individual").build();
        ProductType type2 = ProductType.builder().name("Corporate").build();
        repo.saveAndFlush(type1);
        repo.saveAndFlush(type2);
        list.add(type1);
        list.add(type2);
        //repo.deleteAll();
        //Assert.assertTrue("Database should be empty",repo.findAll().isEmpty());
    }


    @Test
    public void saveAndGetProd(){
        Product prod = Product.builder().name("Afya Nafuu").active(true).type(list.get(0)).build();
        prodRepo.saveAndFlush(prod);
        Product compById = prodRepo.findCompById(prod.getId());
        ProductType typeFromProd = compById.getType();
        Assert.assertTrue("Product should get retrieved with Product Type", typeFromProd!=null);
    }


}
