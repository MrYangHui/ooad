package bean;

import java.util.Map;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 20:16
 */
public class Report {
    Map<Product, Integer> checkResult;

    public Map<Product, Integer> getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Map<Product, Integer> checkResult) {
        this.checkResult = checkResult;
    }
}
