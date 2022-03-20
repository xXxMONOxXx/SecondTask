package by.mishastoma.main;

//src/main/resources/data/computerComponents.xml

import by.mishastoma.main.builder.DevicesDomBuilder;
import by.mishastoma.main.validator.XmlFileValidator;

public class App
{

    public static void main( String[] args ) {
        try {
            DevicesDomBuilder builder = new DevicesDomBuilder();
            builder.buildDevicesList("src/main/resources/data/computerComponents.xml");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
