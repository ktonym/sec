package ke.co.kip.sec.repo;

import ke.co.kip.sec.entity.ProductType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ProdTypeRepoTest {
//    @Autowired
//    private TestEntityManager entityManager;
    @Autowired
    private ProductTypeRepo repo;

    @Test
    public void saveProdType(){
        ProductType type = ProductType.builder().name("Individual").build();
        repo.saveAndFlush(type);
        Assert.assertTrue("Product Type should not be null",type.getId()!=null);
    }

    @Test
    public void deleteProdType(){
        ProductType type1 = ProductType.builder().name("Individual").build();
        ProductType type2 = ProductType.builder().name("Corporate").build();
        repo.saveAndFlush(type1);
        repo.saveAndFlush(type2);
        repo.deleteAll();
        Assert.assertTrue("Database should be empty",repo.findAll().isEmpty());
    }

}
