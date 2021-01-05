package mapper;

import annotation.*;
import bean.Product;

public interface ProductMapper {
    @OrmSelect("select * from product where name = #{name}")
    Product selectProductByName(@OrmParam("name") String name);

    @OrmSelect("select * from product where id = #{id}")
    Product selectProductById(@OrmParam("id") int id);

    @OrmInsert("insert into product(name) values(#{name})")
    int insertProduct(@OrmParam(value = "name") String name);

    @OrmDelete("delete from product where id=#{id}")
    int deleteProduct(@OrmParam(value="id")int id);
}