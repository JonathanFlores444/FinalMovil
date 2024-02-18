package com.jafa.finalmovil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    TextView tvIrLogin;
    EditText edCedula, edNombre, edApellido, edCorreo, edTelefono, edUsuario, edContrasenia;
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

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = edCedula.getText().toString();
                String nombre = edNombre.getText().toString();
                String apellido = edApellido.getText().toString();
                String correo = edCorreo.getText().toString();
                String telefono = edTelefono.getText().toString();
                String usuario = edUsuario.getText().toString();
                String contrasenia = edContrasenia.getText().toString();
                if (!cedula.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !telefono.isEmpty() && !usuario.isEmpty() && !contrasenia.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistroActivity.this);
                    builder.setMessage("Su usuario y contraseña seran:\nUsuario: " + usuario + "\nContaseña: " + contrasenia + "\n¿Desea continuar?")
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    signupWS(cedula, nombre, apellido, correo, telefono, usuario, contrasenia);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    dialog.dismiss();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
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

    private void signupWS(String ced, String nom, String ape, String cor, String tel, String usu, String con) {
        String url = "http://192.168.1.60/WS/webapi.php?op=signup";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    String resultado = jsonResponse.getString(0);

                    if ("2".equals(resultado)) {
                        Intent IrLogin = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(IrLogin);
                        finish();
                        Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else if("0".equals(resultado)){
                        Toast.makeText(RegistroActivity.this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();
                        edCedula.setText("");
                        edUsuario.setText("");
                        edCedula.setHint("CEDULA (Escriba uno valido)");
                        edUsuario.setHint("USUARIO (Escriba uno valido)");

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cedula", ced);
                params.put("nombre", nom.toUpperCase());
                params.put("apellido", ape.toUpperCase());
                params.put("correo", cor);
                params.put("telefono", tel);
                params.put("usuario", usu);
                params.put("contrasenia", con);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }
}