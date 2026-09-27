package com.medical.monitoring.sensor;
import com.medical.monitoring.exception.VendorHardwareException;

public class VendorEcgDriver {
    public int fetchCardioFrame(double[] buffer, boolean highPrecision) throws VendorHardwareException {
        if (!highPrecision) {
            throw new VendorHardwareException("ERR_INVALID_MODE");
        }
        buffer[0] = 80.0;
        buffer[1] = 99.0;
        buffer[2] = 36.5;
        return 0;
    }
}