package util;

/**
 * Created by jia on 3/23/17.
 */

import java.io.*;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class DataBaseUtils {
    private static String username;
    private static String password;
    private static String dataBaseName;
    private static Connection con = null;

    static {
        config("jdbc.properties");
    }

    //load jdbc properties
    public static void config(String path){
        InputStream inputStream = DataBaseUtils.class.getClassLoader().getResourceAsStream(path);
        Properties p = new Properties();
        try {
            p.load(inputStream);
            username = p.getProperty("db.username");
            password = p.getProperty("db.password");
            dataBaseName = p.getProperty("db.dataBaseName");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //connect the database
    public static void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataBaseName+"?useUnicode=true&characterEncoding=utf8&useSSL=true",username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //close the connection
    public static void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     * return results in list
     * @param sql
     * @param objects
     * @return
     * @throws SQLException
     */
    public static List<Map<String,Object>> queryForList(String sql,Object...objects){
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = con.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i+1, objects[i]);
            }

            rs = statement.executeQuery();
            while(rs.next()){
                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                int count = resultSetMetaData.getColumnCount();
                Map<String,Object> map = new HashMap<String, Object>();
                for (int i = 0; i < count; i++) {
                    map.put(resultSetMetaData.getColumnName(i+1), rs.getObject(resultSetMetaData.getColumnName(i+1)));
                }
                result.add(map);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * return results in map
     * @param sql
     * @param objects
     * @return
     * @throws SQLException
     */
    public static Map<String,Object> queryForMap(String sql,Object...objects) throws SQLException{
        Map<String,Object> result = new HashMap<String,Object>();
        List<Map<String,Object>> list = queryForList(sql, objects);
        if(list.size() == 0){
            return null;
        }

        result = list.get(0);
        return result;
    }

    public static <T>T queryForBean(String sql,Class clazz,Object...objects){
        T obj = null;
        Map<String,Object> map = null;
        Field field = null;
        try {
            obj = (T) clazz.newInstance();   //创建一个新的Bean实例
            map = queryForMap(sql, objects); //先将结果集放在一个Map中
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(map == null){
            return null;
        }
        //遍历Map
        for (String columnName : map.keySet()) {
            Method method = null;
            String propertyName = stringUtils.columnToProperty(columnName); //属性名称

            try {
                field = clazz.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            } catch (SecurityException e1) {
                e1.printStackTrace();
            }
            //获取JavaBean中的字段
            String fieldType = field.toString().split(" ")[1]; //获取该字段的类型
            //System.out.println(fieldType);
            Object value = map.get(columnName);
            if(value == null){
                continue;
            }
            /**获取set方法的名字* */
            String setMethodName = "set" + stringUtils.upperCaseFirstCharacter(propertyName);
            //System.out.println(setMethodName);
            try {
                /**获取值的类型* */
                String valueType = value.getClass().getName();

                /**查看类型是否匹配* */
                if(!fieldType.equalsIgnoreCase(valueType)){
                    //System.out.println("类型不匹配");
                    if(fieldType.equalsIgnoreCase("java.lang.Integer")){
                        value = Integer.parseInt(String.valueOf(value));
                    }else if(fieldType.equalsIgnoreCase("java.lang.String")){
                        value = String.valueOf(value);
                    }
                }

                /**获取set方法* */
                //System.out.println(valueType);
                method = clazz.getDeclaredMethod(setMethodName,Class.forName(fieldType));
                /**执行set方法* */
                method.invoke(obj, value);
            }catch(Exception e){
                e.printStackTrace();
                /**如果报错，基本上是因为类型不匹配* */
            }
        }
        //System.out.println(obj);
        return obj;
    }

}
