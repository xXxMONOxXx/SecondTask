package by.mishastoma.builder;

import by.mishastoma.entity.*;
import by.mishastoma.exception.XmlParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DevicesDomBuilder {

    private static final Logger logger = LogManager.getLogger();

    private Devices devices;

    private DocumentBuilder documentBuilder;

    public DevicesDomBuilder() throws XmlParseException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        devices = new Devices();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Document builder cannot be created");
            throw new XmlParseException("Document builder cannot be created" + ' ' + e);
        }
    }


    public void buildDevicesList(String filepath) throws XmlParseException {
        Document document;
        try {
            document = documentBuilder.parse(filepath);
            Element root = document.getDocumentElement();
            addCpus(root.getElementsByTagName(DevicesXmlTag.CPU.getValue()));
            addGpus(root.getElementsByTagName(DevicesXmlTag.GPU.getValue()));
            addHeadphones(root.getElementsByTagName(DevicesXmlTag.HEADPHONES.getValue()));
            addKeyboards(root.getElementsByTagName(DevicesXmlTag.KEYBOARD.getValue()));
            addMouses(root.getElementsByTagName(DevicesXmlTag.MOUSE.getValue()));

        } catch (IOException e) {
            logger.error("File cannot be opened or read {}", filepath);
            throw new XmlParseException("Failed to open/read file" + filepath + ' ' + e);
        } catch (SAXException e) {
            logger.error("Filed cannot be parsed {}", filepath);
            throw new XmlParseException("Failed to parse XML file " + filepath + ' ' + e);
        }
    }

    private Cpu cpuBuilder(Element device) {
        return new Cpu.Builder()
                .withName(getElementTextContent(device, DevicesXmlTag.NAME.getValue()))
                .withOrigin(getElementTextContent(device, DevicesXmlTag.ORIGIN.getValue()))
                .withSerialNumber(device.getAttribute(DevicesXmlTag.SERIAL_NUMBER.getValue()))
                .withPrice(Integer.parseInt(getElementTextContent(device, DevicesXmlTag.PRICE.getValue())))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, DevicesXmlTag.IS_CRITICAL.getValue())))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, DevicesXmlTag.DATE_OF_MANUFACTURE.getValue())))
                .withPowerUsage(Integer.parseInt(getElementTextContent(device, DevicesXmlTag.POWER_USAGE.getValue())))
                .withHasFan(Boolean.parseBoolean(getElementTextContent(device, DevicesXmlTag.PRESENCE_OF_FAN.getValue())))
                .build();
    }

    private Gpu gpuBuilder(Element device) {
        return new Gpu.Builder()
                .withName(getElementTextContent(device, DevicesXmlTag.NAME.getValue()))
                .withOrigin(getElementTextContent(device, DevicesXmlTag.ORIGIN.getValue()))
                .withSerialNumber(device.getAttribute(DevicesXmlTag.SERIAL_NUMBER.getValue()))
                .withPrice(Integer.parseInt(getElementTextContent(device, DevicesXmlTag.PRICE.getValue())))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, DevicesXmlTag.IS_CRITICAL.getValue())))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, DevicesXmlTag.DATE_OF_MANUFACTURE.getValue())))
                .withPowerUsage(Integer.parseInt(getElementTextContent(device, DevicesXmlTag.POWER_USAGE.getValue())))
                .withHasFan(Boolean.parseBoolean(getElementTextContent(device, DevicesXmlTag.PRESENCE_OF_FAN.getValue())))
                .withPorts(portsBuilder((Element) device.getElementsByTagName(DevicesXmlTag.PORTS.getValue()).item(0)))
                .build();
    }

    private Headphones headphonesBuilder(Element device) {
        return new Headphones.Builder()
                .withName(getElementTextContent(device, DevicesXmlTag.NAME.getValue()))
                .withOrigin(getElementTextContent(device, DevicesXmlTag.ORIGIN.getValue()))
                .withSerialNumber(device.getAttribute(DevicesXmlTag.SERIAL_NUMBER.getValue()))
                .withPrice(Integer.parseInt(getElementTextContent(device, DevicesXmlTag.PRICE.getValue())))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, DevicesXmlTag.IS_CRITICAL.getValue())))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, DevicesXmlTag.DATE_OF_MANUFACTURE.getValue())))
                .withPort(getElementTextContent(device, DevicesXmlTag.PORT.getValue()))
                .withPeripheralType(device.getAttribute(DevicesXmlTag.PERIPHERAL_TYPE.getValue()))
                .build();

    }

    private Keyboard keyboardBuilder(Element device) {
        return new Keyboard.Builder()
                .withName(getElementTextContent(device, DevicesXmlTag.NAME.getValue()))
                .withOrigin(getElementTextContent(device, DevicesXmlTag.ORIGIN.getValue()))
                .withSerialNumber(device.getAttribute(DevicesXmlTag.SERIAL_NUMBER.getValue()))
                .withPrice(Integer.parseInt(getElementTextContent(device, DevicesXmlTag.PRICE.getValue())))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, DevicesXmlTag.IS_CRITICAL.getValue())))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, DevicesXmlTag.DATE_OF_MANUFACTURE.getValue())))
                .withPorts(portsBuilder((Element) device.getElementsByTagName(DevicesXmlTag.PORTS.getValue()).item(0)))
                .withPeripheralType(device.getAttribute(DevicesXmlTag.PERIPHERAL_TYPE.getValue()))
                .build();
    }

    private Mouse mouseBuilder(Element device) {
        return new Mouse.Builder()
                .withName(getElementTextContent(device, DevicesXmlTag.NAME.getValue()))
                .withOrigin(getElementTextContent(device, DevicesXmlTag.ORIGIN.getValue()))
                .withSerialNumber(device.getAttribute(DevicesXmlTag.SERIAL_NUMBER.getValue()))
                .withPrice(Integer.parseInt(getElementTextContent(device, DevicesXmlTag.PRICE.getValue())))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, DevicesXmlTag.IS_CRITICAL.getValue())))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, DevicesXmlTag.DATE_OF_MANUFACTURE.getValue())))
                .withPort(getElementTextContent(device, DevicesXmlTag.PORT.getValue()))
                .withPeripheralType(device.getAttribute(DevicesXmlTag.PERIPHERAL_TYPE.getValue()))
                .build();
    }

    private void addCpus(NodeList cpusList) {
        for (int i = 0; i < cpusList.getLength(); i++) {
            devices.addDevice(cpuBuilder((Element) cpusList.item(i)));
        }
    }

    private void addGpus(NodeList gpusList) {
        for (int i = 0; i < gpusList.getLength(); i++) {
            devices.addDevice(gpuBuilder((Element) gpusList.item(i)));
        }
    }

    private void addHeadphones(NodeList headphonesList) {
        for (int i = 0; i < headphonesList.getLength(); i++) {
            devices.addDevice(headphonesBuilder((Element) headphonesList.item(i)));
        }
    }

    private void addKeyboards(NodeList keyboardsList) {
        for (int i = 0; i < keyboardsList.getLength(); i++) {
            devices.addDevice(keyboardBuilder((Element) keyboardsList.item(i)));
        }
    }

    private void addMouses(NodeList mousesList) {
        for (int i = 0; i < mousesList.getLength(); i++) {
            devices.addDevice(mouseBuilder((Element) mousesList.item(i)));
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private static List<String> portsBuilder(Element portsElement) {
        List<String> ports = new ArrayList<>();
        NodeList nodeList = portsElement.getElementsByTagName(DevicesXmlTag.PORT.getValue());
        for (int i = 0; i < nodeList.getLength(); i++) {
            ports.add(nodeList.item(i).getTextContent());
        }
        return ports;
    }
}
