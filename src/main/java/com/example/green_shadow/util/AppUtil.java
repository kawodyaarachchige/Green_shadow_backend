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
}
