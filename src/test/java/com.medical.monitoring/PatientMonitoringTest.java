package com.medical.monitoring;

import com.medical.monitoring.exception.SensorReadException;
import com.medical.monitoring.factory.SensorFactory;
import com.medical.monitoring.model.HealthData;
import com.medical.monitoring.monitor.IcuPatientMonitor;
import com.medical.monitoring.monitor.PatientMonitor;
import com.medical.monitoring.sensor.EcgSensorAdapter;
import com.medical.monitoring.sensor.VendorEcgDriver;
import com.medical.monitoring.sensor.VitalSensor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientMonitoringTest {

    @Test
    @DisplayName("Success Path: SensorFactory creates valid ECG Adapter and reads health data")
    void testEcgSensorAdapterSuccessPath() {
        // Arrange
        VitalSensor ecgSensor = SensorFactory.createSensor("ecg");

        // Act
        HealthData data = ecgSensor.readData();

        // Assert
        assertNotNull(data, "Health data should not be null");
        assertEquals(80, data.heartRate(), "Heart rate should match expected value from driver");
        assertEquals(99, data.oxygenLevel(), "Oxygen level should match expected value from driver");
        assertEquals(36.5, data.temperature(), 0.01, "Temperature should match expected value");
    }

    @Test
    @DisplayName("Success Path: IcuPatientMonitor processes valid vitals without throwing exceptions")
    void testIcuPatientMonitorExecution() {
        // Arrange
        VitalSensor sensor = SensorFactory.createSensor("bed");
        PatientMonitor monitor = new IcuPatientMonitor(sensor);

        // Act & Assert
        assertDoesNotThrow(monitor::processVitals, "Monitor processing should complete successfully");
    }

    @Test
    @DisplayName("Failure Path: Adapter catches VendorHardwareException and translates it into SensorReadException")
    void testEcgSensorAdapterFailurePath() {
        // Arrange: Custom broken driver simulating hardware fault
        VendorEcgDriver faultyDriver = new VendorEcgDriver() {
            @Override
            public int fetchCardioFrame(double[] buffer, boolean highPrecision) throws com.medical.monitoring.exception.VendorHardwareException {
                throw new com.medical.monitoring.exception.VendorHardwareException("ERR_HARDWARE_FAILURE");
            }
        };

        VitalSensor adapter = new EcgSensorAdapter(faultyDriver);

        // Act & Assert
        SensorReadException exception = assertThrows(
                SensorReadException.class,
                adapter::readData,
                "Adapter must wrap VendorHardwareException inside SensorReadException"
        );

        assertTrue(exception.getMessage().contains("ECG Sensor failure"), "Exception message should explain failure context");
    }

    @Test
    @DisplayName("Failure Path: SensorFactory throws IllegalArgumentException for unknown sensor types")
    void testSensorFactoryInvalidType() {
        // Act & Assert
        assertThrows(
                IllegalArgumentException.class,
                () -> SensorFactory.createSensor("unknown_type"),
                "Factory should reject unsupported sensor types"
        );
    }
}