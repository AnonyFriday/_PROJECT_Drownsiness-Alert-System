package com.duyvukim.drowsinessalertsystem.detection;

import com.google.mlkit.vision.face.Face;

public interface IEyeDetectorCallbacks {

    /**
     * This image will be called when the face is detected
     *
     * @param face
     */
    void onFaceDetected(Face face);
}
