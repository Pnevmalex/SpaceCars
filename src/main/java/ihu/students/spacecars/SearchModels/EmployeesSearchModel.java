package ihu.students.spacecars.SearchModels;

public class EmployeesSearchModel {
    String name;
    Integer age;
    String phone, category;

    public EmployeesSearchModel(String name, Integer age, String phone, String category) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getCategory() {
        return category;
    }
}
