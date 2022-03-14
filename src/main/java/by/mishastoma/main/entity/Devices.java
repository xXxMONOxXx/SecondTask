package by.mishastoma.main.entity;

import java.util.ArrayList;
import java.util.List;

public class Devices {

    private List<ComputerComponent> computerComponents = new ArrayList<>();

    public Devices(){

    }

    public void addDevice(ComputerComponent computerComponent){
        this.computerComponents.add(computerComponent);
    }

    public ComputerComponent get(int index){
        if(index<0 || index>computerComponents.size()){

        }
        return computerComponents.get(index);
    }

}
