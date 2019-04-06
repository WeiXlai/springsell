package com.zdxh.sell.repository;

import com.zdxh.sell.dataobject.ProductCategory;
import org.hibernate.criterion.Example;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
            Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);
            System.out.println(productCategory.toString());
    }

    @Test
    /*@Transactional*/
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男生最爱");
        productCategory.setCategoryType(3);
        productCategoryRepository.save(productCategory);

    }

    @Test
    public void updateTest() {
        ProductCategory productCategory=productCategoryRepository.findById(1).get();
        productCategory.setCategoryType(4);
        productCategory.setCategoryName("冬瓜");
        ProductCategory result = productCategoryRepository.save(productCategory);
        Assert.assertEquals(productCategory, result);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2,3,4);

        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}