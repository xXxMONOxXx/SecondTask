package by.mishastoma.main.entity;

import java.util.Objects;

public abstract class ComputerComponent {

    private int serialNumber;
    private String name;
    private String origin;
    private int price;
    private boolean isCritical;

    protected ComputerComponent(int serialNumber, String name, String origin, int price, boolean isCritical) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.isCritical = isCritical;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
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

    @Override
    public String toString() {
        return "ComputerComponent{" +
                "serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", isCritical=" + isCritical +
                '}';
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
                origin.equals(that.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, name, origin, price, isCritical);
    }
}
