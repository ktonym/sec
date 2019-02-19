package ke.co.kip.sec.web;

import ke.co.kip.sec.entity.ProductType;
import ke.co.kip.sec.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    private IProductTypeService service;

    @PostMapping(value = "/create",produces = {"application/json"})
    @ResponseBody
    public ProductType create(@RequestBody ProductDao dao){
        return service.create(dao.name);
    }

    static class ProductDao{
        public Long id;
        public String name;
    }
}
