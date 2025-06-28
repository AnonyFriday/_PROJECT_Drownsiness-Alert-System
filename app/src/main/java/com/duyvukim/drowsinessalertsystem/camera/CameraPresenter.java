package com.duyvukim.drowsinessalertsystem.camera;

import android.annotation.SuppressLint;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;

import com.duyvukim.drowsinessalertsystem.detection.FaceAnalyzer;
import com.duyvukim.drowsinessalertsystem.detection.IFaceAnalyzerCallbacks;
import com.duyvukim.drowsinessalertsystem.detection.IssuesDetector;
import com.duyvukim.drowsinessalertsystem.utils.AppCts;
import com.google.mlkit.vision.face.Face;

public class CameraPresenter implements ICameraContract.Presenter {

    // =========================================
    // === Fields
    // =========================================

    private ICameraContract.View view;
    private CameraFramesSource cameraFramesSource;
    private int frameClosedEyesCounter = 0;
    private int multiplePeopleFrameCounter = 0;
    private int headPoseProblemCounter = 0;

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
            new FaceAnalyzer(new IFaceAnalyzerCallbacks() {
                @Override
                public void onFaceDetected(Face face) {
                    if (face == null) return;

                    // Detect isDrowsy
                    if (IssuesDetector.isDrowsy(face)) {
                        frameClosedEyesCounter++;

                        // might be trigger after 200 consecutive frames closing, not everytime closing
                        if (frameClosedEyesCounter > AppCts.Thresholds.FRAMES_CLOSED_THRESHOLD) {

//                            view.showMessage("Drowsiness detected");
                            // TODO: send to the firestore
                        }
                    } else {
                        frameClosedEyesCounter = 0;
                    }

                    // Detect is Head Pose problem here
                    // TODO: fix the status later
                    if (!IssuesDetector.isHeadPoseProblem(face).isBlank()) {
                        headPoseProblemCounter++;
                        if (headPoseProblemCounter > AppCts.Thresholds.FRAMES_HEAD_POSE_PROBLEM_THRESHOLD) {

//                            view.showMessage("Head pose problem detected");
                            // TODO: send to the firestore
                        }
                    } else {
                        headPoseProblemCounter = 0;
                    }
                }

                // Detect multiple people
                @Override
                public void onFacesCountDetected(int facesCount) {
                    if (facesCount > 1) {
                        multiplePeopleFrameCounter++;
                        if (multiplePeopleFrameCounter > AppCts.Thresholds.FRAMES_MULTIPLE_PEOPLE_THRESHOLD) {

//                            view.showMessage("Number of people: " + facesCount);
                            // TODO: send to the firestore
                        }
                    } else {
                        multiplePeopleFrameCounter = 0;
                    }
                }

            }).analyzeImageFrame(imageProxy);
        });

        cameraFramesSource.start();
    }
}


