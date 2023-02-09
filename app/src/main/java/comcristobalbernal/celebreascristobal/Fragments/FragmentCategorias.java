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

import comcristobalbernal.celebreascristobal.Adaptadores.AdaptadorCategoria;
import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.models.Categoria;

public class FragmentCategorias extends Fragment {
    private List<Categoria> categorias;
    public FragmentCategorias(){
        super(R.layout.lista);
    }

    public interface IOnAttach{
        List<Categoria> getCategorias();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdaptadorCategoria adaptadorAutor = new AdaptadorCategoria(categorias);
        RecyclerView recyclerView = view.findViewById(R.id.rvLista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adaptadorAutor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttach iOnAttach = (IOnAttach) context;
        categorias = iOnAttach.getCategorias();

    }
}
