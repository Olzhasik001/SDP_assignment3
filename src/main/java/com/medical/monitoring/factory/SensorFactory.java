package com.medical.monitoring.factory;
import com.medical.monitoring.sensor.*;

public class SensorFactory {
    public static VitalSensor createSensor(String sensorType) {
        return switch (sensorType.toLowerCase()) {
            case "bed" -> new HospitalBedSensor();
            case "band" -> new WearableBandSensor();
            case "ecg" -> new EcgSensorAdapter(new VendorEcgDriver());
            default -> throw new IllegalArgumentException("Unknown sensor type: " + sensorType);
        };
    }
}