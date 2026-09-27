package com.medical.monitoring.monitor;
import com.medical.monitoring.sensor.VitalSensor;

public abstract class PatientMonitor {
    protected final VitalSensor sensor;

    public PatientMonitor(VitalSensor sensor) {
        this.sensor = sensor;
    }

    public abstract void processVitals();
}