package com.duyvukim.drowsinessalertsystem.detection;

import com.google.mlkit.vision.face.Face;

public interface IFaceAnalyzerCallbacks {

    /**
     * This image will be called when the face is detected
     *
     * @param face
     */
    void onFaceDetected(Face face);

    void onFacesCountDetected(int count);
}
