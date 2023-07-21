package ihu.students.spacecars.SearchModels;

public class CustomersSearchModel {
    String fullname, phone;
    Integer carID;
    String make, model;

    public CustomersSearchModel(String fullname, String phone, Integer carID, String make, String model) {
        this.fullname = fullname;
        this.phone = phone;
        this.carID = carID;
        this.make = make;
        this.model = model;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
