package pl.thewalkingcode.utils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name = "navigationBean", eager = true)
public class NavigationBean {

    public String goToPage(String page) {
        return page;
    }

}
