package com.duyvukim.drowsinessalertsystem.services;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseServicesProvider {

    public static final FirebaseFirestore getFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    public static final FirebaseMessaging getFirebaseMessaging() {
        return FirebaseMessaging.getInstance();
    }
}
