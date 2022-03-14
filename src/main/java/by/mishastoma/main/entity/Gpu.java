package by.mishastoma.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Gpu extends ComputerComponent{

    private int powerUsage;

    private boolean hasFan;

    private List<String> ports = new ArrayList<>();

    public Gpu(int serialNumber, String name, String origin, int price, boolean isCritical, int powerUsage,
               boolean hasFan, List<String> ports) {
        super(serialNumber, name, origin, price, isCritical);
        this.powerUsage=powerUsage;
        this.hasFan=hasFan;
        this.ports=ports;
    }

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
}
