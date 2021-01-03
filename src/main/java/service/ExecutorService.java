package service;

import bean.Product;
import bean.Report;
import bean.executor.Expert;
import bean.executor.IExecutor;
import bean.executor.Market;
import bean.task.ExpertTask;
import bean.task.MarketTask;
import bean.task.SuperviseTask;

import java.time.LocalDate;
import java.util.*;

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
        marketListMap.computeIfAbsent(executor, k -> new ArrayList<MarketTask>());
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
            if(executor instanceof Expert){
                System.out.printf("专家抽检 专家: %s 农贸市场: %s 截止日期: %tF %s\n",
                        executor.getName(), marketTask.getMarket().getName(),marketTask.getDeadline(),
                        (marketTask.isFinished() ? "已完成" : "未完成"));
            }else {
                System.out.printf("自检任务  农贸市场: %s 截止日期: %tF %s\n",
                        marketTask.getMarket().getName(),marketTask.getDeadline(),
                        (marketTask.isFinished() ? "已完成" : "未完成"));
            }
            Map<Product, Report> reportMap = marketTask.getReportMap();
            for(Map.Entry<Product, Report> entry : reportMap.entrySet()){
                System.out.print("-"+entry.getKey().getName());
                if(entry.getValue() == null){
                    System.out.println(" 未抽检");
                }else {
                    System.out.printf(" 不合格数: %d 抽检时间: %tF\n",entry.getValue().getResult(),entry.getValue().getCheckTime());
                }
            }
        }
    }
    public void executeTask(IExecutor executor, Market market, Product product){
        List<MarketTask> marketTasks = marketListMap.get(executor);
        if(marketTasks == null)
            return;
        for(MarketTask marketTask : marketTasks){
            if(marketTask.getMarket().equals(market) && marketTask.getReportMap().containsKey(product)){
                Report report = new Report();
                report.setProduct(product);
                report.setResult((int)(Math.random() * 10));
                report.setCheckTime(LocalDate.now());
                marketTask.getReportMap().put(product, report);
                marketTask.update();
                System.out.printf("%s 完成抽检 %s %s, 不合格数: %d\n",
                        executor.getName(),market.getName(),product.getName(), report.getResult());
            }
        }
    }
}
