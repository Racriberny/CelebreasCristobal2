package comcristobalbernal.celebreascristobal.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import comcristobalbernal.celebreascristobal.R;

public class FragmentMain extends Fragment {

    private Button btFrases, btnAdmin, btAutores, btCategorias;


    public FragmentMain() {
        super(R.layout.layout_principal);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btFrases = view.findViewById(R.id.btConsultasFrases);
        btnAdmin = view.findViewById(R.id.btAdmin);
        btAutores = view.findViewById(R.id.btConsultasActores);
        btCategorias = view.findViewById(R.id.btConsultasCategorias);

        /*
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
        });*/

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


}
