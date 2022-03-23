package by.mishastoma.entity;

import java.time.LocalDate;

public class Mouse extends AComputerComponent {

    private String port;

    private String peripheralType;

    public String getPeripheralType() {
        return peripheralType;
    }

    public void setPeripheralType(String peripheralType) {
        this.peripheralType = peripheralType;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", isCritical=" + isCritical +
                ", manufactureDate=" + manufactureDate +
                ", port='" + port + '\'' +
                ", peripheralType='" + peripheralType + '\'' +
                '}';
    }

    public static class Builder {
        private Mouse newMouse;

        public Builder() {
            newMouse = new Mouse();
        }

        public Builder withName(String name) {
            newMouse.name = name;
            return this;
        }

        public Builder withOrigin(String origin) {
            newMouse.origin = origin;
            return this;
        }

        public Builder withSerialNumber(String serialNumber) {
            newMouse.serialNumber = serialNumber;
            return this;
        }

        public Builder withPrice(int price) {
            newMouse.price = price;
            return this;
        }

        public Builder withIsCritical(boolean isCritical) {
            newMouse.isCritical = isCritical;
            return this;
        }

        public Builder withManufactureDate(LocalDate manufactureDate) {
            newMouse.manufactureDate = manufactureDate;
            return this;
        }

        public Builder withPort(String port) {
            newMouse.port = port;
            return this;
        }

        public Builder withPeripheralType(String peripheralType){
            newMouse.peripheralType = peripheralType;
            return this;
        }

        public Mouse build() {
            return newMouse;
        }
    }
}
