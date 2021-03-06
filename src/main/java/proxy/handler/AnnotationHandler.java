package proxy.handler;

import annotation.*;
import util.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// 处理具体的注解
public class AnnotationHandler {
    public static  Object handleOrmInsert(OrmInsert extInsert, Method method, Object[] args) {
        // 获取注解上面的sql语句
        String insertSql = extInsert.value();
        // 方法里面的参数和注解里面的参数进行绑定
        ConcurrentHashMap<Object, Object> paramsMap = paramsMap( method, args);
        // 存放sql执行的参数---参数绑定过程
        String[] sqlInsertParameter = SQLUtil.sqlInsertParameter(insertSql);
        List<Object> sqlParams = sqlParams(sqlInsertParameter, paramsMap);
        // 根据参数替换为？
        String newSQL = SQLUtil.paramQuestion(insertSql, sqlInsertParameter);
        System.out.println("newSQL:" + newSQL + ",sqlParams:" + sqlParams.toString());
        return JDBCUtil.insert(newSQL, false, sqlParams);
    }

    public static Object handleOrmSelect(OrmSelect ormSelect,Object proxy,Method method,Object[] args){
        String selectSql = ormSelect.value();
        ConcurrentHashMap<Object, Object> paramsMap = paramsMap(method, args);
        List<String> sqlSelectParameter = SQLUtil.sqlSelectParameter(selectSql);
        List<Object> sqlParams = new ArrayList<>();
        for (String parameterName : sqlSelectParameter) {
            Object parameterValue = paramsMap.get(parameterName);
            sqlParams.add(parameterValue);
        }

        String newSql = SQLUtil.paramQuestion(selectSql, sqlSelectParameter);
        ResultSet res = JDBCUtil.query(newSql, sqlParams);
        try {
            if (!res.next()){
                return null;
            }
            // 下标往上移动移位
            res.previous();
            //获取方法的返回类型
            Class<?> returnType = method.getReturnType();
            Object obj = returnType.newInstance();
            while (res.next()) {
                // 获取当前所有的属性
                Field[] declaredFields = returnType.getDeclaredFields();
                for (Field field : declaredFields) {
                    String fieldName = field.getName();
                    Object fieldValue = res.getObject(fieldName);
                    field.setAccessible(true);
                    field.set(obj, fieldValue);
                }
            }
            return obj;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static List<Object> sqlParams(String[] sqlInsertParameter, ConcurrentHashMap<Object, Object> paramsMap) {
        List<Object> sqlParams = new ArrayList<>();
        for (String paramName : sqlInsertParameter) {
            Object paramValue = paramsMap.get(paramName);
            sqlParams.add(paramValue);
        }
        return sqlParams;
    }

    private static ConcurrentHashMap<Object, Object> paramsMap(Method method, Object[] args) {
        ConcurrentHashMap<Object, Object> paramsMap = new ConcurrentHashMap<>();
        // 获取方法上的参数
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            OrmParam ormParam = parameter.getDeclaredAnnotation(OrmParam.class);
            if (ormParam != null) {
                String paramName = ormParam.value();
                Object paramValue = args[i];
                paramsMap.put(paramName, paramValue);
            }
        }
        return paramsMap;
    }
}
