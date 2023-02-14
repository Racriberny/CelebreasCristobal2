package comcristobalbernal.celebreascristobal.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAPIService;
import comcristobalbernal.celebreascristobal.models.Usuario;
import comcristobalbernal.celebreascristobal.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMain extends Fragment {

    private List<Usuario> usuarioList;
    private IAPIService iapiService;
    String usuario;
    public interface IOnActivoUser{
        String usuario();
    }
    public FragmentMain() {
        super(R.layout.layout_principal);
    }
    private Button btnAdmin;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usuarioList = new ArrayList<>();
        iapiService = RestClient.getInstance();
        Button btFrases = view.findViewById(R.id.btConsultasFrases);
        btnAdmin = view.findViewById(R.id.btAdmin);
        Button btAutores = view.findViewById(R.id.btConsultasActores);
        Button btCategorias = view.findViewById(R.id.btConsultasCategorias);
        cargarUsuarios();

        btFrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.frgMain, FragmentFrases.class, null)
                        .commit();
            }
        });

        btAutores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.frgMain, FragmentAutores.class, null)
                        .commit();
            }
        });
        btCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.frgMain, FragmentCategorias.class, null)
                        .commit();
            }
        });
    }

    public void cargarUsuarios(){
        iapiService.getUsuario().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                assert response.body() != null;
                usuarioList.addAll(response.body());

                for (int i = 0; i <usuarioList.size() ; i++) {
                    if (usuario.equalsIgnoreCase(usuarioList.get(i).getNombre())){
                        if (usuarioList.get(i).isAdmin() == 0){
                            btnAdmin.setVisibility(View.GONE);
                        }else if(usuarioList.get(i).isAdmin() == 1){
                            btnAdmin.setVisibility(View.VISIBLE);

                            btnAdmin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    FragmentManager manager = getParentFragmentManager();
                                    manager.beginTransaction()
                                            .setReorderingAllowed(true)
                                            .addToBackStack(null)
                                            .replace(R.id.frgMain, FragmentAdmin.class, null)
                                            .commit();
                                }
                            });

                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

            }
        });
    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnActivoUser iOnActivoUser = (IOnActivoUser) context;
        iOnActivoUser.usuario();
        usuario = iOnActivoUser.usuario();
    }
}
