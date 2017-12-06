package pl.thewalkingcode.beans;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import pl.thewalkingcode.model.Device;
import pl.thewalkingcode.repository.DeviceRepository;
import pl.thewalkingcode.service.DeviceService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {

    private List<Device> deviceList;

    private String newSerialNumber;
    private String newName;
    private Date newWarrantyDate;

    @Inject
    private DeviceService deviceService;

    @ManagedProperty(value = "#{userInfoBean}")
    private UserInfoBean userInfoBean;

    public AdminBean() {
    }

    @PostConstruct
    private void init() {
        newSerialNumber = "";
        newName = "";
        newWarrantyDate = new Date();
        deviceList = deviceService.findAll();
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

    public String getNewSerialNumber() {
        return newSerialNumber;
    }

    public void setNewSerialNumber(String newSerialNumber) {
        this.newSerialNumber = newSerialNumber;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public Date getNewWarrantyDate() {
        return newWarrantyDate;
    }

    public void setNewWarrantyDate(Date newWarrantyDate) {
        this.newWarrantyDate = newWarrantyDate;
    }

    public void onRowEdit(RowEditEvent event) {
        Device updateDevice = (Device) event.getObject();
        updateDevice.setUpdateDate(new Date());
        boolean success = deviceService.update(updateDevice);

        if (success) {
            FacesMessage msg = new FacesMessage("Device Edited", ((Device) event.getObject()).getSerialNumber());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Device) event.getObject()).getSerialNumber());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void addNewDevice() {
        Device device = new Device();
        device.setName(newName);
        device.setSerialNumber(newSerialNumber);
        device.setWarrantyDate(newWarrantyDate);
        device.setStatus("IN PROGRESS");
        device.setUpdateDate(new Date());
        deviceService.save(device);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Add " + device.getName() + " " + device.getSerialNumber()));

        deviceList = deviceService.findAll();

        newWarrantyDate = new Date();
        newName = "";
        newSerialNumber = "";
    }

    public void deleteDevice(Device deviceToRemove) {
        deviceService.delete(deviceToRemove.getId());
        deviceList = deviceService.findAll();
    }

}
