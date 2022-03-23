package by.mishastoma.entity;

import java.time.LocalDate;

public class Headphones extends AComputerComponent {

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
        return "Headphones{" +
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
        private Headphones newHeadphones;

        public Builder() {
            newHeadphones = new Headphones();
        }

        public Builder withName(String name) {
            newHeadphones.name = name;
            return this;
        }

        public Builder withOrigin(String origin) {
            newHeadphones.origin = origin;
            return this;
        }

        public Builder withSerialNumber(String serialNumber) {
            newHeadphones.serialNumber = serialNumber;
            return this;
        }

        public Builder withPrice(int price) {
            newHeadphones.price = price;
            return this;
        }

        public Builder withIsCritical(boolean isCritical) {
            newHeadphones.isCritical = isCritical;
            return this;
        }

        public Builder withManufactureDate(LocalDate manufactureDate) {
            newHeadphones.manufactureDate = manufactureDate;
            return this;
        }

        public Builder withPort(String port) {
            newHeadphones.port = port;
            return this;
        }

        public Builder withPeripheralType(String peripheralType){
            newHeadphones.peripheralType = peripheralType;
            return this;
        }

        public Headphones build() {
            return newHeadphones;
        }
    }
}
