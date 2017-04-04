package service;

import bean.Book;
import util.DataBaseUtils;

import java.util.List;

/**
 * Created by jia on 3/27/17.
 */

public class searchService {
    static {
        DataBaseUtils.getConnection();
    }

    public List getBook(Object...objects){
        String sql = null;
        if(objects[0] == "1") {
            sql = "select b.ISBN, ba.author, b.name, c.major, bs.location, cl.addedDate" +
                    " from Book b, BookAuthor ba, Category c, Bookstore bs, Classification cl" +
                    " where b.partOf = bs.id" +
                    " and c.partOf = bs.id" +
                    " and ba.book = b.ISBN" +
                    " and cl.belongs = b.ISBN" +
                    " and cl.isBelongedTo = c.id" +
                    " and b.ISBN = ?";
        }
        else if(objects[0] == "2") {
            sql = "select b.ISBN, ba.author, b.name, c.major, bs.location, cl.addedDate" +
                    " from Book b, BookAuthor ba, Category c, Bookstore bs, Classification cl" +
                    "  where b.partOf = bs.id" +
                    "  and c.partOf = bs.id" +
                    "  and ba.book = b.ISBN" +
                    "  and cl.belongs = b.ISBN" +
                    "  and cl.isBelongedTo = c.id" +
                    " and b.name = ?";
        }
        else {
            sql = "select b.ISBN, ba.author, b.name, c.major, bs.location, cl.addedDate" +
                    " from Book b, BookAuthor ba, Category c, Bookstore bs, Classification cl" +
                    " where b.partOf = bs.id" +
                    " and c.partOf = bs.id" +
                    " and ba.book = b.ISBN" +
                    " and cl.belongs = b.ISBN" +
                    " and cl.isBelongedTo = c.id" +
                    " and ba.author = ?";
        }
        System.out.println(sql);
        System.out.println(objects[1]);
        List list = DataBaseUtils.queryForList(sql, objects[1]);
        if(list == null){
            return null;
        }
        System.out.println(list);
        return list;
    }
}
