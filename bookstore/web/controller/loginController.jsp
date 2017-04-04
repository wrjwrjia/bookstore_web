<%--
  Created by IntelliJ IDEA.
  User: jia
  Date: 3/13/17
  Time: 9:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,service.loginService,util.stringUtils,bean.*" %>

<%
//request code
//request.setCharacterEncoding("UTF-8");
//get parameters from index.jsp
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (stringUtils.isEmpty(username) || stringUtils.isEmpty(password)) {
            out.print("-1");
        }   else {
            loginService loginService = new loginService();
            User user = loginService.getUser(username);
            if (user == null) {
                out.print("-2");
            } else if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
                out.print("-3");
            } else {
                out.print("1");
                session.setAttribute("user", user);
                session.setAttribute("username", user.getUsername());
            }
        }
%>