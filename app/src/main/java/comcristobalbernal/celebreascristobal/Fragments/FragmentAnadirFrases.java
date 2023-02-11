package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.models.Frase;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAnadirFrases extends Fragment {
    private IAPIService iapiService;
    private EditText autor;
    private EditText categoria;
    private EditText fechaProgramada;
    private EditText texto;

    private List<Autor> autorList;
    private List<Categoria> categoriaList;

    public FragmentAnadirFrases(){
        super(R.layout.anadirfrase);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        autorList = new ArrayList<>();
        categoriaList = new ArrayList<>();

        autor = view.findViewById(R.id.edtAutorFrase);
        categoria = view.findViewById(R.id.edtCategoriaFrase);
        fechaProgramada = view.findViewById(R.id.edtFechaFrase);
        texto = view.findViewById(R.id.edtTextoFrase);
        Button button = view.findViewById(R.id.btAñadirAdminFrase);
        getCargarAutores();
        cargarCategorias();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirFrase();
            }
        });


    }

    private void anadirFrase() {
        Boolean validacionAutores = null;
        Boolean validacionCategorias = null;
        int autoId = Integer.parseInt(autor.getText().toString());
        String autorIdString  = String.valueOf(autoId);
        int categoriaId = Integer.parseInt(categoria.getText().toString());
        String categoriaIdString = String.valueOf(categoriaId);
        String fechaProgradama = fechaProgramada.getText().toString();
        String text = texto.getText().toString();

        if (autorIdString.isEmpty()) {
            autor.setError("Es necesario escribir algo...");
            autor.requestFocus();
            return;
        }
        if (categoriaIdString.isEmpty()) {
            categoria.setError("Es necesario escribir algo...");
            categoria.requestFocus();
            return;
        }
        if (fechaProgradama.isEmpty()) {
            fechaProgramada.setError("Es necesario escribir algo...");
            fechaProgramada.requestFocus();
            return;
        }
        if (text.isEmpty()) {
            texto.setError("Es necesario escribir algo...");
            texto.requestFocus();
            return;
        }
        for (int i = 0; i <autorList.size() ; i++) {
            if (autoId == autorList.get(i).getId()){
                validacionAutores = true;
            }else {
                validacionAutores =false;
            }
        }
        for (int i = 0; i <categoriaList.size() ; i++) {
            if (categoriaId == categoriaList.get(i).getId()){
                validacionCategorias = true;
            }else {
                validacionCategorias = false;
            }
        }
        if (Boolean.TRUE.equals(validacionAutores) && Boolean.TRUE.equals(validacionCategorias)){
            Toast.makeText(getContext(),"Puedes crear la frase", Toast.LENGTH_LONG).show();
            crearFrase(autoId,categoriaId,fechaProgradama,text);
        }else if (Boolean.FALSE.equals(validacionAutores) && Boolean.FALSE.equals(validacionCategorias)){
            Toast.makeText(getContext(),"La CategoriaId o el AutorId no existen!!!",Toast.LENGTH_SHORT).show();
        }
    }

    private void crearFrase(int autoId, int categoriaId, String fechaProgradama, String text) {
        iapiService.addFrase(new Frase(text,fechaProgradama,autoId,categoriaId)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if(response.isSuccessful()) {
                    if(Boolean.TRUE.equals(response.body())) {
                        Log.i(String.valueOf(getContext()), "Frase añadida correctamente");
                    } else {
                        Log.i(String.valueOf(getContext()), "Error al añadir la frase");

                        Log.i(String.valueOf(getContext()), response.raw().toString());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void getCargarAutores() {
        iapiService.getAutor().enqueue(new Callback<List<Autor>>() {

            @Override
            public void onResponse(@NonNull Call<List<Autor>> call, @NonNull Response<List<Autor>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    autorList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Autor>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Ha fallado", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cargarCategorias(){
        iapiService.getCategoria().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    categoriaList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getContext(), "Ha fallado", Toast.LENGTH_LONG).show();
            }
        });
    }


}
