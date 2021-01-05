package mapper;

import bean.executor.*;
import annotation.*;

public interface ExpertMapper {
    @OrmSelect("select * from expert where name = #{name}")
    Expert selectExpertByName(@OrmParam("name") String name);

    @OrmSelect("select * from expert where id = #{id}")
    Expert selectExpertById(@OrmParam("id") int id);

    @OrmInsert("insert into expert(name) values(#{name})")
    int insertExpert(@OrmParam(value = "name") String name);

    @OrmDelete("delete from expert where id=#{id}")
    int deleteExpert(@OrmParam(value="id")int id);

}
