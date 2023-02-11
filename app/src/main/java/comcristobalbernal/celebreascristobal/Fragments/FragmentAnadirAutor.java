package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAnadirAutor extends Fragment {
    private IAPIService iapiService;

    private EditText edtMuerte;
    private EditText edtNacimiento;
    private EditText edtNombre;
    private EditText edtProfession;
    private Button btAnadir;

    public FragmentAnadirAutor() {
        super(R.layout.anadirauto);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iapiService = RestClient.getInstance();
        edtMuerte = view.findViewById(R.id.edtMuerte);
        edtNacimiento = view.findViewById(R.id.edtNacimiento);
        edtNombre = view.findViewById(R.id.edtNombre);
        edtProfession = view.findViewById(R.id.edtProfession);
        btAnadir = view.findViewById(R.id.btAñadirAdminAutores);

        btAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirAutor();
            }
        });

    }

    private void anadirAutor() {
        String muerte = edtMuerte.getText().toString();
        int nacimiento = Integer.parseInt(edtNacimiento.getText().toString());
        int muerteString = Integer.parseInt(muerte);
        String nombre = edtNombre.getText().toString();
        String profession = edtProfession.getText().toString();
        //Aqui se deberia de hacer una comprobacion que el año de naciemiento no sea inferior al de muerte.

        if (nacimiento < muerteString){

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
        Call<Boolean> booleanCall = iapiService.addAutor(new Autor(nombre,nacimiento,muerte,profession));

        booleanCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
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
