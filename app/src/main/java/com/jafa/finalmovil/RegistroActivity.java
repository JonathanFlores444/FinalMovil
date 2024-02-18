package com.jafa.finalmovil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    TextView tvIrLogin;
    EditText edCedula,edNombre, edApellido, edCorreo, edTelefono, edUsuario, edContrasenia;
    Button btRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        edCedula = findViewById(R.id.txtCedulaReg);
        edNombre = findViewById(R.id.txtNombreReg);
        edApellido = findViewById(R.id.txtApellidoReg);
        edCorreo = findViewById(R.id.txtCorreoReg);
        edTelefono = findViewById(R.id.txtTelefonoReg);
        edUsuario = findViewById(R.id.txtUsuarioReg);
        edContrasenia = findViewById(R.id.txtContraseniaReg);
        tvIrLogin = findViewById(R.id.txtRegAtras);
        btRegistrar = findViewById(R.id.btnRegistrar);

        String cedula = edCedula.getText().toString();
        String nombre = edNombre.getText().toString();
        String apellido = edApellido.getText().toString();
        String correo = edCorreo.getText().toString();
        String telefono = edTelefono.getText().toString();
        String usuario = edUsuario.getText().toString();
        String contrasenia = edContrasenia.getText().toString();

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cedula.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !telefono.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                    builder.setMessage("Su usuario y contraseña seran:\nUsuario: " + usuario + "\nContaseña: " + contrasenia + "\n¿Desea continuar?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent IrLogin = new Intent(RegistroActivity.this, MainActivity.class);
                                    startActivity(IrLogin);
                                    finish();
                                    Toast.makeText(RegistroActivity.this, "Su registro fue exitoso", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.dismiss();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    Toast.makeText(RegistroActivity.this, "Evite dejar campos vacios", Toast.LENGTH_SHORT).show();

                }
            }
        });
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