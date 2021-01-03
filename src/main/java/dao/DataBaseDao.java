package dao;

import bean.Product;
import bean.executor.Expert;
import bean.executor.Market;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author: YXH
 * @date: 2021/1/1
 * @time: 14:16
 */
public class DataBaseDao {

    private static final String JDBC_URL = "jdbc:h2:file: ../../db";
    private static final String USER = "db";
    private static final String PASSWORD = "db";
    private static final String DRIVER_CLASS="org.h2.Driver";

    public static void main(String[] args) throws Exception {
        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();

//        ResultSet rs = stmt.executeQuery("SELECT * FROM EXPERT");
//        while (rs.next()){
//            System.out.println(rs.getInt(1) + "/t" +    rs.getString(2));
//        }

        //释放资源
        stmt.close();
        conn.close();
    }

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
