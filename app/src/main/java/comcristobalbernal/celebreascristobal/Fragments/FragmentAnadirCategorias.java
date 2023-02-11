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

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentAnadirCategorias extends Fragment {
    private IAPIService iapiService;

    private EditText edtCategoria;

    public FragmentAnadirCategorias() {
        super(R.layout.anadircategorias);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button volverAtras = view.findViewById(R.id.volverAtrasCategoria);
        Button anadirCategoria = view.findViewById(R.id.btAñadirAdminCategoria);
        edtCategoria = view.findViewById(R.id.edtAñadirCategorias);
        iapiService = RestClient.getInstance();

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

        Call<Boolean> booleanCall = iapiService.addCategoria(new Categoria(categoria));

        booleanCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if (Boolean.TRUE.equals(response.body())){
                    Toast.makeText(getContext(),"Has añadido la categoria " + categoria,Toast.LENGTH_LONG).show();

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
