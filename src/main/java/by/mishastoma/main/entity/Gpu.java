package by.mishastoma.main.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gpu extends ComputerComponent {

    private int powerUsage;

    private boolean hasFan;

    private List<String> ports = new ArrayList<>();

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public int getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(int powerUsage) {
        this.powerUsage = powerUsage;
    }

    public boolean isHasFan() {
        return hasFan;
    }

    public void setHasFan(boolean hasFan) {
        this.hasFan = hasFan;
    }

    public static class Builder {
        private Gpu newGpu;

        public Builder() {
            newGpu = new Gpu();
        }

        public Builder withName(String name) {
            newGpu.name = name;
            return this;
        }

        public Builder withOrigin(String origin) {
            newGpu.origin = origin;
            return this;
        }

        public Builder withSerialNumber(String serialNumber) {
            newGpu.serialNumber = serialNumber;
            return this;
        }

        public Builder withPrice(int price) {
            newGpu.price = price;
            return this;
        }

        public Builder withIsCritical(boolean isCritical) {
            newGpu.isCritical = isCritical;
            return this;
        }

        public Builder withManufactureDate(LocalDate manufactureDate){
            newGpu.manufactureDate=manufactureDate;
            return this;
        }

        public Builder withPowerUsage (int powerUsage){
            newGpu.powerUsage = powerUsage;
            return this;
        }

        public Builder withHasFan(boolean hasFan){
            newGpu.hasFan = hasFan;
            return this;
        }

        public ComputerComponent build() {
            return newGpu;
        }
    }
}
