package pl.thewalkingcode.utils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ApplicationScoped
@ManagedBean(name = "navigationBean", eager = true)
public class NavigationBean implements Serializable {

    public String goToPage(String page) {
        return page;
    }

}
