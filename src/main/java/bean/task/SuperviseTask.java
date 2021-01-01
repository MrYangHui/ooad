package bean.task;

import bean.executor.Market;
import bean.Product;

import java.util.Date;
import java.util.List;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 19:54
 */
public class SuperviseTask {
    //监管任务基类

    private List<Market> markets;
    private List<Product> products;

    private String details;
    private Date deadline;

    private List<MarketTask> marketTasks;

    public List<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<MarketTask> getMarketTasks() {
        return marketTasks;
    }

    public void setMarketTasks(List<MarketTask> marketTasks) {
        this.marketTasks = marketTasks;
    }


}
