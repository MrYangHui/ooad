package bean.task;

import bean.Product;
import bean.Report;
import bean.executor.Market;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 20:03
 */
public class MarketTask {
    private boolean isFinished = false;
    private Market market;
    private Map<Product, Report> reportMap;
    private Date deadline;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public MarketTask(boolean isFinished, Market market, Date deadline) {
        this.isFinished = isFinished;
        this.market = market;
        this.deadline = deadline;
        reportMap = new HashMap<Product, Report>();
    }

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

    public Map<Product, Report> getReportMap() {
        return reportMap;
    }

    public void setReportMap(Map<Product, Report> reportMap) {
        this.reportMap = reportMap;
    }

    public void update(){
        if(!reportMap.containsValue(null))
            isFinished = true;
    }
}
