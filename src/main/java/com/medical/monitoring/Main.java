package com.medical.monitoring;
import com.medical.monitoring.factory.SensorFactory;
import com.medical.monitoring.monitor.IcuPatientMonitor;
import com.medical.monitoring.monitor.PatientMonitor;
import com.medical.monitoring.sensor.VitalSensor;

public class Main {
    public static void main(String[] args) {
        VitalSensor ecgSensor = SensorFactory.createSensor("ecg");
        PatientMonitor icuMonitor = new IcuPatientMonitor(ecgSensor);

        icuMonitor.processVitals();
    }
}