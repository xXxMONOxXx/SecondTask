package by.mishastoma.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Keyboard extends ComputerComponent{

    private List<String> ports = new ArrayList<>();

    public Keyboard(int serialNumber, String name, String origin, int price, boolean isCritical, List<String> ports) {
        super(serialNumber, name, origin, price, isCritical);
        this.ports=ports;
    }

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }
}
