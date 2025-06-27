package com.duyvukim.drowsinessalertsystem.camera;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;

public interface ICameraContract {

    // =========================================
    // === View
    // =========================================

    interface View {
        void showAlert();

        void showMessage(String msg);
    }

    // =========================================
    // === Presenter
    // =========================================

    interface Presenter {
        void startCamera(PreviewView previewView, LifecycleOwner lifecycleOwner);
    }
}
