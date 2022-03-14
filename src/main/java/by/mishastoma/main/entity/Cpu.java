package by.mishastoma.main.entity;

public class Cpu extends ComputerComponent{

    private int powerUsage;

    private boolean hasFan;

    public Cpu(int serialNumber, String name, String origin, int price, boolean isCritical, int powerUsage, boolean hasFan) {
        super(serialNumber, name, origin, price, isCritical);
        this.powerUsage=powerUsage;
        this.hasFan=hasFan;
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
}
