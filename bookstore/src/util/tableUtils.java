package util;

/**
 * Created by jia on 3/23/17.
 */

import java.lang.reflect.Field;

import annotation.column;
import annotation.table;


public class tableUtils {
    public static String getCreateTableSQl(Class<?> clazz){
        StringBuilder sb = new StringBuilder();
        sb.append("create table ");
        //get table name
        table table = (table) clazz.getAnnotation(table.class);
        String tableName = table.tableName();
        sb.append(tableName).append("(\n");

        Field[] fields = clazz.getDeclaredFields();
        String primaryKey = "";
        //loop all fields
        for (int i = 0; i < fields.length; i++) {
            column column = (column) fields[i].getAnnotations()[0];
            String field = column.field();
            String type = column.type();
            boolean defaultNull = column.defaultNull();

            sb.append("\t" + field).append(" ").append(type);
            if(defaultNull){
                if(type.toUpperCase().equals("TIMESTAMP")){
                    sb.append(",\n");
                }else{
                    sb.append(" DEFAULT NULL,\n");
                }
            }else{
                sb.append(" NOT NULL,\n");
                if(column.primaryKey()){
                    primaryKey = "PRIMARY KEY ("+field+")";
                }
            }
        }

        if(!stringUtils.isEmpty(primaryKey)){
        sb.append("\t").append(primaryKey);
        }
        sb.append("\n) DEFAULT CHARSET=utf8");

        return sb.toString();
    }

}
