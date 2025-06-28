package com.duyvukim.drowsinessalertsystem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.duyvukim.drowsinessalertsystem.camera.CameraActivity;
import com.duyvukim.drowsinessalertsystem.databinding.ActivityMainBinding;
import com.duyvukim.drowsinessalertsystem.utils.AppCts;

public class MainActivity extends AppCompatActivity {

    // ====================================
    // === Fields
    // ====================================

    private ActivityMainBinding binding;

    // ====================================
    // === Lifecycles
    // ====================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checkAndRequestCameraPermission();

        binding.joinExamButton.setOnClickListener(v -> {
            String code;
            if (binding.examCodeInput.getText() != null)
                code = binding.examCodeInput.getText().toString().trim();
            else {
                binding.examCodeInput.setError("Please enter code!");
                return ;
            }

            if (code.equals(AppCts.Identities.EXAM_CODE))
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            else binding.examCodeInput.setError("Code isn't found");
        });
    }

    // ====================================
    // === Camera Permission
    // ====================================

    private final ActivityResultLauncher<String> requestCameraPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "Camera permission GRANTED", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Camera permission DENIED", Toast.LENGTH_SHORT).show();
                    // Handle the case where the user denies the permission
                }
            });

    private void checkAndRequestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted
            Toast.makeText(this, "Camera permission already granted", Toast.LENGTH_SHORT).show();
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            // Explain to the user why you need the permission
            Toast.makeText(this, "Camera permission is needed to take pictures.", Toast.LENGTH_LONG).show();
            // Then, request the permission
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        } else {
            // Directly request the permission
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }
}