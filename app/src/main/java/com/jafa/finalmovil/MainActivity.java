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
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvIrRegistro;
    Button btIrInicio;
    EditText edusuario, edcontrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvIrRegistro = findViewById(R.id.lblRegistrarse);
        btIrInicio = findViewById(R.id.btnIngresar);
        edusuario = findViewById(R.id.txtUsuario);
        edcontrasenia = findViewById(R.id.txtContrasenia);
        tvIrRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IrRegistro = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(IrRegistro);
            }
        });
        btIrInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edusuario.getText().toString();
                String pass = edcontrasenia.getText().toString();
                if(!user.isEmpty() || !pass.isEmpty()){
                    loginWS();
                }else {
                    Toast.makeText(MainActivity.this, "Escriba bien no sea gil", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loginWS(){
        String url = "http://192.168.1.60/WS/webapi.php?op=login&user='"+edusuario.getText().toString()+"'&pass='"+edcontrasenia.getText().toString()+"'";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int userType = jsonObject.getInt("user_type");
                    Toast.makeText(MainActivity.this,userType,Toast.LENGTH_SHORT).show();
                    if("0".equals(userType)){
                        Toast.makeText(MainActivity.this, "Estudiambre",Toast.LENGTH_SHORT).show();
                    } else if ("1".equals(userType)){
                        Toast.makeText(MainActivity.this, "Dochente",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Datos Incorrectos",Toast.LENGTH_SHORT).show();
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
        });
        Volley.newRequestQueue(this).add(postRequest);
    }


}