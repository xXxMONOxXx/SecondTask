package by.mishastoma.main.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Keyboard extends AComputerComponent {

    private List<String> ports = new ArrayList<>();

    private String peripheralType;

    public String getPeripheralType() {
        return peripheralType;
    }

    public void setPeripheralType(String peripheralType) {
        this.peripheralType = peripheralType;
    }

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public void addPort(String port) {
        this.ports.add(port);
    }

    @Override
    public String toString() {
        return "Keyboard{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", isCritical=" + isCritical +
                ", manufactureDate=" + manufactureDate +
                ", ports=" + ports +
                ", peripheralType='" + peripheralType + '\'' +
                '}';
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

        public Builder withPeripheralType(String peripheralType) {
            newKeyboard.peripheralType = peripheralType;
            return this;
        }

        public Keyboard build() {
            return newKeyboard;
        }
    }
}
