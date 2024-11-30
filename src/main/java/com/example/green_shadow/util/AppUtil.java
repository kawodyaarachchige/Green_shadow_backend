package com.example.green_shadow.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateCropCode(String cropCategory) {
        return "CROP-" + cropCategory + "-" + UUID.randomUUID().toString();

    }
    public static String convertImageToBase64(byte [] image) {
        return Base64.getEncoder().encodeToString(image);
    }
    public static String generateLogCode() {
        return "LOG-" + UUID.randomUUID().toString();
    }
    public static String generateFieldCode() {
        return "FIELD-" + UUID.randomUUID().toString();
    }
    public static String generateStaffId() {
        return "STAFF-" + UUID.randomUUID().toString();
    }
    public static String generateVehicleCode() {
        return "VEHICLE-" + UUID.randomUUID().toString();
    }
    public static String generateEquipmentId() {
        return "EQUIPMENT-" + UUID.randomUUID().toString();
    }
    
}
