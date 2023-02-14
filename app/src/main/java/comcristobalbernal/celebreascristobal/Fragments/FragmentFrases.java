package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorFrase;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Frase;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFrases extends Fragment {
    private IAPIService iapiService;
    private AdaptadorFrase adaptadorFrase;
    private List<Frase> frases;

    public FragmentFrases(){
        super(R.layout.lista);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        frases = new ArrayList<>();
        adaptadorFrase = new AdaptadorFrase();
        RecyclerView recyclerView = view.findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorFrase);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getFrasesDevolver();
    }

    public void getFrasesDevolver() {
        iapiService.getFrases().enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(@NonNull Call<List<Frase>> call, @NonNull Response<List<Frase>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    frases.addAll(response.body());
                    adaptadorFrase.setData(frases);
                    adaptadorFrase.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Frase>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
