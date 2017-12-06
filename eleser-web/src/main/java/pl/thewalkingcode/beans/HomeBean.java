package pl.thewalkingcode.beans;

import pl.thewalkingcode.model.Device;
import pl.thewalkingcode.service.DeviceService;
import pl.thewalkingcode.utils.NavigationBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class HomeBean implements Serializable {

    @Inject
    private DeviceService deviceService;

    @ManagedProperty(value = "#{infoBean}")
    private InfoBean infoBean;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    private String searchNumber;
    private Device searchDevice;

    public HomeBean() {
        searchNumber = "";
        searchDevice = null;
    }

    public String getSearchNumber() {
        return searchNumber;
    }

    public void setSearchNumber(String searchNumber) {
        this.searchNumber = searchNumber;
    }

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

    public void search() {
        searchDevice = deviceService.findBySerialNumber(searchNumber);
        if (searchDevice == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No result!", ""));
        }
        searchNumber = "";
    }

    public Device getSearchDevice() {
        return searchDevice;
    }

    public void setSearchDevice(Device searchDevice) {
        this.searchDevice = searchDevice;
    }
}
