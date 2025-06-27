package com.duyvukim.drowsinessalertsystem.services;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DrowsinessLoggerService {

    /**
     * Create a log entry for drowsy behavior.
     */
    public static void logDrowsiness() {
        FirebaseFirestore db = FirebaseServicesProvider.getFirebaseFirestore();
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("timestamp", FieldValue.serverTimestamp());
        logMap.put("isDrowsy", true);
        db.collection("drowsinessLogs").add(logMap);
    }
}
