package com.isep.engreq.util;

public class verifyLocation {
    public static boolean isInsidePortugal(double latitude, double longitude) {
        // Portugal mainland
        if (latitude >= 36.9615 && latitude <= 42.1542 && longitude >= -9.5073 && longitude <= -6.1892) {
            return true;
        }
        // Azores
        if (latitude >= 36.9615 && latitude <= 42.1542 && longitude >= -9.5073 && longitude <= -6.1892) {
            return true;
        }
        // Madeira
        if (latitude >= 36.9615 && latitude <= 42.1542 && longitude >= -9.5073 && longitude <= -6.1892) {
            return true;
        }
        return false;
    }
}