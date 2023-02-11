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

import comcristobalbernal.celebreascristobal.MainActivity;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAnadirAutor extends Fragment {
    private IAPIService iapiService;
    private List<Autor> autors;
    private EditText edtMuerte;
    private EditText edtNacimiento;
    private EditText edtNombre;
    private EditText edtProfession;

    public FragmentAnadirAutor() {
        super(R.layout.anadirauto);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        getCargarAutores();
        edtMuerte = view.findViewById(R.id.edtMuerte);
        edtNacimiento = view.findViewById(R.id.edtNacimiento);
        edtNombre = view.findViewById(R.id.edtNombre);
        edtProfession = view.findViewById(R.id.edtProfession);
        autors = new ArrayList<>();
        Button btAnadir = view.findViewById(R.id.btAñadirAdminAutores);
        Button btInicio = view.findViewById(R.id.btVolverInicioAutores);

        btAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirAutor();
            }
        });
        btInicio.setOnClickListener(new View.OnClickListener() {
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
    //Probar...
    private void anadirAutor() {
        Boolean validacion = null;
        String muerte = edtMuerte.getText().toString();
        int nacimiento = Integer.parseInt(edtNacimiento.getText().toString());
        int muerteString = Integer.parseInt(muerte);
        String nacimientoComprobacion = String.valueOf(nacimiento);
        String nombre = edtNombre.getText().toString();
        String profession = edtProfession.getText().toString();
        if (muerte.isEmpty()) {
            edtNombre.setError("Es necesario escribir algo...");
            edtNombre.requestFocus();
            return;
        }
        if (nacimientoComprobacion.isEmpty()) {
            edtNombre.setError("Es necesario escribir algo...");
            edtNombre.requestFocus();
            return;
        }
        if (nombre.isEmpty()) {
            edtNombre.setError("Es necesario escribir algo...");
            edtNombre.requestFocus();
            return;
        }
        if (profession.isEmpty()) {
            edtProfession.setError("Es necesario escribir algo...");
            edtProfession.requestFocus();
            return;
        }
        if (nacimiento > muerteString){
            validacion = false;
            Toast.makeText(getContext(),"La muerte tiene que ser superior al nacimiento o igual!!!",Toast.LENGTH_LONG).show();
        }
        for (int i = 0; i < autors.size() ; i++) {
            validacion = nombre.equalsIgnoreCase(autors.get(i).getNombre());
        }
        if (Boolean.TRUE.equals(validacion)){
            Toast.makeText(getContext(), "Ya existe este autor", Toast.LENGTH_LONG).show();
        }else if (Boolean.FALSE.equals(validacion)){
            Call<Boolean> booleanCall = iapiService.addAutor(new Autor(nombre,nacimiento,muerte,profession));

            booleanCall.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                    for (int i = 0; i <autors.size() ; i++) {
                        if (nombre.equalsIgnoreCase(autors.get(i).getNombre())){
                            Toast.makeText(getContext(),"Este autor ya existe manolo",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getContext(),"Has añadido el autor con nombre " + nombre,Toast.LENGTH_LONG).show();
                        }
                    }
                    if (Boolean.TRUE.equals(response.body())){
                        Toast.makeText(getContext(),"Has añadido el autor con nombre " + nombre,Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    public void getCargarAutores() {
        iapiService.getAutor().enqueue(new Callback<List<Autor>>() {

            @Override
            public void onResponse(@NonNull Call<List<Autor>> call, @NonNull Response<List<Autor>> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    autors.addAll(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Autor>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Ha fallado", Toast.LENGTH_LONG).show();
            }
        });
    }
}
