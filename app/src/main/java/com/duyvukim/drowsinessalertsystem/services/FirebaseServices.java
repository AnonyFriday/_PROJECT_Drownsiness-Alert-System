package com.duyvukim.drowsinessalertsystem.services;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseServices {

    public static final FirebaseFirestore getFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }
}
