package com.duyvukim.drowsinessalertsystem.camera;

import androidx.camera.view.PreviewView;
import androidx.lifecycle.LifecycleOwner;

public interface ICameraContract {
    interface View {
        void showAlert();

        void showMessage();
    }

    interface Presenter {
        void startCamera(PreviewView previewView, LifecycleOwner lifecycleOwner);
    }
}
