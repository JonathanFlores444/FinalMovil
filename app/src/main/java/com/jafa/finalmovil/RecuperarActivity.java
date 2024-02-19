package com.jafa.finalmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

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
                String cedular = edCedula.getText().toString();
                String usuarior = edUsuario.getText().toString();
                String newcontrar = edNuevaCon.getText().toString();
                String confcontrar = edConfirmaCon.getText().toString();

                if (!cedular.isEmpty() && !usuarior.isEmpty() && !newcontrar.isEmpty() && !confcontrar.isEmpty()) {
                    if (newcontrar.equals(confcontrar)) {
                        newpassWS(cedular, usuarior, confcontrar);
                    } else {
                        Toast.makeText(RecuperarActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        edNuevaCon.setText("");
                        edConfirmaCon.setText("");
                    }
                } else {
                    Toast.makeText(RecuperarActivity.this, "No deje campos vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void newpassWS(String ced, String usu, String con) {
        String url = "http://192.168.1.60/WS/webapi.php?op=newpass";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    String resultado = jsonResponse.getString(0);

                    if ("3".equals(resultado)) {
                        Intent IrLogin = new Intent(RecuperarActivity.this, MainActivity.class);
                        startActivity(IrLogin);
                        finish();
                        Toast.makeText(RecuperarActivity.this, "Se actualizo correctamente", Toast.LENGTH_SHORT).show();
                    } else if("0".equals(resultado)){
                        Toast.makeText(RecuperarActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                        edCedula.setText("");
                        edUsuario.setText("");
                        edNuevaCon.setText("");
                        edConfirmaCon.setText("");

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
                params.put("cedula",ced);
                params.put("usuario", usu);
                params.put("contrasenia", con);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }
}