package com.jafa.finalmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Map;

public class InicioActivity extends AppCompatActivity {
    TextView tvMostrarProyecto, tvMostrarGrupo, tvMostrarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        tvMostrarProyecto = findViewById(R.id.lblMuestraProyecto);
        tvMostrarGrupo = findViewById(R.id.lblMuestraGrupo);
        tvMostrarUsuario = findViewById(R.id.lblMuestraUsuario);

        String ip = "192.168.1.60";
        Bundle enviar = getIntent().getExtras();
        if(enviar != null){
            String usua = enviar.getString("Usuario");
            String cont = enviar.getString("Contrasenia");
            tvMostrarUsuario.setText(usua);
            proyectoWS(ip, usua, cont);

        }
    }
    private void proyectoWS(String ip, String usu, String con) {
        String url = "http://"+ip+"/WS/webapi.php?op=listarGrup";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    String nombrepro = jsonResponse.getString(0);
                    String nombregrup = jsonResponse.getString(1);
                    if(!jsonResponse.equals(0)){
                        tvMostrarProyecto.setText(nombrepro);
                        tvMostrarGrupo.setText(nombregrup);
                    }else{
                        Toast.makeText(InicioActivity.this, "Error: usuario obtenido erroneo", Toast.LENGTH_SHORT).show();
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
                params.put("usuario", usu);
                params.put("contrasenia", con);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }

}