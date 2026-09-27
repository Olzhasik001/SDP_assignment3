package com.medical.monitoring.monitor;
import com.medical.monitoring.model.HealthData;
import com.medical.monitoring.sensor.VitalSensor;

public class DailyCheckupMonitor extends PatientMonitor {
    public DailyCheckupMonitor(VitalSensor sensor) {
        super(sensor);
    }

    @Override
    public void processVitals() {
        HealthData data = sensor.readData();
        System.out.println("[DAILY LOG] Recorded pulse: " + data.heartRate() + ", SpO2: " + data.oxygenLevel() + "%");
    }
}