package service;

import bean.Product;
import bean.Report;
import bean.executor.IExecutor;
import bean.executor.Market;
import bean.task.ExpertTask;
import bean.task.MarketTask;
import bean.task.SuperviseTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: YXH
 * @date: 2021/1/1
 * @time: 14:34
 */
public class ExecutorService {
    private Map<IExecutor, List<MarketTask>> marketListMap;

    public ExecutorService() {
        marketListMap = new HashMap<IExecutor, List<MarketTask>>();
    }

    private void addMarketTask(IExecutor executor, MarketTask marketTask){
        if(marketListMap.get(executor) == null){
            marketListMap.put(executor, new ArrayList<MarketTask>());
        }
        marketListMap.get(executor).add(marketTask);
    }

    public void addTask(SuperviseTask superviseTask){
        IExecutor executor = null;
        if (superviseTask instanceof ExpertTask){
            executor = ((ExpertTask) superviseTask).getExpert();
        }
        for(MarketTask marketTask : superviseTask.getMarketTasks()){
            if(executor == null)
                addMarketTask(marketTask.getMarket(), marketTask);
            else
                addMarketTask(executor, marketTask);
        }
    }

    public void checkTasks(IExecutor executor){
        List<MarketTask> marketTasks = marketListMap.get(executor);
        if(marketTasks == null)
            return;
        for(MarketTask marketTask : marketTasks){
            Map<Product, Report> reportMap = marketTask.getReportMap();
            for(Map.Entry<Product, Report> entry : reportMap.entrySet()){
                System.out.print(entry.getKey().getName()+" ");
                if(entry.getValue() == null){
                    System.out.println("未抽检");
                }else {
                    System.out.println("不合格数:"+entry.getValue().getResult()+" 抽检时间:"+entry.getValue().getCheckTime());
                }
            }
        }
    }
    public void executeTask(IExecutor executor, Market market, Product product){
        List<MarketTask> marketTasks = marketListMap.get(executor);
        if(marketTasks == null)
            return;
        for(MarketTask marketTask : marketTasks){
            if(marketTask.getMarket() == market){
                Report report = new Report();
                report.setProduct(product);
                report.setResult((int)(Math.random() * 10));
                marketTask.getReportMap().put(product, report);
                marketTask.update();
            }
        }
    }
}
