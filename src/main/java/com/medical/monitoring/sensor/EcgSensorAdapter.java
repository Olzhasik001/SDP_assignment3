package com.medical.monitoring.sensor;
import com.medical.monitoring.exception.SensorReadException;
import com.medical.monitoring.exception.VendorHardwareException;
import com.medical.monitoring.model.HealthData;

public class EcgSensorAdapter implements VitalSensor {
    private final VendorEcgDriver ecgDriver;

    public EcgSensorAdapter(VendorEcgDriver ecgDriver) {
        this.ecgDriver = ecgDriver;
    }

    @Override
    public HealthData readData() throws SensorReadException {
        try {
            double[] rawBuffer = new double[3];
            int statusCode = ecgDriver.fetchCardioFrame(rawBuffer, true);

            if (statusCode != 0) {
                throw new SensorReadException("Hardware failure with status code: " + statusCode, null);
            }

            return new HealthData((int) rawBuffer[0], (int) rawBuffer[1], rawBuffer[2]);
        } catch (VendorHardwareException e) {
            throw new SensorReadException("ECG Sensor failure: " + e.getMessage(), e);
        }
    }
}