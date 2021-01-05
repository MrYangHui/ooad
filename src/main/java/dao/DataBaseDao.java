package dao;

import bean.Product;
import bean.executor.Expert;
import bean.executor.Market;
import mapper.*;
import session.SqlSession;

/**
 * @author: YXH
 * @date: 2021/1/1
 * @time: 14:16
 */

public class DataBaseDao {
    private final ExpertMapper expertMapper = SqlSession.getMapper(ExpertMapper.class);
    private final MarketMapper marketMapper = SqlSession.getMapper(MarketMapper.class);
    private final ProductMapper productMapper = SqlSession.getMapper(ProductMapper.class);

    public Expert selectExpertByName(String name){
        return expertMapper.selectExpertByName(name);
    }

    public Market selectMarketByName(String name){
        return marketMapper.selectMarketByName(name);
    }

    public Product selectProductByName(String name){
        return productMapper.selectProductByName(name);
    }

    public Expert selectExpertById(int id){
        return expertMapper.selectExpertById(id);
    }

    public Market selectMarketById(int id){
        return marketMapper.selectMarketById(id);
    }

    public Product selectProductById(int id){
        return productMapper.selectProductById(id);
    }

    public void insertExpert(String name){
        expertMapper.insertExpert(name);
    }

    public void insertMarket(String name){
        marketMapper.insertMarket(name);
    }

    public void insertProduct(String name){
        productMapper.insertProduct(name);
    }
}
