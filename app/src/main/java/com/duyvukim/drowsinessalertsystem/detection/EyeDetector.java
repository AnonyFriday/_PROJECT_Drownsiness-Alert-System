package com.duyvukim.drowsinessalertsystem.detection;

import android.util.Log;

import com.google.mlkit.vision.face.Face;

public class EyeDetector {
    public static boolean isDrowsy(Face face) {

        Float leftProb = face.getLeftEyeOpenProbability();
        Float rightProb = face.getRightEyeOpenProbability();

        // If both eyes are closed, consider the person as drowsy
        // 1: eye is fully open
        // 0: eye is fully closed

        boolean isLeftEyeSleep = leftProb != null && leftProb < 0.7f;
        boolean isRightEyeSleep = rightProb != null && rightProb < 0.7f;

        Log.d("EyeProb", "Left: " + isLeftEyeSleep);
        Log.d("EyeProb", "Right: " + isLeftEyeSleep);

        return isLeftEyeSleep || isRightEyeSleep;
    }
}
