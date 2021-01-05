package mapper;

import annotation.*;
import bean.executor.Market;

public interface MarketMapper {
    @OrmSelect("select * from market where name = #{name}")
    Market selectMarketByName(@OrmParam("name") String name);

    @OrmSelect("select * from market where id = #{id}")
    Market selectMarketById(@OrmParam("id") int id);

    @OrmInsert("insert into market(name) values(#{name})")
    int insertMarket(@OrmParam(value = "name") String name);

    @OrmDelete("delete from market where id=#{id}")
    int deleteMarket(@OrmParam(value="id")int id);
}
