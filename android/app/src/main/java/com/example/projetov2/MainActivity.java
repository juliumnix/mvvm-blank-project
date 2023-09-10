package com.example.projetov2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetov2.viewmodel.FlutterIntegrationViewModel;
import com.example.projetov2.viewmodel.ReactIntegrationViewModel;

public class MainActivity extends AppCompatActivity {
    private FlutterIntegrationViewModel viewModelFlutter;
    private ReactIntegrationViewModel viewModelReact;
    private EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModelFlutter = new ViewModelProvider(this).get(FlutterIntegrationViewModel.class);
        viewModelReact = new ViewModelProvider(this).get(ReactIntegrationViewModel.class);
        Button btnReact = findViewById(R.id.btn_react);
        Button btnFlutter = findViewById(R.id.btn_flutter);
        txtMessage = findViewById(R.id.txt_texto);

        viewModelFlutter.initFramework(this);

        btnFlutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelFlutter.saveMessage(txtMessage.getText().toString());
                viewModelFlutter.navigateTo(MainActivity.this);
            }
        });
        btnReact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelReact.saveMessage(txtMessage.getText().toString());
                viewModelReact.navigateTo(MainActivity.this);
            }
        });
    }
}