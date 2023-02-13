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

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorAutor;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.interfaces.IAutorFrase;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAutores extends Fragment {
    private IAPIService iapiService;
    private List<Autor> autors;

    private IAutorFrase listener;

    public FragmentAutores(){
        super(R.layout.lista);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        RecyclerView recyclerView = view.findViewById(R.id.rvLista);
        autors = new ArrayList<>();

        iapiService.getAutor().enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                assert response.body() != null;
                autors.addAll(response.body());
                AdaptadorAutor adaptadorAutor = new AdaptadorAutor(autors,listener);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adaptadorAutor);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (IAutorFrase) context;
    }
}
