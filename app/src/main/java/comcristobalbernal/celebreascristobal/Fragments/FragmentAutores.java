package comcristobalbernal.celebreascristobal.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorAutor;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.IAutorFrase;
import comcristobalbernal.celebreascristobal.models.Autor;

public class FragmentAutores extends Fragment {

    public interface IOnAttach{
        List<Autor> getAutor();
    }

    private List<Autor> autors;

    private IAutorFrase listener;

    public FragmentAutores(){
        super(R.layout.lista);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdaptadorAutor adaptadorAutor = new AdaptadorAutor(autors,listener);
        RecyclerView recyclerView = view.findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorAutor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttach attach = (IOnAttach) context;
        autors = attach.getAutor();
        listener = (IAutorFrase) context;
    }
}
