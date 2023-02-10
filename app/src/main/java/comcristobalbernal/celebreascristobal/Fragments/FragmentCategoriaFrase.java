package comcristobalbernal.celebreascristobal.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.Adaptadores.AdapatadorCategoriaFrase;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.models.Frase;

public class FragmentCategoriaFrase extends Fragment {

    public interface IOnAttachListener {
        List<Frase> getFrasesCategoria();
        Categoria getCategoriaSeleccionada();
    }
    private List<Frase> frases;
    private  List<Frase> frasesCategoria;
    private Categoria categoria;

    public FragmentCategoriaFrase() {
        super(R.layout.lista);
    }



    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvLista = view.findViewById(R.id.rvLista);
        frasesCategoria = new ArrayList<>();
        for (Frase frase : frases) {
            if (frase.getIdCategoria() == categoria.getId()) {
                frasesCategoria.add(frase);
            }
        }

        AdapatadorCategoriaFrase adaptadorFrases = new AdapatadorCategoriaFrase(frasesCategoria, categoria);
        rvLista.setHasFixedSize(true);
        rvLista.setAdapter(adaptadorFrases);
        rvLista.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener attachListener = (IOnAttachListener) context;
        frases = attachListener.getFrasesCategoria();
        categoria = attachListener.getCategoriaSeleccionada();
    }
}
