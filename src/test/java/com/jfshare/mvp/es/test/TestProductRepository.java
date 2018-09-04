package com.jfshare.mvp.es.test;


import com.jfshare.mvp.server.elasticsearch.ESProduct;
import com.jfshare.mvp.server.elasticsearch.repository.ESProductRepository;
import com.jfshare.mvp.server.service.LevelInfoService;
import com.jfshare.mvp.server.service.ProductService;
import com.alibaba.fastjson.JSON;
import com.jfshare.mvp.server.JfshareMvpServer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * TestProductRepository
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JfshareMvpServer.class)
public class TestProductRepository {


    @Autowired
    ESProductRepository productRepository;
    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private LevelInfoService levelInfoService;
    @Autowired
    private ProductService productService;
    
    @Test
    public void testProductService() {
//    	productService.syncESProduct(false, "ze170627170852000615");
    	Page<ESProduct> result = productService.queryESProduct("ze", 0, 10);
    	result.stream().forEach(p -> System.out.println(JSON.toJSONString(p)));

    }

   /* @Before
    public void before() {
        esTemplate.deleteIndex(ESProduct.class);
        esTemplate.createIndex(ESProduct.class);
        esTemplate.putMapping(ESProduct.class);
        esTemplate.refresh(ESProduct.class);
    }*/

   /* @Test
    public void test(){
        ESProduct product = new ESProduct("1001", "JavaDevMap learn Elasticsearch", 67d, "计算机网络");
        ESProduct saveBean = productRepository.save(product);
        System.out.println("save id is :"+saveBean.getId());
        Optional<ESProduct> byId = productRepository.findById(saveBean.getId());
        ESProduct findBean = byId.get();
        System.out.println("findBean is :"+findBean);
        findBean.setBrand("update brand");
        productRepository.save(findBean);
        Optional<ESProduct> byId1 = productRepository.findById(findBean.getId());
        ESProduct updateBean = byId1.get();
        System.out.println("updateBean is "+updateBean);
//        productRepository.delete(updateBean);
//        Optional<Product> byId2 = productRepository.findById(findBean.getId());
//        System.out.println("delete search result is "+byId2);

    }*/



   /* @Test
    public void testSearch(){
        for (int i=0;i<40;i++){
            double price=new Random().nextDouble()*100;
            System.out.println("price is "+price);

            ESProduct product = new ESProduct(i+"", "J在“Spring Boot应用企业级博客系统”课程中（http://coding.imooc.com/class/125.html），所有的博客功能都已经完成了。读者朋友们开始愉快地使用博客来发表博客了。但如果朋友们足够细心的话，发现在输入中文的标签的时候，存在一定的问题。", price, "计算机网络");
            productRepository.save(product);
        }


        Page<ESProduct> pageProducts = productRepository.findByProductName("Boot 课程", new PageRequest(0, 10));
        System.out.println("getTotalElements is :"+pageProducts.getTotalElements());
        System.out.println("getTotalPages is :"+pageProducts.getTotalPages());
        System.out.println("getContent().size() is :"+pageProducts.getContent().size());

    }
    @Test
    public void testSearchLessThan(){
        for (int i=0;i<40;i++){
            double price=new Random().nextDouble()*100;
            System.out.println("price is "+price);
            ESProduct product = new ESProduct(i+"", "JavaDevMap learn Elasticsearch", price, "计算机网络");
            productRepository.save(product);
        }


        List<ESProduct> byPriceLessThan = productRepository.findByPriceLessThan(20d);
        System.out.println("size is "+byPriceLessThan.size());
        byPriceLessThan = productRepository.findByPriceGreaterThan(20d);
        System.out.println("size is "+byPriceLessThan.size());

    }*/





    /*@Test
    public void addTestDatas(){
        for (int i=0;i<40;i++){
            double price=new Random().nextDouble()*100;
            System.out.println("price is "+price);
            ESProduct product = new ESProduct(i+"", "JavaDevMap learn Elasticsearch", price, "计算机网络");
            productRepository.save(product);
        }
    }
    
    
    @Test
    public void testJvjindou(){
    	int userId=99;
    	levelInfoService.presentJvjindouByuserId(userId);
    }*/



}
