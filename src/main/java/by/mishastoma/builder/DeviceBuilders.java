package by.mishastoma.builder;

import by.mishastoma.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeviceBuilders {

    private static final Logger logger = LogManager.getLogger();

    private static DeviceBuilders instance;

    protected String currentName;
    protected int currentPrice;
    protected String currentSerialNumber;
    protected String currentOrigin;
    protected boolean currentIsCritical;
    protected LocalDate currentDateOfManufacture;
    protected int currentPowerUsage;
    protected boolean currentPresenceOfFan;
    protected List<String> currentPorts;
    protected String currentPeripheralType;

    private DeviceBuilders(){
        currentPorts = new ArrayList<>();
    }

    public static DeviceBuilders getInstance(){
        if(instance == null){
            instance = new DeviceBuilders();
        }
        return instance;
    }

    protected Cpu buildCpu() {
        logger.info("Created new device, type: {}",  DevicesXmlTag.CPU.getValue());
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

    protected Gpu buildGpu() {
        logger.info("Created new device, type: {}",  DevicesXmlTag.GPU.getValue());
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

    protected Headphones buildHeadphones() {
        logger.info("Created new device, type: {}",  DevicesXmlTag.HEADPHONES.getValue());
        return new Headphones.Builder()
                .withName(currentName)
                .withOrigin(currentOrigin)
                .withSerialNumber(currentSerialNumber)
                .withPrice(currentPrice)
                .withIsCritical(currentIsCritical)
                .withManufactureDate(currentDateOfManufacture)
                .withPeripheralType(currentPeripheralType)
                .withPort(currentPorts.get(0))
                .build();
    }

    protected Keyboard buildKeyboard() {
        logger.info("Created new device, type: {}",  DevicesXmlTag.KEYBOARD.getValue());
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

    protected Mouse buildMouse() {
        logger.info("Created new device, type: {}",  DevicesXmlTag.MOUSE.getValue());
        return new Mouse.Builder()
                .withName(currentName)
                .withOrigin(currentOrigin)
                .withSerialNumber(currentSerialNumber)
                .withPrice(currentPrice)
                .withIsCritical(currentIsCritical)
                .withManufactureDate(currentDateOfManufacture)
                .withPeripheralType(currentPeripheralType)
                .withPort(currentPorts.get(0))
                .build();
    }
}
