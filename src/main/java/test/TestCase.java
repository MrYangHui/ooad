package test;
import bean.executor.Market;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.ExecutorService;
import service.IndicatorService;
import service.RegulatorService;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 13:48
 */
public class TestCase {
    private RegulatorService regulatorService;
    private ExecutorService executorService;

    @Before
    public void setUp(){
        System.out.println("before testcase， setUp()");
        regulatorService = new RegulatorService();
        executorService = new ExecutorService();
        regulatorService.startSelfCheckTask(
                new String[]{"农贸市场A", "农贸市场B"},
                new String[]{"水产品", "禽肉产品"},
                new Date()
        );
        regulatorService.notifyExecutor(executorService);
    }
    @Test
    public void testOneCase(){
        Market market = new Market();
        market.setName("农贸市场A");
        executorService.checkTasks(market);
    }
    @Test
    public void testTwoCase(){
        Market market1 = new Market();
        market1.setName("农贸市场A");
        Market market2 = new Market();
        market2.setName("农贸市场A");
        assertTrue(market1.equals(market2));
    }

    @After
    public void tearDown(){
        System.out.println("After testcase， tearDown()");
    }
}
