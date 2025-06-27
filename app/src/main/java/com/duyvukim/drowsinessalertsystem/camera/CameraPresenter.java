package com.duyvukim.drowsinessalertsystem.camera;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;

import com.duyvukim.drowsinessalertsystem.detection.EyeDetector;
import com.duyvukim.drowsinessalertsystem.detection.FaceAnalyzer;

public class CameraPresenter implements ICameraContract.Presenter {

    // =========================================
    // === Fields
    // =========================================

    private ICameraContract.View view;

    // =========================================
    // === Constructors
    // =========================================

    public CameraPresenter(ICameraContract.View view) {
        this.view = view;
    }

    // =========================================
    // === Methods
    // =========================================

    @SuppressLint("UnsafeOptInUsageError")
    @Override
    public void startCamera(PreviewView previewView, LifecycleOwner lifecycleOwner) {

        // Initialize the camera and start preview
        new CameraFramesSource(previewView, lifecycleOwner, imageProxy -> {

            // If having the image proxy, then process the image
            // through the EyeDetector

            new FaceAnalyzer(face -> {
                if (face != null && EyeDetector.isDrowsy(face)) {

                    // TODO: implement the alarm and notification
                    // TODO: might be trigger after 10,20 consecutive frames closing, not everytime closing

                    Log.d("Drowsiness", "Drowsy");
                    view.showMessage("Drowsiness detected");
                }
            }).analyzeImageFrame(imageProxy);

        }).start();
    }
}
