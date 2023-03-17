package comcristobalbernal.celebreascristobal.Autenticacion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import comcristobalbernal.celebreascristobal.MainActivity;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.Utils.HashGenerator;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Frase;
import comcristobalbernal.celebreascristobal.models.Usuario;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private IAPIService iapiService;
    private SharedPreferences prefs;
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        iapiService = RestClient.getInstance();
        etUsername = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etContra);
        //esto no hace nada!!! solo lo he puesto para comprobar si funciona la conexion al servidor!!!1
        getFrases();

        findViewById(R.id.btIniciarSesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loginUser();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        findViewById(R.id.btRegistrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Registrar.class));
            }
        });
    }

    private void getFrases() {
        iapiService.getFrases().enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {

            }

            @Override
            public void onFailure(Call<List<Frase>> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loginUser() throws NoSuchAlgorithmException {
        String username = etUsername.getText().toString();
        String password = HashGenerator.getSHAString(etPassword.getText().toString());
        if (username.isEmpty()) {
            etUsername.setError("Es necesario escribir algo...");
            etUsername.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Es necesario escribir algo...");
            etPassword.requestFocus();
            return;
        }
        Call<Boolean> booleanCall = iapiService.logUsuario(new Usuario(username, password));

        booleanCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if (Boolean.TRUE.equals(response.body())) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("nombreUsuario",username));
                    Toast.makeText(LoginActivity.this, "Has inicion sesion con " +username, Toast.LENGTH_SHORT).show();
                    prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("name",username);
                    editor.putString("contrasena",password);
                    editor.apply();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuario no válido o no registrado!!!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
