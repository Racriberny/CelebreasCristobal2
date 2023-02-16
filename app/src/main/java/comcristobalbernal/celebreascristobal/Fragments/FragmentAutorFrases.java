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

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadoFrasesAutor;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.models.Frase;

public class FragmentAutorFrases extends Fragment {
    public interface IOnAttachListenerAutorFrase {
        List<Frase> getFrasesAutor();
        Autor getAutorSeleccionado();
    }

    private List<Frase> frases;
    private List<Frase> frasesAutor;
    private Autor autor;

    public FragmentAutorFrases(){
        super(R.layout.lista);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frasesAutor = new ArrayList<>();
        RecyclerView rvLista = view.findViewById(R.id.rvLista);
        for (Frase frasess : frases) {
            if (frasess.getAutorId() == autor.getId()) {
                frasesAutor.add(frasess);
            }
        }
        AdaptadoFrasesAutor adaptadorFrases = new AdaptadoFrasesAutor(frasesAutor, autor);
        rvLista.setHasFixedSize(true);
        rvLista.setAdapter(adaptadorFrases);
        rvLista.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListenerAutorFrase attachListener = (IOnAttachListenerAutorFrase) context;
        frases = attachListener.getFrasesAutor();
        autor = attachListener.getAutorSeleccionado();
    }
}
