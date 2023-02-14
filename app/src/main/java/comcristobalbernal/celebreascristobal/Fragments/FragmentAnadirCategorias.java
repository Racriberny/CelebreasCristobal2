package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
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
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAnadirCategorias extends Fragment {
    private IAPIService iapiService;
    private List<Categoria> categorias;
    private EditText edtCategoria;

    public FragmentAnadirCategorias() {
        super(R.layout.anadircategorias);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categorias = new ArrayList<>();
        Button volverAtras = view.findViewById(R.id.volverAtrasCategoria);
        Button anadirCategoria = view.findViewById(R.id.btAñadirAdminCategoria);
        edtCategoria = view.findViewById(R.id.edtAñadirCategorias);
        iapiService = RestClient.getInstance();
        cargarCategorias();

        anadirCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirCategoria();
            }
        });
        volverAtras.setOnClickListener(new View.OnClickListener() {
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

    public void anadirCategoria(){
        String categoria = edtCategoria.getText().toString();
        if (categoria.isEmpty()) {
            edtCategoria.setError("Es necesario escribir algo...");
            edtCategoria.requestFocus();
            return;
        }
        for (int i = 0; i <categorias.size() ; i++) {
            if (categoria.equalsIgnoreCase(categorias.get(i).getNombre())){
                Toast.makeText(getContext(), "Ya existe esta categoria " + categoria, Toast.LENGTH_LONG).show();
                return;
            }
        }
        Call<Boolean> booleanCall = iapiService.addCategoria(new Categoria(categoria));

        booleanCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                Toast.makeText(getContext(), "Se ha creado correctamente la categoria " +categoria, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void cargarCategorias(){
        iapiService.getCategoria().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    categorias.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Toast.makeText(getContext(), "Ha fallado", Toast.LENGTH_LONG).show();
            }
        });
    }
}
