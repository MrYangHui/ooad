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

    List<Market> markets;
    List<Product> products;

    String details;
    Date deadline;
}
