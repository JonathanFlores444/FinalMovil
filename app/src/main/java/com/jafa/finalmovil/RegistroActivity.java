package com.jafa.finalmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {
    TextView tvIrLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tvIrLogin = findViewById(R.id.txtRegAtras);
        tvIrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrLogin = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(IrLogin);
                finish();
            }
        });
    }
}