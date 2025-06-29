package com.duyvukim.drowsinessalertsystem;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Add Firebase
        FirebaseApp.initializeApp(this);


        FirebaseFirestore db = FirebaseFirestore.getInstance("drowsiness-detection");
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true) // Example setting
                .build();
        db.setFirestoreSettings(settings);

        // ADD
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Ada");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);

        CollectionReference ref = db.collection("test");

//        db.collection("userssss")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("TEST", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("TEST", "Error adding document", e);
//                    }
//                });
//
//        // Testing get collection
//        db.collection("test")
//                .get()
//                .addOnSuccessListener(querySnapshot -> {
//                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
//                        String message = doc.getString("message");
//                        Timestamp timestamp = doc.getTimestamp("timestamp");
//                        Log.d("FirestoreRead", "Message: " + message + ", Timestamp: " + timestamp);
//                    }
//                })
//                .addOnFailureListener(e -> Log.e("FirestoreRead", "Read failed: " + e.getMessage(), e));

    }
}
