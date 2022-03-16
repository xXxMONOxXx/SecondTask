package by.mishastoma.main.entity;

import java.time.LocalDate;

public class Mouse extends ComputerComponent {

    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

        public ComputerComponent build() {
            return newMouse;
        }
    }
}
