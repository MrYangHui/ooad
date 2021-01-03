package bean;

import bean.executor.IExecutor;
import bean.task.MarketTask;

/**
 * @author: YXH
 * @date: 2021/1/2
 * @time: 22:43
 */
public class Evaluation {
    private MarketTask MarketTask;
    private IExecutor executor;
    private int score;
    private int status;   // 0 尚未评估， 1 按时完成， 2 未按时完成， 3 超过20天未完成

    public Evaluation(){

    }

    public Evaluation(MarketTask marketTask, IExecutor executor){
        this.MarketTask = marketTask;
        this.executor = executor;
    }

    public MarketTask getMarketTask() {
        return MarketTask;
    }

    public void setMarketTask(MarketTask marketTask) {
        this.MarketTask = marketTask;
    }

    public IExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(IExecutor executor) {
        this.executor = executor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
