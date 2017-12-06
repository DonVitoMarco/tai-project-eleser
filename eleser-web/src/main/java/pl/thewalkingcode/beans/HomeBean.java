package pl.thewalkingcode.beans;

import pl.thewalkingcode.utils.NavigationBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable {

    @ManagedProperty(value = "#{infoBean}")
    private InfoBean infoBean;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    public InfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.infoBean = infoBean;
    }

    public NavigationBean getNavigationBean() {
        return navigationBean;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

}
