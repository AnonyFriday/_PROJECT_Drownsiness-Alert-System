package com.duyvukim.drowsinessalertsystem.camera;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;

import com.duyvukim.drowsinessalertsystem.detection.IssuesDetector;
import com.duyvukim.drowsinessalertsystem.detection.FaceAnalyzer;
import com.duyvukim.drowsinessalertsystem.utils.AppCts;

public class CameraPresenter implements ICameraContract.Presenter {

    // =========================================
    // === Fields
    // =========================================

    private ICameraContract.View view;
    private CameraFramesSource cameraFramesSource;
    private int frameClosedEyesCounter = 0;

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

        // TODO: Run at different executor service for detection

        // Initialize the camera and start preview
        cameraFramesSource = new CameraFramesSource(previewView, lifecycleOwner, imageProxy -> {

            // If having the image proxy, then process the image
            // through the EyeDetector

            new FaceAnalyzer(face -> {

                frameClosedEyesCounter++;

                if (face != null) {
                    if (IssuesDetector.isDrowsy(face)) {
                        // might be trigger after 200 consecutive frames closing, not everytime closing
                        if (frameClosedEyesCounter > AppCts.Thresholds.FRAMES_CLOSED_THRESHOLD) {

                            // TODO: implement the alarm and notification
                            // TODO: set the flag for alarm, alarm whenever it was turned off and notification

                            Log.d("Drowsiness", "Drownsy");
                            view.showMessage("Drowsiness detected");
                        }
                    } else {
                        frameClosedEyesCounter = 0;
                    }

                    String headPoseResult = IssuesDetector.HeadPoseDetection(face); // Call the updated method
                    Log.d("HeadPoseDetection", "Head Pose: " + headPoseResult);
//                    if (!headPoseResult.equals("Looking Straight") && !headPoseResult.equals("No Face Detected")) {
//                        view.showMessage(headPoseResult);
//                    }

                }
            }).analyzeImageFrame(imageProxy);
        });

        cameraFramesSource.start();
    }



}
