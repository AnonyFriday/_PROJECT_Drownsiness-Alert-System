package com.duyvukim.drowsinessalertsystem.detection;

import android.media.Image;

import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageProxy;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

public class FaceAnalyzer {

    private final FaceDetector faceDetector;
    private final IFaceCallback faceCallback;
    private final FaceDetectorOptions faceDetectorOptions;

    public FaceAnalyzer(IFaceCallback faceCallback) {
        this.faceCallback = faceCallback;

        // Build the face detector with the custom options
        faceDetectorOptions = new FaceDetectorOptions.Builder()
                .enableTracking() // for real time frames
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                .build();

        this.faceDetector = FaceDetection.getClient(faceDetectorOptions);
    }

    /**
     * Analyze the image frame from the camera
     *
     * @param imageProxy
     */
    @ExperimentalGetImage
    public void analyzeImageFrame(ImageProxy imageProxy) {

        // analzye the image frame by frame captured from the camera
        // CameraX wraps frame of image into the ImageProxy and send to the analyzer
        Image imageFrame = imageProxy.getImage();
        if (imageFrame == null) return;

        // pass the image frame to the face detector
        InputImage inputImage = InputImage.fromMediaImage(imageFrame, imageProxy.getImageInfo().getRotationDegrees());

        // process the image
        faceDetector.process(inputImage)
                .addOnSuccessListener(faces -> {
                    for (Face face : faces) {

                        // pass the face to the callback if detected
                        faceCallback.onFaceDetected(face);
                    }
                })
                .addOnCompleteListener(task -> imageProxy.close());
    }
}