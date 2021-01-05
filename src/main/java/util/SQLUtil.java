package util;

import java.util.ArrayList;
import java.util.List;

public class SQLUtil{
    public static String[] sqlInsertParameter(String sql) {
        int startIndex = sql.indexOf("values");
        int endIndex = sql.length();
        String substring = sql.substring(startIndex + 6, endIndex)
                .replace("(", "")
                .replace(")", "")
                .replace("#{", "")
                .replace("}", "");
        return substring.split(",");
    }

    public static List<String> sqlSelectParameter(String sql) {
        int startIndex = sql.indexOf("where");
        int endIndex = sql.length();
        String substring = sql.substring(startIndex + 5, endIndex);
        String[] split = substring.split("and");
        List<String> listArr = new ArrayList<>();
        for (String string : split) {
            String[] sp2 = string.split("=");
            listArr.add(sp2[0].trim());
        }
        return listArr;
    }

    public static String paramQuestion(String sql, String[] parameterName) {
        for (String string : parameterName) {
            sql = sql.replace("#{" + string + "}", "?");
        }
        return sql;
    }

    public static String paramQuestion(String sql, List<String> parameterName) {
        for (String string : parameterName) {
            sql = sql.replace("#{" + string + "}", "?");
        }
        return sql;
    }


}
