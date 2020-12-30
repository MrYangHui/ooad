package service;

import bean.Regulator;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 19:11
 */
public class RegulatorService {
    private Regulator regulator;

    RegulatorService(Regulator regulator){
        this.regulator = regulator;
    }

    public void startTask(){
        regulator.startTask();
    }

    public void checkTask(){
        regulator.checkTask();
    }
}
