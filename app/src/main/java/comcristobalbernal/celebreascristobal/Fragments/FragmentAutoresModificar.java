package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorAutoresModificar;
import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorCategoriasModificarLista;
import comcristobalbernal.celebreascristobal.MainActivity;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAutoresModificar extends Fragment {

    private IAPIService iapiService;
    private AdaptadorAutoresModificar adaptadorAutoresModificar;
    private List<Autor> autorList;
    private EditText id;
    private EditText muerte;
    private EditText nacimiento;
    private EditText nombre;
    private EditText profession;

    public FragmentAutoresModificar(){
        super(R.layout.rvlistamodificarautores);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        autorList = new ArrayList<>();
        adaptadorAutoresModificar = new AdaptadorAutoresModificar();
        RecyclerView recyclerView = view.findViewById(R.id.rvModificarAutores);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorAutoresModificar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getCargarAutores();
    }


    public void getCargarAutores() {
        iapiService.getAutor().enqueue(new Callback<List<Autor>>() {

            @Override
            public void onResponse(@NonNull Call<List<Autor>> call, @NonNull Response<List<Autor>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    autorList.addAll(response.body());
                    adaptadorAutoresModificar.setData(autorList);
                    adaptadorAutoresModificar.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Autor>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "No se han podido obtener los autores", Toast.LENGTH_LONG).show();
            }
        });
    }
}
