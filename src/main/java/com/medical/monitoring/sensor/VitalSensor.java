package com.medical.monitoring.sensor;
import com.medical.monitoring.exception.SensorReadException;
import com.medical.monitoring.model.HealthData;

public interface VitalSensor {
    HealthData readData() throws SensorReadException;
}