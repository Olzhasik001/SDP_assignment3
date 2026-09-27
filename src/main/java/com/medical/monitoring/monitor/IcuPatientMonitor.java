package com.medical.monitoring.monitor;
import com.medical.monitoring.model.HealthData;
import com.medical.monitoring.sensor.VitalSensor;

public class IcuPatientMonitor extends PatientMonitor {
    public IcuPatientMonitor(VitalSensor sensor) {
        super(sensor);
    }

    @Override
    public void processVitals() {
        HealthData data = sensor.readData();
        if (data.heartRate() > 120 || data.oxygenLevel() < 90) {
            System.out.println("[ALARM] Critical vitals detected! Heart rate: " + data.heartRate());
        } else {
            System.out.println("[ICU] Patient stable. Pulse: " + data.heartRate() + ", SpO2: " + data.oxygenLevel() + "%");
        }
    }
}