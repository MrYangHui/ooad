package bean.task;

import bean.executor.Market;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 20:03
 */
public class MarketTask {
    private boolean isFinished;
    private Market market;

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
