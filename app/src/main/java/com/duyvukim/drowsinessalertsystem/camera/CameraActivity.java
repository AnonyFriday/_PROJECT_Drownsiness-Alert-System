package com.duyvukim.drowsinessalertsystem.camera;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.duyvukim.drowsinessalertsystem.databinding.ActivityCameraBinding;

public class CameraActivity extends AppCompatActivity implements ICameraContract.View {

    private ActivityCameraBinding binding;
    private ICameraContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        // Setup bindings
        binding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup presenter
        presenter = new CameraPresenter(this);

        // Start the camera stream
        presenter.startCamera(binding.previewView, this);

        // TODO: ask for camera permission

        // TODO: ask for notification permission
    }

    @Override
    public void showAlert() {
        Toast.makeText(this, "Alert!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}