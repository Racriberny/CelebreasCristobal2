package comcristobalbernal.celebreascristobal.Autenticacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Usuario;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrar extends AppCompatActivity {
    private IAPIService iapiService;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        iapiService = RestClient.getInstance();
        etUsername = findViewById(R.id.etUsuarioRegistrar);
        etPassword = findViewById(R.id.etContraRegistrar);

        findViewById(R.id.btResgistrarRegistrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        findViewById(R.id.btLoginResgrister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registrar.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (username.isEmpty()) {
            etUsername.setError("Es necesario escribir algo...");
            etUsername.requestFocus();
            return;
        } else if (password.isEmpty()) {
            etPassword.setError("Es necesario escribir algo...");
            etPassword.requestFocus();
            return;
        }

        Call<Boolean> booleanCall = iapiService.addUsuario(new Usuario(username, password));
        booleanCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                System.out.println(response.body());
                if (Boolean.TRUE.equals(response.body())) {
                    Toast.makeText(Registrar.this, "Has creado un usuario con el nombre " + username, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Registrar.this, LoginActivity.class));
                } else {
                    Toast.makeText(Registrar.this, "Usuario no registrado o ya existente", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, Throwable t) {
                Toast.makeText(Registrar.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
