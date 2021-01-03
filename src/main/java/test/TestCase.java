package test;
import bean.Product;
import bean.executor.Expert;
import bean.executor.Market;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ExecutorService;
import service.IndicatorService;
import service.RegulatorService;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;


/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 13:48
 */
public class TestCase {
    private RegulatorService regulatorService;
    private ExecutorService executorService;
    private IndicatorService indicatorService;
    private Market marketA;
    private Market marketB;
    private Market marketC;
    private Product productA;
    private Product productB;

    @Before
    public void setUp(){
        System.out.println("Before");
        regulatorService = new RegulatorService();
        indicatorService = new IndicatorService(regulatorService);
        executorService = new ExecutorService();
        marketA = new Market("农贸市场A");
        marketB = new Market("农贸市场B");
        marketC = new Market("农贸市场C");
        productA = new Product("水产品");
        productB = new Product("禽肉产品");
    }

    @Test
    public void testMarketTask(){
        System.out.println("测试用例: 监管局发起自检任务,农贸市场查看并完成。");
        regulatorService.startSelfCheckTask(
                new String[]{"农贸市场A", "农贸市场B"},
                new String[]{"水产品", "禽肉产品"},
                LocalDate.now()
        );
        regulatorService.notifyExecutor(executorService);
        executorService.checkTasks(marketA);

        executorService.executeTask(marketA,marketA,productA);
        executorService.executeTask(marketA,marketA,productB);
        executorService.checkTasks(marketA);

        executorService.checkTasks(marketB);
    }

    @Test
    public void testExpertTask(){
        System.out.println("测试用例: 监管局发起专家抽检,专家查看并完成。");
        regulatorService.startExpertTask(
                new String[]{"农贸市场A", "农贸市场B"},
                new String[]{"水产品", "禽肉产品"},
                LocalDate.now(),
                "张三"
        );
        regulatorService.notifyExecutor(executorService);
        Expert expert = new Expert("张三");
        executorService.checkTasks(expert);
        executorService.executeTask(expert, marketA, productA);
        executorService.executeTask(expert, marketB, productB);
        executorService.checkTasks(expert);
    }

    @Test
    public void testCheckReject(){
        System.out.println("测试用例: 监管局查看某个农贸产品类别在某个时间范围内的总的不合格数。");
        regulatorService.startSelfCheckTask(
                new String[]{"农贸市场A", "农贸市场B"},
                new String[]{"水产品", "禽肉产品"},
                LocalDate.now()
        );
        regulatorService.notifyExecutor(executorService);
        executorService.executeTask(marketA,marketA,productA);
        executorService.executeTask(marketB,marketB,productA);
        regulatorService.checkReject(productA,LocalDate.now().plusDays(-1),LocalDate.now());
        regulatorService.checkReject(productA,LocalDate.now().plusDays(-2),LocalDate.now().plusDays(-1));
    }

    @Test
    public void testCheckEvaluation(){
        System.out.println("测试用例: 验证专家和农贸市场按时完成和未按时完成的抽检的场景，获取评估总得分和评估得扣分的记录。");
        regulatorService.startExpertTask(
                new String[]{"农贸市场A", "农贸市场B", "农贸市场C"},
                new String[]{"水产品"},
                LocalDate.now().plusDays(1),
                "张三"
        );
        regulatorService.notifyExecutor(executorService);
        Expert expert = new Expert("张三");
        executorService.executeTask(expert, marketA, productA);
        indicatorService.update();
        regulatorService.checkEvaluation(expert);

        LocalDate date1 = LocalDate.now().plusDays(2);
        new MockUp<LocalDate>(LocalDate.class){
            @Mock
            public LocalDate now(){
                return date1;
            }
        };
        indicatorService.update();
        regulatorService.checkEvaluation(expert);

        executorService.executeTask(expert,marketB, productA);
        LocalDate date2 = date1.plusDays(20);
        new MockUp<LocalDate>(LocalDate.class){
            @Mock
            public LocalDate now(){
                return date2;
            }
        };
        indicatorService.update();
        regulatorService.checkEvaluation(expert);
    }

    @Test
    public void testOne() {
        LocalDate localDate = LocalDate.now().plusDays(20);
        new MockUp<LocalDate>(LocalDate.class){
            @Mock
            public LocalDate now(){
                return localDate;
            }
        };
        System.out.printf("%tF\n", LocalDate.now());
        new MockUp<LocalDate>(LocalDate.class){
            @Mock
            public LocalDate now(){
                return localDate.plusDays(-10);
            }
        };
        System.out.printf("%tF\n", LocalDate.now());
    }

    @After
    public void tearDown(){
        System.out.println("After");
    }
}
