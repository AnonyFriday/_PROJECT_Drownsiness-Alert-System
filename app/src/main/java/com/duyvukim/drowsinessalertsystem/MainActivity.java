package com.duyvukim.drowsinessalertsystem;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.duyvukim.drowsinessalertsystem.camera.CameraActivity;
import com.duyvukim.drowsinessalertsystem.databinding.ActivityMainBinding;
import com.duyvukim.drowsinessalertsystem.utils.AppCts;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText examCodeInput;
    private MaterialButton joinExamButton;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeViews();
        joinExamButton.setOnClickListener(v->{
            String code;
            if (examCodeInput.getText()!=null)
                code = examCodeInput.getText().toString().trim();
            else {
                examCodeInput.setError("Please enter code!");
                return ;
            }

            if (code.equals(AppCts.Notifications.EXAM_CODE))
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            else examCodeInput.setError("Code isn't found");
        });
    }

    private void initializeViews() {
        joinExamButton = binding.joinExamButton;
        examCodeInput = binding.examCodeInput;
    }
}