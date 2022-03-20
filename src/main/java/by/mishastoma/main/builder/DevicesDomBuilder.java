package by.mishastoma.main.builder;

import by.mishastoma.main.entity.*;
import by.mishastoma.main.exception.XmlParseException;
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
            logger.error("Document builder cannot be created.");
            throw new XmlParseException("Document builder cannot be created.");
        }
    }

    private Cpu cpuBuilder(Element device) {
        return new Cpu.Builder()
                .withName(getElementTextContent(device, "name"))
                .withOrigin(getElementTextContent(device, "origin"))
                .withSerialNumber(device.getAttribute("serial-number"))
                .withPrice(Integer.parseInt(getElementTextContent(device, "price")))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, "is-critical")))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, "date-of-manufacture")))
                .withPowerUsage(Integer.parseInt(getElementTextContent(device, "power-usage")))
                .withHasFan(Boolean.parseBoolean(getElementTextContent(device, "presence-of-fan")))
                .build();
    }

    private Gpu gpuBuilder(Element device) {
        return new Gpu.Builder()
                .withName(getElementTextContent(device, "name"))
                .withOrigin(getElementTextContent(device, "origin"))
                .withSerialNumber(device.getAttribute("serial-number"))
                .withPrice(Integer.parseInt(getElementTextContent(device, "price")))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, "is-critical")))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, "date-of-manufacture")))
                .withPowerUsage(Integer.parseInt(getElementTextContent(device, "power-usage")))
                .withHasFan(Boolean.parseBoolean(getElementTextContent(device, "presence-of-fan")))
                .withPorts(portsBuilder((Element) device.getElementsByTagName("ports").item(0)))
                .build();
    }

    private Headphones headphonesBuilder(Element device){
        return new Headphones.Builder()
                .withName(getElementTextContent(device, "name"))
                .withOrigin(getElementTextContent(device, "origin"))
                .withSerialNumber(device.getAttribute("serial-number"))
                .withPrice(Integer.parseInt(getElementTextContent(device, "price")))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, "is-critical")))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, "date-of-manufacture")))
                .withPort(getElementTextContent(device, "port"))
                .withPeripheralType(device.getAttribute("peripheral-type"))
                .build();

    }

    private Keyboard keyboardBuilder(Element device){
        return new Keyboard.Builder()
                .withName(getElementTextContent(device, "name"))
                .withOrigin(getElementTextContent(device, "origin"))
                .withSerialNumber(device.getAttribute("serial-number"))
                .withPrice(Integer.parseInt(getElementTextContent(device, "price")))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, "is-critical")))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, "date-of-manufacture")))
                .withPorts(portsBuilder((Element) device.getElementsByTagName("ports").item(0)))
                .withPeripheralType(device.getAttribute("peripheral-type"))
                .build();
    }

    private Mouse mouseBuilder (Element device){
        return new Mouse.Builder()
                .withName(getElementTextContent(device, "name"))
                .withOrigin(getElementTextContent(device, "origin"))
                .withSerialNumber(device.getAttribute("serial-number"))
                .withPrice(Integer.parseInt(getElementTextContent(device, "price")))
                .withIsCritical(Boolean.parseBoolean(getElementTextContent(device, "is-critical")))
                .withManufactureDate(LocalDate.parse(getElementTextContent(device, "date-of-manufacture")))
                .withPort(getElementTextContent(device, "port"))
                .withPeripheralType(device.getAttribute("peripheral-type"))
                .build();
    }

    private void addCpus(NodeList cpusList){
        for(int i=0;i<cpusList.getLength();i++){
            devices.addDevice(cpuBuilder((Element) cpusList.item(i)));
        }
    }

    private void addGpus(NodeList gpusList){
        for(int i=0;i<gpusList.getLength();i++){
            devices.addDevice(gpuBuilder((Element) gpusList.item(i)));
        }
    }

    private void addHeadphones(NodeList headphonesList){
        for(int i=0;i<headphonesList.getLength();i++){
            devices.addDevice(headphonesBuilder((Element) headphonesList.item(i)));
        }
    }

    private void addKeyboards(NodeList keyboardsList){
        for(int i=0;i<keyboardsList.getLength();i++){
            devices.addDevice(keyboardBuilder((Element) keyboardsList.item(i)));
        }
    }

    private void addMouses (NodeList mousesList){
        for(int i=0;i<mousesList.getLength();i++){
            devices.addDevice(mouseBuilder((Element) mousesList.item(i)));
        }
    }

    public void buildDevicesList(String filepath) throws XmlParseException {
        Document document;
        try {
            document = documentBuilder.parse(filepath);
            Element root = document.getDocumentElement();
            NodeList devicesList = root.getElementsByTagName("devices");
            addCpus(root.getElementsByTagName("cpu"));
            addGpus(root.getElementsByTagName("gpu"));
            addHeadphones(root.getElementsByTagName("headphones"));
            addKeyboards(root.getElementsByTagName("keyboard"));
            addMouses(root.getElementsByTagName("mouse"));

        } catch (IOException e) {
            logger.error("File cannot be opened or read {}", filepath);
            throw new XmlParseException("Failed to open/read file" + filepath);
        }
        catch (SAXException e){
            logger.error("Filed cannot be parsed {}", filepath);
            throw new XmlParseException("Failed to parse XML file " + filepath);
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private static List<String> portsBuilder(Element portsElement) {
        List<String> ports = new ArrayList<String>();
        NodeList nodeList = portsElement.getElementsByTagName("port");
        for (int i = 0; i < nodeList.getLength(); i++) {
            ports.add(nodeList.item(i).getTextContent());
        }
        return ports;
    }
}
