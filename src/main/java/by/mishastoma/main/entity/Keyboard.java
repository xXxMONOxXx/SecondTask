package by.mishastoma.main.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Keyboard extends ComputerComponent {

    private List<String> ports = new ArrayList<>();

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public static class Builder {
        private Keyboard newKeyboard;

        public Builder() {
            newKeyboard = new Keyboard();
        }

        public Builder withName(String name) {
            newKeyboard.name = name;
            return this;
        }

        public Builder withOrigin(String origin) {
            newKeyboard.origin = origin;
            return this;
        }

        public Builder withSerialNumber(String serialNumber) {
            newKeyboard.serialNumber = serialNumber;
            return this;
        }

        public Builder withPrice(int price) {
            newKeyboard.price = price;
            return this;
        }

        public Builder withIsCritical(boolean isCritical) {
            newKeyboard.isCritical = isCritical;
            return this;
        }

        public Builder withManufactureDate(LocalDate manufactureDate) {
            newKeyboard.manufactureDate = manufactureDate;
            return this;
        }

        public Builder withPorts(List<String> ports) {
            newKeyboard.ports = ports;
            return this;
        }

        public ComputerComponent build() {
            return newKeyboard;
        }
    }
}
