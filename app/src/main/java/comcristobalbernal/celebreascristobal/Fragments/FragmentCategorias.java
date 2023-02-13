package comcristobalbernal.celebreascristobal.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorCategoria;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.interfaces.ICategoriaFrase;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategorias extends Fragment {
    private List<Categoria> categorias;
    private IAPIService iapiService;
    public FragmentCategorias(){
        super(R.layout.lista);
    }
    private ICategoriaFrase listener;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        RecyclerView recyclerView = view.findViewById(R.id.rvLista);
        categorias = new ArrayList<>();

        iapiService.getCategoria().enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                assert response.body() != null;
                categorias.addAll(response.body());
                AdaptadorCategoria adaptadorAutor = new AdaptadorCategoria(categorias,listener);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adaptadorAutor);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {

            }
        });




    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (ICategoriaFrase) context;
    }
}
