package by.mishastoma.validator;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlFileValidator {

    private static XmlFileValidator instance;

    private XmlFileValidator() {
    }

    public static XmlFileValidator getInstance() {
        if (instance == null) {
            instance = new XmlFileValidator();
        }
        return instance;
    }

    public boolean isValid(String xmlFilepath, String schemaFilepath) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaFilepath);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilepath);
            validator.validate(source);
            return true;
        } catch (IOException | org.xml.sax.SAXException e) {
            return false;
        }
    }
}