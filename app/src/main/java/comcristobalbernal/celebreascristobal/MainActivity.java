package comcristobalbernal.celebreascristobal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Fragments.FragmentAutores;
import comcristobalbernal.celebreascristobal.Fragments.FragmentCategorias;
import comcristobalbernal.celebreascristobal.Utils.SettingActivity;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.models.Usuario;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FragmentCategorias.IOnAttach, FragmentAutores.IOnAttach {

    private IAPIService apiService;
    private SharedPreferences prefs;
    private List<Autor> autores;
    private List<Categoria> categorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = RestClient.getInstance();
        autores = new ArrayList<>();
        categorias = new ArrayList<>();
        creacionyCargaDatos();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_setting) {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void creacionyCargaDatos(){
        getCargarCategorias();
        getCargarAutores();
        //getCargaUsuarios();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ip",RestClient.CASA);
        editor.putString("puerto", String.valueOf(RestClient.PORT));
        editor.apply();
    }


    @Override
    public List<Autor> getAutor() {
        if (autores == null){
            getCargarAutores();
        }
        return autores;
    }

    @Override
    public List<Categoria> getCategorias() {
        if (categorias == null){
            getCargarCategorias();
        }
        return categorias;
    }

    public void getCargarAutores() {
        apiService.getAutor().enqueue(new Callback<List<Autor>>() {

            @Override
            public void onResponse(@NonNull Call<List<Autor>> call, @NonNull Response<List<Autor>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    autores.addAll(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Autor>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "No se han podido obtener los autores", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getCargarCategorias() {
        apiService.getCategoria().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(@NonNull Call<List<Categoria>> call, @NonNull Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    categorias.addAll(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Categoria>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "No se han podido obtener las categorias", Toast.LENGTH_LONG).show();
            }
        });
    }
    /*
    private void getCargaUsuarios(){
        apiService.getUsuario().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(@NonNull Call<List<Usuario>> call, @NonNull Response<List<Usuario>> response) {
                assert response.body() != null;
                usuario.addAll(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Usuario>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }*/
}