package by.mishastoma.main;

import by.mishastoma.main.entity.Devices;
import by.mishastoma.main.parser.DevicesSaxParser;

public class App
{

    public static void main( String[] args ) {
        DevicesSaxParser parser = new DevicesSaxParser();
        try {
            parser.buildDevicesList("src/main/resources/data/computerComponents.xml");
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
        Devices devices = parser.getDevices();
        for (int i=0;i<devices.size();i++) {
            try {
                System.out.println(devices.get(i).toString());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
