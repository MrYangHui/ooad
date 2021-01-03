package mapper;

import bean.executor.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ExpertMapper {
    @Select("select * from expert where name = #{name}")
    Expert selectExpertByName(String name);

    @Insert("insert into expert(name) values (#{name})")
    int insertExpert(Expert expert);
}
