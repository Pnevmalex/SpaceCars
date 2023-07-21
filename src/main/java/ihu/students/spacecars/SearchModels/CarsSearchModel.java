package ihu.students.spacecars.SearchModels;

public class CarsSearchModel {
    Integer carsID;
    String make, model;
    Integer year, price;

    public CarsSearchModel(Integer carsID, String make, String model, Integer year, Integer price) {
        this.carsID = carsID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Integer getCarsID() {
        return carsID;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getPrice() {
        return price;
    }

}
