package com.duyvukim.drowsinessalertsystem.detection;

import com.google.mlkit.vision.face.Face;

public interface IFaceCallback {
    public void onFaceDetected(Face face);
}
