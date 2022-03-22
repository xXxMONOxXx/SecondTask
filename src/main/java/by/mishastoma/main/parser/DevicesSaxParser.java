package by.mishastoma.main.parser;

import by.mishastoma.main.entity.Devices;
import by.mishastoma.main.exception.XmlParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DevicesSaxParser {

    private static final Logger logger = LogManager.getLogger();

    private Devices devices;
    private XMLReader reader;
    private DevicesSaxParserHandler handler = new DevicesSaxParserHandler();

    public DevicesSaxParser(){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try{
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
        }
        catch (ParserConfigurationException | SAXException e){
            e.printStackTrace(); //todo
        }
        reader.setContentHandler(handler);
    }

    public Devices getDevices(){
        return devices;
    }
    public void buildDevicesList(String filePath) throws XmlParseException{
        try{
            reader.parse(filePath);
        }
        catch (IOException e){
            logger.error("Couldn't open/find file: {}", filePath);
            throw new XmlParseException();
        }
        catch (SAXException e){
            logger.error("During parser an error appeared: {}", e.getMessage());
            throw new XmlParseException(e.getMessage());
        }
        devices = handler.getDevices();
    }
}
