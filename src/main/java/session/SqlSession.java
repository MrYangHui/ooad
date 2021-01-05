package session;

import proxy.OrmInvocationHandler;
import java.lang.reflect.Proxy;

public class SqlSession {
    public static<T> T getMapper(Class clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),new Class []{clazz},new OrmInvocationHandler(clazz));
    }
}
