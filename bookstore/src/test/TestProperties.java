package test;

import bean.Book;
import service.*;
import util.DataBaseUtils;
import util.stringUtils;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Properties;

import static java.lang.System.out;

/**
 * Created by jia on 3/23/17.
 */
public class TestProperties {
    public static void main(String[] args) throws SQLException {
        DataBaseUtils.getConnection();
        DataBaseUtils.config("jdbc.properties");
        searchService searchService = new searchService();
        List list = searchService.getBook("1", "12345ABCD");
        System.out.println(list);
    }
}
