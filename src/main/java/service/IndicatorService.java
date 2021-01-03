package service;

import bean.Evaluation;
import bean.task.MarketTask;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 14:24
 */
public class IndicatorService {
    private RegulatorService regulatorService;

    public IndicatorService(RegulatorService regulatorService){
        this.regulatorService = regulatorService;
    }

    public void update(){
        List<Evaluation> evaluations = regulatorService.getEvaluations();
        LocalDate localDate = LocalDate.now();
        System.out.printf("当前日期: %tF, 调用update()\n", localDate);
        for(Evaluation evaluation : evaluations){
            MarketTask marketTask = evaluation.getMarketTask();
            LocalDate deadline = marketTask.getDeadline();
            if(!localDate.isAfter(deadline)){   //尚未到ddl
                if(marketTask.isFinished() && evaluation.getStatus() == 0){
                    evaluation.setStatus(1);
                    evaluation.setScore(10);
                }
                continue;
            }
            if(localDate.isAfter(deadline) && !localDate.isAfter(deadline.plusDays(20))){   //超时不足20天
                if(evaluation.getStatus() == 0){
                    evaluation.setStatus(2);
                    evaluation.setScore(-10);
                }
                continue;
            }
            if(localDate.isAfter(deadline.plusDays(20))){
                if(!marketTask.isFinished()){
                    evaluation.setStatus(3);
                    evaluation.setScore(-20);
                }
            }
        }
    }
}
