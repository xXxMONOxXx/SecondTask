package by.mishastoma.builder;

import by.mishastoma.entity.*;
import by.mishastoma.exception.XmlParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DevicesStaxBuilder {

    private static final Logger logger = LogManager.getLogger();

    private DeviceBuilders deviceBuilders;
    private Devices devices;
    private XMLInputFactory inputFactory;
    private XMLEvent event;
    private XMLEventReader reader;

    public DevicesStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        deviceBuilders = DeviceBuilders.getInstance();
        devices = new Devices();
    }

    public Devices getDevices() {
        return devices;
    }

    public void buildDevices(String filePath) throws XmlParseException{
        try {
            reader = inputFactory.createXMLEventReader(new FileInputStream(filePath));
            while (reader.hasNext()) {
                event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String name = startElement.getName().getLocalPart();
                    if (isDevice(name)) {
                        Attribute serialNumber = startElement.getAttributeByName(
                                new QName(DevicesXmlTag.SERIAL_NUMBER.getValue()));
                        if (serialNumber != null) {
                            deviceBuilders.currentSerialNumber = serialNumber.getValue();
                        }

                        Attribute peripheralType = startElement.getAttributeByName(
                                new QName(DevicesXmlTag.PERIPHERAL_TYPE.getValue()));
                        if (peripheralType != null) {
                            deviceBuilders.currentPeripheralType = peripheralType.getValue();
                        }
                    } else {
                        updateProperty(name);
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String name = endElement.getName().getLocalPart();
                    if (isDevice(name)) {
                        if (DevicesXmlTag.CPU.getValue().equals(name)) {
                            devices.addDevice(deviceBuilders.buildCpu());
                        } else if (DevicesXmlTag.GPU.getValue().equals(name)) {
                            devices.addDevice(deviceBuilders.buildGpu());
                        } else if (DevicesXmlTag.HEADPHONES.getValue().equals(name)) {
                            devices.addDevice(deviceBuilders.buildHeadphones());
                        } else if (DevicesXmlTag.KEYBOARD.getValue().equals(name)) {
                            devices.addDevice(deviceBuilders.buildKeyboard());
                        } else if (DevicesXmlTag.MOUSE.getValue().equals(name)) {
                            devices.addDevice(deviceBuilders.buildMouse());
                        }
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("Error while parsing file: {}", filePath);
            throw new XmlParseException(e.getMessage());
        } catch (IOException e) {
            logger.error("Couldn't read from file: {} ", filePath);
            throw new XmlParseException("Couldn't read from file" + filePath);
        }
    }

    private void updateProperty(String name) throws XMLStreamException {
        try {
            if (DevicesXmlTag.NAME.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentName = event.asCharacters().getData();
            } else if (DevicesXmlTag.SERIAL_NUMBER.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentSerialNumber = event.asCharacters().getData();
            } else if (DevicesXmlTag.ORIGIN.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentOrigin = event.asCharacters().getData();
            } else if (DevicesXmlTag.PRICE.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentPrice = Integer.parseInt(event.asCharacters().getData());
            } else if (DevicesXmlTag.IS_CRITICAL.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentIsCritical = Boolean.parseBoolean(event.asCharacters().getData());
            } else if (DevicesXmlTag.DATE_OF_MANUFACTURE.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentDateOfManufacture = LocalDate.parse(event.asCharacters().getData());
            } else if (DevicesXmlTag.POWER_USAGE.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentPowerUsage = Integer.parseInt(event.asCharacters().getData());
            } else if (DevicesXmlTag.PRESENCE_OF_FAN.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentPresenceOfFan = Boolean.parseBoolean(event.asCharacters().getData());
            } else if (DevicesXmlTag.PORTS.getValue().equals(name)) {
                deviceBuilders.currentPorts = new ArrayList<>();
            } else if (DevicesXmlTag.PORT.getValue().equals(name)) {
                event = reader.nextEvent();
                deviceBuilders.currentPorts.add(event.asCharacters().getData());
            }
        } catch (XMLStreamException e) {
            logger.error("Invalid data in element {} ", name);
            throw new XMLStreamException("Invalid data in element " + name);
        }
    }

    private boolean isDevice(String name) {
        return DevicesXmlTag.CPU.getValue().equals(name) ||
                DevicesXmlTag.GPU.getValue().equals(name) ||
                DevicesXmlTag.MOUSE.getValue().equals(name) ||
                DevicesXmlTag.KEYBOARD.getValue().equals(name) ||
                DevicesXmlTag.HEADPHONES.getValue().equals(name);
    }
}
