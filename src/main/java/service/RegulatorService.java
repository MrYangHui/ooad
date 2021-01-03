package service;

import bean.Evaluation;
import bean.Product;
import bean.Report;
import bean.executor.IExecutor;
import bean.executor.Market;
import bean.task.ExpertTask;
import bean.task.MarketTask;
import bean.task.SelfCheckTask;
import bean.task.SuperviseTask;
import dao.DataBaseDao;

import java.time.LocalDate;
import java.util.*;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 19:11
 */
public class RegulatorService {
    private SuperviseTask superviseTask;
    private DataBaseDao dataBaseDao = new DataBaseDao();
    private List<Evaluation> evaluations;

    public RegulatorService() {
    }

    private void startTask(String[] marketNames, String[] productNames, LocalDate deadline){
        List<Market> markets = new ArrayList<>();
        for(String marketName : marketNames){
            markets.add(dataBaseDao.selectMarketByName(marketName));
        }
        superviseTask.setMarkets(markets);

        List<Product> products = new ArrayList<>();
        for(String productName : productNames){
            products.add(dataBaseDao.selectProductByName(productName));
        }
        superviseTask.setProducts(products);
        superviseTask.setDeadline(deadline);

        evaluations = new ArrayList<>();
        List<MarketTask> marketTasks = new ArrayList<>();
        for(Market market : markets){
            MarketTask marketTask = new MarketTask(market,deadline);
            Map<Product, Report> reports = new HashMap<>();
            for(Product product : products){
                reports.put(product, null);
            }
            marketTask.setReportMap(reports);
            marketTasks.add(marketTask);

            IExecutor executor = market;
            if(superviseTask instanceof ExpertTask){
                executor = ((ExpertTask) superviseTask).getExpert();
            }
            evaluations.add(new Evaluation(marketTask, executor));
        }
        superviseTask.setMarketTasks(marketTasks);

    }

    public void startExpertTask(String[] marketNames, String[] productNames, LocalDate deadline, String expertName){
        superviseTask = new ExpertTask(dataBaseDao.selectExpertByName(expertName));
        startTask(marketNames,productNames,deadline);
    }

    public void startSelfCheckTask(String[] marketNames, String[] productNames, LocalDate deadline){
        superviseTask = new SelfCheckTask();
        startTask(marketNames,productNames,deadline);
    }

    public void notifyExecutor(ExecutorService executorService){
        executorService.addTask(superviseTask);
    }

    public void checkReject(Product product, LocalDate startDate, LocalDate endDate){
        System.out.printf("%tF 至 %tF 不合格的 %s\n",startDate, endDate, product.getName());
        int total = 0;
        for(MarketTask marketTask : superviseTask.getMarketTasks()){
            Map<Product, Report> reportMap = marketTask.getReportMap();
            if(reportMap.get(product) != null){
                Report report = reportMap.get(product);
                if(!report.getCheckTime().isBefore(startDate) && !report.getCheckTime().isAfter(endDate)){
                    total += report.getResult();
                    System.out.printf("%s %d 抽检时间: %tF\n",
                            marketTask.getMarket().getName(), report.getResult(), report.getCheckTime());
                }
            }
        }
        System.out.printf("总计 %d\n", total);
    }

    public void checkEvaluation(IExecutor executor){
        int score = 0;
        for(Evaluation evaluation : evaluations){
            if(!evaluation.getExecutor().equals(executor))
                continue;
            if(evaluation.getStatus() == 1){
                System.out.printf("按时完成\"%s 截止日期: %tF\"监管任务, 得%d分\n",
                        evaluation.getMarketTask().getMarket().getName(), evaluation.getMarketTask().getDeadline(), evaluation.getScore());
                score += evaluation.getScore();
            } else if(evaluation.getStatus() == 2){
                System.out.printf("没有按时完成\"%s 截止日期: %tF\"监管任务, 扣%d分\n",
                        evaluation.getMarketTask().getMarket().getName(), evaluation.getMarketTask().getDeadline(), evaluation.getScore());
                score += evaluation.getScore();
            } else if(evaluation.getStatus() == 3){
                System.out.printf("超过20天未完成\"%s 截止日期: %tF\"监管任务, 扣%d分\n",
                        evaluation.getMarketTask().getMarket().getName(), evaluation.getMarketTask().getDeadline(), evaluation.getScore());
                score += evaluation.getScore();
            }
        }
        System.out.printf("%s 总得分: %d\n", executor.getName(), score);
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
}
