package dao;

import bean.Product;
import bean.executor.Expert;
import bean.executor.Market;

/**
 * @author: YXH
 * @date: 2021/1/1
 * @time: 14:16
 */

public class DataBaseDao {
    public Expert selectExpertByName(String name){
        Expert expert = new Expert();
        expert.setName(name);
        return expert;
    }

    public Market selectMarketByName(String name){
        Market market = new Market();
        market.setName(name);
        return market;
    }

    public Product selectProductByName(String name){
        Product product = new Product();
        product.setName(name);
        return product;
    }
}
