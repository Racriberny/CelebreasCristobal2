package comcristobalbernal.celebreascristobal.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.models.Categoria;

public class AdaptadorCategoriasModificarLista extends RecyclerView.Adapter<AdaptadorCategoriasModificarLista.CategoriaViewHolderLista> {

    private List<Categoria> categorias;

    public AdaptadorCategoriasModificarLista(){
        categorias = new ArrayList<>();
    }

    @NonNull
    @Override
    public CategoriaViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemmodicarcategorias,parent,false);
        return new CategoriaViewHolderLista(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolderLista holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.bindCategoria(categoria);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public void setData(List<Categoria> categoriaList) {
        this.categorias = categoriaList;
    }

    public static class CategoriaViewHolderLista extends RecyclerView.ViewHolder{
        private TextView id;
        private TextView tvCategoria;

        public CategoriaViewHolderLista(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idModificarCategoria);
            tvCategoria = itemView.findViewById(R.id.tvCategoriaModificar);
        }
        public void bindCategoria(Categoria categoria){
            id.setText(String.valueOf(categoria.getId()));
            tvCategoria.setText(categoria.getNombre());
        }
    }
}
