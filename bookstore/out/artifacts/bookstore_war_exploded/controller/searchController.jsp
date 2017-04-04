<%--
  Created by IntelliJ IDEA.
  User: jia
  Date: 3/27/17
  Time: 4:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,service.searchService,util.stringUtils,bean.*"%>
<%
    //request code
//request.setCharacterEncoding("UTF-8");
//get parameters from index.jsp
    String value = request.getParameter("value");
    String input = request.getParameter("input");
    if (stringUtils.isEmpty(value) || stringUtils.isEmpty(input) || value == "0") {
        out.print("-1");
    }   else {
        searchService searchService = new searchService();
        List list = searchService.getBook(value, input);
        if(list == null)
            out.print("-2");
        else {
            System.out.println(list);
            out.print(list);
        }
    }
%>
%>
