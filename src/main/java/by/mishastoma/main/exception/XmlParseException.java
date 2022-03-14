package by.mishastoma.main.exception;

public class XmlParseException extends Exception{
    public XmlParseException() {
        super();
    }

    public XmlParseException(String message) {
        super(message);
    }

    public XmlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParseException(Throwable cause) {
        super(cause);
    }
}
