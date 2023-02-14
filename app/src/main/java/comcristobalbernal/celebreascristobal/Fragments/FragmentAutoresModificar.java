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
        Button botonModificar = view.findViewById(R.id.btAdminModificarAutor);
        id = view.findViewById(R.id.idModificarAutores);
        muerte = view.findViewById(R.id.edtMuerteModificarAutores);
        nacimiento = view.findViewById(R.id.edtNacimientoAutores);
        nombre = view.findViewById(R.id.edtNombreAutores);
        profession = view.findViewById(R.id.edtProfesionAutores);
        RecyclerView recyclerView = view.findViewById(R.id.rvModificarAutores);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorAutoresModificar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getCargarAutores();

        botonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarAutores();
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
                    adaptadorAutoresModificar.setData(autorList);
                    adaptadorAutoresModificar.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Autor>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "No se han podido obtener los autores", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void modificarAutores(){
        int idAutor = Integer.parseInt(id.getText().toString());
        String muerteAutor = muerte.getText().toString();
        int nacimientoAutor = Integer.parseInt(nacimiento.getText().toString());
        String nombreAutor = nombre.getText().toString();
        String professionAuto = profession.getText().toString();
        Autor autorModificado = new Autor(idAutor,nombreAutor,nacimientoAutor,muerteAutor,professionAuto);

        iapiService.modificarAutores(autorModificado).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Toast.makeText(getContext(),"Autor modificada",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
