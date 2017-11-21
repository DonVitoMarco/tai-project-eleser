package pl.thewalkingcode.beans;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import pl.thewalkingcode.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;


@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    @Inject
    private UserService userService;

    private String username;

    public HelloBean() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.hasRole("admin"));
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
