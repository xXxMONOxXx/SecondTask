package by.mishastoma.main.entity;

import java.time.LocalDate;

public class Cpu extends ComputerComponent {

    private int powerUsage;

    private boolean hasFan;

    public int getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(int powerUsage) {
        this.powerUsage = powerUsage;
    }

    public boolean hasFan() {
        return hasFan;
    }

    public void setHasFan(boolean hasFan) {
        this.hasFan = hasFan;
    }

        public static class Builder {
            private Cpu newCpu;

            public Builder() {
                newCpu = new Cpu();
            }

            public Builder withName(String name) {
                newCpu.name = name;
                return this;
            }

            public Builder withOrigin(String origin) {
                newCpu.origin = origin;
                return this;
            }

            public Builder withSerialNumber(String serialNumber) {
                newCpu.serialNumber = serialNumber;
                return this;
            }

            public Builder withPrice(int price) {
                newCpu.price = price;
                return this;
            }

            public Builder withIsCritical(boolean isCritical) {
                newCpu.isCritical = isCritical;
                return this;
            }

            public Builder withManufactureDate(LocalDate manufactureDate){
                newCpu.manufactureDate=manufactureDate;
                return this;
            }

            public Builder withPowerUsage (int powerUsage){
                newCpu.powerUsage = powerUsage;
                return this;
            }

            public Builder withHasFan(boolean hasFan){
                newCpu.hasFan = hasFan;
                return this;
            }

            public Cpu build() {
                return newCpu;
            }
        }
}
