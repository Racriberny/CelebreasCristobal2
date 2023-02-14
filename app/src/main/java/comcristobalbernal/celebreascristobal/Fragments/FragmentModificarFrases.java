package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorModificarFrases;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.models.Frase;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentModificarFrases extends Fragment {
    private IAPIService iapiService;
    private AdaptadorModificarFrases adaptadorModificarFrases;
    private List<Frase> frases;
    private EditText fraseModificar;
    private EditText idFrase;

    public FragmentModificarFrases(){
        super(R.layout.rvlistamodificarfrases);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        frases = new ArrayList<>();
        adaptadorModificarFrases = new AdaptadorModificarFrases();
        idFrase = view.findViewById(R.id.idModificarFraseAdmin);
        fraseModificar = view.findViewById(R.id.edtModificarFrase);
        Button btModificar = view.findViewById(R.id.tModificarAdminFrase);
        RecyclerView recyclerView = view.findViewById(R.id.rvmodificarfrase);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorModificarFrases);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getCargarFrases();
        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarFrase();
            }
        });
    }


    public void getCargarFrases(){
        iapiService.getFrases().enqueue(new Callback<List<Frase>>() {
            @Override
            public void onResponse(@NonNull Call<List<Frase>> call, @NonNull Response<List<Frase>> response) {
                assert response.body() != null;
                frases.addAll(response.body());
                adaptadorModificarFrases.setData(frases);
                adaptadorModificarFrases.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<List<Frase>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void modificarFrase(){
        int id = Integer.parseInt(idFrase.getText().toString());
        String frase = fraseModificar.getText().toString();
        Frase fraseModificar = new Frase(id,frase);

        iapiService.modificarFrases(fraseModificar).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                Toast.makeText(getContext(),"Frase modificada",Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
