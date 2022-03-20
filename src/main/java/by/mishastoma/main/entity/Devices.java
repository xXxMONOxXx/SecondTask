package by.mishastoma.main.entity;

import by.mishastoma.main.exception.EntityException;
import java.util.ArrayList;
import java.util.List;

public class Devices {

    private List<AComputerComponent> devices;

    public Devices(){
        devices = new ArrayList<>();
    }

    public void addDevice(AComputerComponent device) {
        this.devices.add(device);
    }

    public AComputerComponent get(int index) throws EntityException {
        if (index < 0 || index > devices.size()) {
            throw new EntityException("Index is out of range.");
        }
        return devices.get(index);
    }

}
