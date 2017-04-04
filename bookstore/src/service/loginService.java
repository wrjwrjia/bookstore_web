package service;

/**
 * Created by jia on 3/26/17.
 */
import bean.User;
import util.DataBaseUtils;

public class loginService {

    static {
        DataBaseUtils.getConnection();
    }
    public User getUser(String username){
        String sql = "select * from user where username = ?";
        User user = DataBaseUtils.queryForBean(sql, User.class, username);
        if(user == null){
            return null;
        }
        //System.out.println(user);
        return user;
    }
}
