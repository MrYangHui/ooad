package bean;

import java.time.LocalDate;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 20:16
 */
public class Report {
    private Product product;
    private int result;
    private LocalDate checkTime;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public LocalDate getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDate checkTime) {
        this.checkTime = checkTime;
    }
}
