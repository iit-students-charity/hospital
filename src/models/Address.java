package models;

class Address {
    private String city;
    private String street;
    private String buildingNumber;

    Address(String city, String street, String buildingNumber) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }

    Address() {
        this("", "", "");
    }

    String getFullAddress() {
        if (city.isEmpty()) {
            return street + " " + buildingNumber;
        }
        return city + ", " + street + " " + buildingNumber;
    }

    String getCity() {
        return city;
    }

    String getStreet() {
        return street;
    }

    String getBuildingNumber() {
        return buildingNumber;
    }

    void setCity(String city) {
        this.city = city;
    }

    void setStreet(String street) {
        this.street = street;
    }

    void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
