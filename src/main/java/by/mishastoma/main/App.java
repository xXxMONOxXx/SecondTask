package by.mishastoma.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class App
{
    private static final Logger logger = LogManager.getLogger();

    public static void main( String[] args )
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("src/main/resources/data/computerComponents.xml");
            NodeList nodes = doc.getElementsByTagName("devices");
            NodeList nodeList = nodes.item(0).getChildNodes();
            for(int i=0 ;i<nodeList.getLength();i++){
                if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(nodeList.item(i).getNodeName());
                }
            }
        }
        catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}
