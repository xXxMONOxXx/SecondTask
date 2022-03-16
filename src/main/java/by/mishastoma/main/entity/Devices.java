package by.mishastoma.main.entity;

import by.mishastoma.main.exception.EntityException;

import java.util.ArrayList;
import java.util.List;

public class Devices {

    private List<ComputerComponent> ComputerComponents;

    public Devices(){
        ComputerComponents = new ArrayList<>();
    }

    public void addDevice(ComputerComponent computerComponent){
        this.ComputerComponents.add(computerComponent);
    }

    public ComputerComponent get(int index) throws EntityException {
        if(index<0 || index> ComputerComponents.size()){
            throw new EntityException("Index is out of range.");
        }
        return ComputerComponents.get(index);
    }

}
