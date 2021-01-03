package bean.task;

import bean.Product;
import bean.Report;
import bean.executor.Market;

import java.time.LocalDate;
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
    private LocalDate deadline;
    private LocalDate finishDate;

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public MarketTask(Market market, LocalDate deadline) {
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
        if(!reportMap.containsValue(null)){
            isFinished = true;
            finishDate = LocalDate.now();
        }
    }
}
