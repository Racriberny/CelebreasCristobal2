package comcristobalbernal.celebreascristobal.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cristobalbernal.frasecelebrescristobal.Adaptadores.AdaptadoFrasesAutor;
import com.cristobalbernal.frasecelebrescristobal.R;
import com.cristobalbernal.frasecelebrescristobal.models.Autor;
import com.cristobalbernal.frasecelebrescristobal.models.Frase;

import java.util.ArrayList;
import java.util.List;

public class FragmentFraseAutor extends Fragment {

    public interface IOnAttachListenerAutorFrase {
        List<Frase> getFrasesAutor();
        Autor getAutorSeleccionado();
    }

    private List<Frase> frases;
    private final List<Frase> frasesAutor = new ArrayList<>();
    private Autor autor;

    public FragmentFraseAutor(){
        super(R.layout.lista);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvLista = view.findViewById(R.id.rvLista);
        for (Frase frase : frases) {
            if (frase.getId() == autor.getId()) {
                frasesAutor.add(frase);
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
