package service;

import bean.Product;
import bean.Report;
import bean.executor.Market;
import bean.task.ExpertTask;
import bean.task.MarketTask;
import bean.task.SelfCheckTask;
import bean.task.SuperviseTask;
import dao.DataBaseDao;

import java.util.*;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 19:11
 */
public class RegulatorService {
    private SuperviseTask superviseTask;
    private DataBaseDao dataBaseDao = new DataBaseDao();

    public RegulatorService() {
    }

    private void startTask(String[] marketNames, String[] productNames, Date deadline){
        List<Market> markets = new ArrayList<Market>();
        for(String marketName : marketNames){
            markets.add(dataBaseDao.selectMarketByName(marketName));
        }
        superviseTask.setMarkets(markets);

        List<Product> products = new ArrayList<Product>();
        for(String productName : productNames){
            products.add(dataBaseDao.selectProductByName(productName));
        }
        superviseTask.setProducts(products);
        superviseTask.setDeadline(deadline);

        List<MarketTask> marketTasks = new ArrayList<MarketTask>();
        for(Market market : markets){
            MarketTask marketTask = new MarketTask(false,market,deadline);
            Map<Product, Report> reports = new HashMap<Product, Report>();
            for(Product product : products){
                reports.put(product, null);
            }
            marketTask.setReportMap(reports);
            marketTasks.add(marketTask);
        }
        superviseTask.setMarketTasks(marketTasks);
    }

    public void startExpertTask(String[] marketNames, String[] productNames, Date deadline, String expertName){
        superviseTask = new ExpertTask(dataBaseDao.selectExpertByName(expertName));
        startTask(marketNames,productNames,deadline);
    }

    public void startSelfCheckTask(String[] marketNames, String[] productNames, Date deadline){
        superviseTask = new SelfCheckTask();
        startTask(marketNames,productNames,deadline);
    }

    public void notifyExecutor(ExecutorService executorService){
        executorService.addTask(superviseTask);
    }

    public void checkTask(){

    }
}
