package test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author: YXH
 * @date: 2020/12/30
 * @time: 13:48
 */
public class TestCase {
    @Before
    public void setUp(){
        System.out.println("Before testcase， setUp()");
    }
    @Test
    public void testOneCase(){
        int a = 1;
        assertEquals(1, a);
        System.out.println("test one case");
    }
    @Test
    public void testTwoCase(){
        int a = 2;
        assertEquals(2, a);
        System.out.println("test one case");
    }

    @After
    public void tearDown(){
        System.out.println("After testcase， tearDown()");
    }
}
