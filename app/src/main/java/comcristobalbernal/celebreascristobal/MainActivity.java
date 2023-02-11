package comcristobalbernal.celebreascristobal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Fragments.FragmentAutores;
import comcristobalbernal.celebreascristobal.Fragments.FragmentCategoriaFrase;
import comcristobalbernal.celebreascristobal.Fragments.FragmentCategorias;
import comcristobalbernal.celebreascristobal.Fragments.FragmentAutorFrases;
import comcristobalbernal.celebreascristobal.Utils.SettingActivity;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.interfaces.IAutorFrase;
import comcristobalbernal.celebreascristobal.interfaces.ICategoriaFrase;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.models.Frase;
import comcristobalbernal.celebreascristobal.models.Usuario;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FragmentCategorias.IOnAttach,
        FragmentAutores.IOnAttach, IAutorFrase, ICategoriaFrase,
        FragmentAutorFrases.IOnAttachListenerAutorFrase,
        FragmentCategoriaFrase.IOnAttachListener {

    private IAPIService apiService;
    private SharedPreferences prefs;
    private List<Autor> autores;
    private List<Frase> frases;
    private List<Categoria> categorias;
    private Autor autorSeleccionado;
    private Categoria categoriaSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = RestClient.getInstance();
        autores = new ArrayList<>();
        categorias = new ArrayList<>();
        frases = new ArrayList<>();
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
        getFrases();
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
    @Override
    public List<Frase> getFrasesAutor() {
        return frases;
    }

    @Override
    public Autor getAutorSeleccionado() {
        return autorSeleccionado;
    }

    @Override
    public void onCategoriaSeleccionada(int id) {
        categoriaSeleccionado = categorias.get(id);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .replace(R.id.frgMain, FragmentCategoriaFrase.class, null)
                .commit();
    }

    @Override
    public List<Frase> getFrasesCategoria() {
        return frases;
    }

    @Override
    public Categoria getCategoriaSeleccionada() {
        return categoriaSeleccionado;
    }

    @Override
    public void onAutorFraseSeleccionado(int id) {
        autorSeleccionado = autores.get(id);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .replace(R.id.frgMain, FragmentAutorFrases.class, null)
                .commit();
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
    public void getFrases() {
        apiService.getFrases().enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(@NonNull Call<List<Frase>> call, @NonNull Response<List<Frase>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    frases.addAll(response.body());
                    Log.i(MainActivity.class.getSimpleName(),frases.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Frase>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "No se han podido obtener las frases", Toast.LENGTH_LONG).show();
            }
        });
    }
}