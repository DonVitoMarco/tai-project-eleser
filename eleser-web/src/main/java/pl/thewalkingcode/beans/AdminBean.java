package pl.thewalkingcode.beans;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import pl.thewalkingcode.model.Device;
import pl.thewalkingcode.repository.DeviceRepository;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {

    private List<Device> deviceList;

    @Inject
    private DeviceRepository deviceRepository;

    @ManagedProperty(value = "#{userInfoBean}")
    private UserInfoBean userInfoBean;

    public AdminBean() {
    }

    @PostConstruct
    private void init() {
        deviceList = deviceRepository.findAll();
    }

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Device Edited", ((Device) event.getObject()).getSerialNumber());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Device) event.getObject()).getSerialNumber());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


}
