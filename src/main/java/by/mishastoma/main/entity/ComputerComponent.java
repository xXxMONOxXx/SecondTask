package by.mishastoma.main.entity;

import java.time.LocalDate;
import java.util.Objects;

abstract class ComputerComponent {

    protected String serialNumber;
    protected String name;
    protected String origin;
    protected int price;
    protected boolean isCritical;
    protected LocalDate manufactureDate;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate dateOfManufacture) {
        this.manufactureDate = dateOfManufacture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerComponent that = (ComputerComponent) o;
        return serialNumber == that.serialNumber &&
                price == that.price &&
                isCritical == that.isCritical &&
                name.equals(that.name) &&
                origin.equals(that.origin) &&
                manufactureDate.equals(that.manufactureDate);
    }

    @Override
    public String toString() {
        return "AbstractComputerComponent{" +
                "serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", isCritical=" + isCritical +
                ", dateOfManufacture=" + manufactureDate +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, name, origin, price, isCritical, manufactureDate);
    }
}
