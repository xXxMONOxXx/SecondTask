package by.mishastoma.entity;

public enum DevicesXmlTag {
    DEVICES("devices"),
    CPU("cpu"),
    GPU("gpu"),
    MOUSE("mouse"),
    KEYBOARD("keyboard"),
    HEADPHONES("headphones"),
    SERIAL_NUMBER("serial_number"),
    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),
    IS_CRITICAL("is-critical"),
    DATE_OF_MANUFACTURE("date-of-manufacture"),
    POWER_USAGE("power-usage"),
    PRESENCE_OF_FAN("presence-of-fan"),
    PORTS("ports"),
    PORT("port"),
    PERIPHERAL_TYPE("peripheral-type");

    private String value;

    DevicesXmlTag(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
