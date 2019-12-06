package com.example.springbootforesdemo;

import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootforesdemoApplication.class)
public class EsDemoProductDSLTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ProductRepository productRepository;

    /**
     * @Description:matchQuery底层采用的是词条匹配查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testMatchQuery(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "华为"));
        // 搜索，获取结果
        Page<Product> products = this.productRepository.search(queryBuilder.build());
        // 总条数
        long total = products.getTotalElements();
        System.out.println("total = " + total);
        for (Product product : products) {
            System.out.println(product);
        }
    }


    /**
     * @Description:定义查询方法,含对价格的降序、升序查询
     * @Author: https://blog.csdn.net/chen_2890
     */
    @Test
    public void testQueryAll(){
        // 查找所有
        //Iterable<Product> list = this.ProductRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Product> list = this.productRepository.findAll(Sort.by("price").ascending());

        for (Product Product:list){
            System.out.println(Product);
        }
    }

}
