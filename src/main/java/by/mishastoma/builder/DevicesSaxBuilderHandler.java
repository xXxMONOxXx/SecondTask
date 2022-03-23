package by.mishastoma.builder;

import by.mishastoma.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;

public class DevicesSaxBuilderHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();

    private DeviceBuilders deviceBuilders;
    private DevicesXmlTag currentXmlTag;
    private DevicesXmlTag currentXmlDeviceTag;
    private EnumSet<DevicesXmlTag> withText;
    private Devices devices;

    public Devices getDevices(){
        return devices;
    }

    public DevicesSaxBuilderHandler(){
        devices = new Devices();
        deviceBuilders = DeviceBuilders.getInstance();
        deviceBuilders.currentPorts = new ArrayList<>();
        withText = EnumSet.range(DevicesXmlTag.CPU, DevicesXmlTag.PERIPHERAL_TYPE);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(DevicesXmlTag.CPU.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.CPU;
            deviceBuilders.currentSerialNumber = attributes.getValue(0);
        }
        else if(DevicesXmlTag.GPU.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.GPU;
            deviceBuilders.currentSerialNumber = attributes.getValue(0);
        }
        else if(DevicesXmlTag.HEADPHONES.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.HEADPHONES;
            deviceBuilders.currentSerialNumber = attributes.getValue(0);
            deviceBuilders.currentPeripheralType = attributes.getValue(1);
        }
        else if(DevicesXmlTag.KEYBOARD.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.KEYBOARD;
            deviceBuilders.currentSerialNumber = attributes.getValue(0);
            deviceBuilders.currentPeripheralType = attributes.getValue(1);
        }
        else if(DevicesXmlTag.MOUSE.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.MOUSE;
            deviceBuilders.currentSerialNumber = attributes.getValue(0);
            deviceBuilders.currentPeripheralType = attributes.getValue(1);
        }
        else if(!DevicesXmlTag.DEVICES.getValue().equals(qName)){
            try {
                DevicesXmlTag temp = DevicesXmlTag.valueOf(qName.toUpperCase().replace('-', '_'));
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
            }
            catch (Exception e){
                logger.error("Invalid element {} in xml file", qName);
                throw new SAXException(String.format("Invalid element %s in xml file", qName));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(currentXmlDeviceTag.getValue().equals(qName)) {
            switch (currentXmlDeviceTag) {
                case CPU: {
                    devices.addDevice(deviceBuilders.buildCpu());
                    break;
                }
                case GPU: {
                    devices.addDevice(deviceBuilders.buildGpu());
                    break;
                }
                case HEADPHONES: {
                    devices.addDevice(deviceBuilders.buildHeadphones());
                    break;
                }
                case KEYBOARD: {
                    devices.addDevice(deviceBuilders.buildKeyboard());
                    break;
                }
                case MOUSE: {
                    devices.addDevice(deviceBuilders.buildMouse());
                    break;
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        if(currentXmlTag!=null){
            switch (currentXmlTag){
                case NAME : {
                    deviceBuilders.currentName = data;
                    break;
                }
                case ORIGIN:{
                    deviceBuilders.currentOrigin = data;
                    break;
                }
                case PRICE:{
                    deviceBuilders.currentPrice = Integer.parseInt(data);
                    break;
                }
                case IS_CRITICAL:{
                    deviceBuilders.currentIsCritical = Boolean.parseBoolean(data);
                    break;
                }
                case DATE_OF_MANUFACTURE:{
                    deviceBuilders.currentDateOfManufacture = LocalDate.parse(data);
                    break;
                }
                case POWER_USAGE:{
                    deviceBuilders.currentPowerUsage = Integer.parseInt(data);
                    break;
                }
                case PORTS:{
                    deviceBuilders.currentPorts = new ArrayList<>();
                    break;
                }
                case PORT:{
                    deviceBuilders.currentPorts.add(data);
                    break;
                }
                case PRESENCE_OF_FAN:{
                    deviceBuilders.currentPresenceOfFan = Boolean.parseBoolean(data);
                    break;
                }
            }
        }
        currentXmlTag = null;
    }

}
