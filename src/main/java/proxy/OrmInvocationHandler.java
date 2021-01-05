package proxy;
import annotation.*;
import proxy.handler.AnnotationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class OrmInvocationHandler implements InvocationHandler {

    private Object targetObject;

    public OrmInvocationHandler(Object targetObject){
        this.targetObject = targetObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args){
        //判断是否有插入注解
        boolean insertFlag = method.isAnnotationPresent(OrmInsert.class);
        if (insertFlag){
            OrmInsert ormInsert = method.getDeclaredAnnotation(OrmInsert.class);
            if (ormInsert!=null){
                return AnnotationHandler.handleOrmInsert(ormInsert,method,args);
            }
        }

        //判断是否有查询注解
        boolean selectFlag = method.isAnnotationPresent(OrmSelect.class);
        if (selectFlag){
            OrmSelect ormSelect = method.getDeclaredAnnotation(OrmSelect.class);
            if (ormSelect!=null){
                return AnnotationHandler.handleOrmSelect(ormSelect,proxy,method,args);
            }
        }
        return null;
    }

    public Object getTargetObject(){
        return targetObject;
    }
}
