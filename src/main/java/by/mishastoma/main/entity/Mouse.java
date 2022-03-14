package by.mishastoma.main.entity;

public class Mouse extends ComputerComponent{

    private String port;

    public Mouse(int serialNumber, String name, String origin, int price, boolean isCritical, String port) {
        super(serialNumber, name, origin, price, isCritical);
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
