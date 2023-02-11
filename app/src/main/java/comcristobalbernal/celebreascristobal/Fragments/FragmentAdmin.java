package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import comcristobalbernal.celebreascristobal.R;

public class FragmentAdmin extends Fragment {
    private Button btAñadirFrase;
    private Button btnModificarFrase;
    private Button btModificarCategoria;
    private Button btModificarAutor;


    public FragmentAdmin() {
        super(R.layout.layout_admin);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btAnadirCategoria = view.findViewById(R.id.btAdminCategoria);
        Button btAnadirAutor = view.findViewById(R.id.btAdminAutor);
        btAnadirCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.frgMain, FragmentAnadirCategorias.class, null)
                        .commit();
            }
        });

        btAnadirAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .replace(R.id.frgMain, FragmentAnadirAutor.class, null)
                        .commit();

            }
        });

    }
}
