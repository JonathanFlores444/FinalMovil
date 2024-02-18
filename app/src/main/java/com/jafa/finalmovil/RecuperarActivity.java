package com.jafa.finalmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecuperarActivity extends AppCompatActivity {
    EditText edCedula, edUsuario, edNuevaCon, edConfirmaCon;
    TextView tvIrAtras;
    Button btActualiza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        edCedula = findViewById(R.id.txtCedulaRecu);
        edUsuario = findViewById(R.id.txtUsuarioRecu);
        edNuevaCon = findViewById(R.id.txtNuevaConRecu);
        edConfirmaCon = findViewById(R.id.txtConfirmarConRecu);
        tvIrAtras = findViewById(R.id.lblRecuAtras);
        btActualiza = findViewById(R.id.btnRecuperar);

        tvIrAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IrLogin = new Intent(RecuperarActivity.this, MainActivity.class);
                startActivity(IrLogin);
                finish();
            }
        });
        btActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = edCedula.getText().toString();
                String usuario = edUsuario.getText().toString();
                String newcontra = edNuevaCon.getText().toString();
                String confcontra = edConfirmaCon.getText().toString();

                if(cedula.isEmpty() && usuario.isEmpty() && newcontra.isEmpty() && confcontra.isEmpty()){
                    Toast.makeText(RecuperarActivity.this, "No deje campos vacios", Toast.LENGTH_SHORT).show();
                }else if(!newcontra.equals(confcontra)){
                    Toast.makeText(RecuperarActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    edNuevaCon.setText("");
                    edConfirmaCon.setText("");
                }else{
                    //Aqui se supone que va el metodo que actualiza
                }
            }
        });
    }
}