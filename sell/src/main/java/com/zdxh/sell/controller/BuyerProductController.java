package com.zdxh.sell.controller;


import com.zdxh.sell.VO.ProductInfoVO;
import com.zdxh.sell.VO.ProductVO;
import com.zdxh.sell.VO.ResultVO;
import com.zdxh.sell.dataobject.ProductCategory;
import com.zdxh.sell.dataobject.ProductInfo;
import com.zdxh.sell.service.CategoryService;
import com.zdxh.sell.service.ProductService;
import com.zdxh.sell.utils.ResultVOUtile;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家端商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key="123")
    public ResultVO list(){
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList=productService.findUpAll();

        //2.查询类目()
//        List<Integer> categoryTypeList=new ArrayList<>();
//        for(ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //java8的lambda表达式
        List<Integer> categoryTypeList=productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据的拼装
        List<ProductVO> productVOList=new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


         return ResultVOUtile.success(productVOList);
    }

}