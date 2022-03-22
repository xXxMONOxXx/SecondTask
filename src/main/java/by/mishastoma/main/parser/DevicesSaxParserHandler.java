package by.mishastoma.main.parser;

import by.mishastoma.main.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DevicesSaxParserHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();
    private DevicesXmlTag currentXmlTag;
    private DevicesXmlTag currentXmlDeviceTag;
    private EnumSet<DevicesXmlTag> withText;
    private Devices devices;

    private String currentName;
    private int currentPrice;
    private String currentSerialNumber;
    private String currentOrigin;
    private boolean currentIsCritical;
    private LocalDate currentDateOfManufacture;
    private int currentPowerUsage;
    private boolean currentPresenceOfFan;
    private List<String> currentPorts;
    private String currentPeripheralType;

    public Devices getDevices(){
        return devices;
    }

    public DevicesSaxParserHandler(){
        devices = new Devices();
        currentPorts = new ArrayList<>();
        withText = EnumSet.range(DevicesXmlTag.CPU, DevicesXmlTag.PERIPHERAL_TYPE);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(DevicesXmlTag.CPU.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.CPU;
            currentSerialNumber = attributes.getValue(0);
        }
        else if(DevicesXmlTag.GPU.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.GPU;
            currentSerialNumber = attributes.getValue(0);
        }
        else if(DevicesXmlTag.HEADPHONES.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.HEADPHONES;
            currentSerialNumber = attributes.getValue(0);
            currentPeripheralType = attributes.getValue(1);
        }
        else if(DevicesXmlTag.KEYBOARD.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.KEYBOARD;
            currentSerialNumber = attributes.getValue(0);
            currentPeripheralType = attributes.getValue(1);
        }
        else if(DevicesXmlTag.MOUSE.getValue().equals(qName)){
            currentXmlDeviceTag = DevicesXmlTag.MOUSE;
            currentSerialNumber = attributes.getValue(0);
            currentPeripheralType = attributes.getValue(1);
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
                    devices.addDevice(buildCpu());
                    break;
                }
                case GPU: {
                    devices.addDevice(buildGpu());
                    break;
                }
                case HEADPHONES: {
                    devices.addDevice(buildHeadphones());
                    break;
                }
                case KEYBOARD: {
                    devices.addDevice(buildKeyboard());
                    break;
                }
                case MOUSE: {
                    devices.addDevice(buildMouse());
                    break;
                }
            }
            logger.info("Added new device, type: {}",  currentXmlDeviceTag.getValue());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        if(currentXmlTag!=null){
            switch (currentXmlTag){
                case NAME : {
                    currentName = data;
                    break;
                }
                case ORIGIN:{
                    currentOrigin = data;
                    break;
                }
                case PRICE:{
                    currentPrice = Integer.parseInt(data);
                    break;
                }
                case IS_CRITICAL:{
                    currentIsCritical = Boolean.parseBoolean(data);
                    break;
                }
                case DATE_OF_MANUFACTURE:{
                    currentDateOfManufacture = LocalDate.parse(data);
                    break;
                }
                case POWER_USAGE:{
                    currentPowerUsage = Integer.parseInt(data);
                    break;
                }
                case PORTS:{
                    currentPorts = new ArrayList<>();
                    break;
                }
                case PORT:{
                    currentPorts.add(data);
                    break;
                }
                case PRESENCE_OF_FAN:{
                    currentPresenceOfFan = Boolean.parseBoolean(data);
                    break;
                }
            }
        }
        currentXmlTag = null;
    }

    private Cpu buildCpu() {
        return new Cpu.Builder()
                .withName(currentName)
                .withOrigin(currentOrigin)
                .withSerialNumber(currentSerialNumber)
                .withPrice(currentPrice)
                .withIsCritical(currentIsCritical)
                .withManufactureDate(currentDateOfManufacture)
                .withPowerUsage(currentPowerUsage)
                .withHasFan(currentPresenceOfFan)
                .build();
    }

    private Gpu buildGpu() {
        return new Gpu.Builder()
                .withName(currentName)
                .withOrigin(currentOrigin)
                .withSerialNumber(currentSerialNumber)
                .withPrice(currentPrice)
                .withIsCritical(currentIsCritical)
                .withManufactureDate(currentDateOfManufacture)
                .withPowerUsage(currentPowerUsage)
                .withHasFan(currentPresenceOfFan)
                .withPorts(currentPorts)
                .build();
    }

    private Headphones buildHeadphones(){
        return new Headphones.Builder()
                .withName(currentName)
                .withOrigin(currentOrigin)
                .withSerialNumber(currentSerialNumber)
                .withPrice(currentPrice)
                .withIsCritical(currentIsCritical)
                .withManufactureDate(currentDateOfManufacture)
                .withPeripheralType(currentPeripheralType)
                .build();
    }

    private Keyboard buildKeyboard(){
        return new Keyboard.Builder()
                .withName(currentName)
                .withOrigin(currentOrigin)
                .withSerialNumber(currentSerialNumber)
                .withPrice(currentPrice)
                .withIsCritical(currentIsCritical)
                .withManufactureDate(currentDateOfManufacture)
                .withPeripheralType(currentPeripheralType)
                .withPorts(currentPorts)
                .build();
    }

    private Mouse buildMouse(){
        return new Mouse.Builder()
                .withName(currentName)
                .withOrigin(currentOrigin)
                .withSerialNumber(currentSerialNumber)
                .withPrice(currentPrice)
                .withIsCritical(currentIsCritical)
                .withManufactureDate(currentDateOfManufacture)
                .withPeripheralType(currentPeripheralType)
                .build();
    }

}
