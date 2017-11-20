package pl.thewalkingcode.beans;

import pl.thewalkingcode.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class HelloBean {

    @Inject
    private UserService userService;

}
