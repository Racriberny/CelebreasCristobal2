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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorCategoriasModificarLista;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategoriasModificarLista extends Fragment {
    private IAPIService iapiService;
    private AdaptadorCategoriasModificarLista adaptadorCategoriasMLista;
    private List<Categoria> categoriaList;
    private EditText categoriaModificada;
    private EditText id;

    public FragmentCategoriasModificarLista(){
        super(R.layout.rvlistamodificar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        categoriaList = new ArrayList<>();
        adaptadorCategoriasMLista = new AdaptadorCategoriasModificarLista();
        id = view.findViewById(R.id.idListaCategoria);
        categoriaModificada = view.findViewById(R.id.modificacionCategoria);
        Button buttonModificar = view.findViewById(R.id.botonModificarCategoria);
        Button buttonVolver = view.findViewById(R.id.btVolverIncioModifcarCategoria);
        RecyclerView recyclerView = view.findViewById(R.id.rvListaModificar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorCategoriasMLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getCargarCategorias();
        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarCategoria();
            }
        });
        buttonVolver.setOnClickListener(new View.OnClickListener() {
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

    public void getCargarCategorias() {
        iapiService.getCategoria().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(@NonNull Call<List<Categoria>> call, @NonNull Response<List<Categoria>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    categoriaList.addAll(response.body());
                    adaptadorCategoriasMLista.setData(categoriaList);
                    adaptadorCategoriasMLista.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Categoria>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "No se han podido obtener las categorias", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void modificarCategoria(){

        if (id.getText().toString().isEmpty()){
            id.setError("Es necesario escribir algo...");
            id.requestFocus();
            return;
        }
        int idCategoria = Integer.parseInt(id.getText().toString());
        String cambiaCategoria = categoriaModificada.getText().toString();
        if (cambiaCategoria.isEmpty()){
            categoriaModificada.setError("Es necesario escribir algo...");
            categoriaModificada.requestFocus();
            return;
        }
        Categoria categoria = new Categoria(idCategoria,cambiaCategoria);

        iapiService.modificarCategoria(categoria).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                Toast.makeText(getContext(),"Categoria modificada",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
