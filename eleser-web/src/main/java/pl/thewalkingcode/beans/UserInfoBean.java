package pl.thewalkingcode.beans;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import pl.thewalkingcode.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@ManagedBean
@SessionScoped
public class UserInfoBean implements Serializable {

    @Inject
    private UserService userService;

    private String username;

    public UserInfoBean() {
        Subject subject = SecurityUtils.getSubject();
        username = (String) subject.getPrincipal();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
