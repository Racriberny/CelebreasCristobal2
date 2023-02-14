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
import androidx.fragment.app.FragmentManager;

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
        getCargarAutores();
        cargarCategorias();
        autor = view.findViewById(R.id.edtAutorFrase);
        categoria = view.findViewById(R.id.edtCategoriaFrase);
        fechaProgramada = view.findViewById(R.id.edtFechaFrase);
        texto = view.findViewById(R.id.edtTextoFrase);
        Button anadir = view.findViewById(R.id.btAñadirAdminFrase);
        Button volver = view.findViewById(R.id.btVolverIncioFrases);
        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirFrase();
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.frgMain, FragmentMain.class, null)
                        .commit();
            }
        });
    }

    private void anadirFrase() {
        boolean validacion = false;
        String fechaProgradama = fechaProgramada.getText().toString();
        String text = texto.getText().toString();
        if (autor.getText().toString().isEmpty()) {
            autor.setError("Es necesario escribir algo...");
            autor.requestFocus();
            return;
        }
        if (categoria.getText().toString().isEmpty()) {
            categoria.setError("Es necesario escribir algo...");
            categoria.requestFocus();
            return;
        }
        if (fechaProgradama.length() !=10) {
            fechaProgramada.setError("La longitud tiene que ser de 10 caracteres y con esta formato 0000-00-00!!!");
            fechaProgramada.requestFocus();
            return;
        }
        if (text.isEmpty()) {
            texto.setError("Es necesario escribir algo...");
            texto.requestFocus();
            return;
        }
        int autoId = Integer.parseInt(autor.getText().toString());
        int categoriaId = Integer.parseInt(categoria.getText().toString());
        for (int i = 0; i < autorList.size() ; i++) {
            if (autoId == autorList.get(i).getId()){
                validacion =true;
            }
        }
        if (!validacion){
            Toast.makeText(getContext(),"No existe autor",Toast.LENGTH_SHORT).show();
            return;
        }
        validacion = false;
        for (int j = 0; j <categoriaList.size() ; j++) {
            if (categoriaId == categoriaList.get(j).getId()){
                validacion = true;
            }
        }
        if (!validacion){
            Toast.makeText(getContext(),"No existe categoria",Toast.LENGTH_SHORT).show();
            return;
        }

        iapiService.addFrase(new Frase(text,fechaProgradama,autoId,categoriaId)).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                Toast.makeText(getContext(),"Se ha añadido!!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

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
                    Log.i(String.valueOf(getContext()),autorList.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Autor>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Ha fallado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cargarCategorias(){
        iapiService.getCategoria().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(@NonNull Call<List<Categoria>> call, @NonNull Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    categoriaList.addAll(response.body());
                    Log.i(String.valueOf(getContext()),categoriaList.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Categoria>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Ha fallado", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
