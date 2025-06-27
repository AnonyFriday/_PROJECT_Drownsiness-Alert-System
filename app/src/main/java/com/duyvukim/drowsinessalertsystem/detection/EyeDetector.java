package com.duyvukim.drowsinessalertsystem.detection;

import android.util.Log;

import com.google.mlkit.vision.face.Face;

public class EyeDetector {
    public static boolean isDrowsy(Face face) {

        Float leftProb = face.getLeftEyeOpenProbability();
        Float rightProb = face.getRightEyeOpenProbability();

        Log.d("Face", "Face: " + face);
        Log.d("EyeProb", "Left: " + leftProb);
        Log.d("EyeProb", "Right: " + leftProb);

        // If both eyes are closed, consider the person as drowsy
        // 1: eye is fully open
        // 0: eye is fully closed

        boolean isLeftEyeSleep = leftProb != null && leftProb < 0.3f;
        boolean isRightEyeSleep = rightProb != null && rightProb < 0.3f;

        return isLeftEyeSleep || isRightEyeSleep;
    }
}
