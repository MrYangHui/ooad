package service;

import bean.executor.Expert;
import mapper.ExpertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertService {
    private ExpertMapper gameMapper;

    @Autowired
    public ExpertService(ExpertMapper gameMapper){
        this.gameMapper = gameMapper;
    }

    public Expert selectExpertByName(String name){
        return this.gameMapper.selectExpertByName(name);
    }

}
