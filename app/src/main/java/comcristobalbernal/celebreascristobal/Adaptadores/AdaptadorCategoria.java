package comcristobalbernal.celebreascristobal.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.models.Categoria;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.CategoriasViewHolder> {
    private final List<Categoria> categorias;

    public AdaptadorCategoria(List<Categoria> categorias){
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public CategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_categoria,parent,false);
        return new CategoriasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasViewHolder holder, int position) {
        Categoria categoria = categorias.get(position);
        holder.bindCategoria(categoria);
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    public static class CategoriasViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNombre;
        public CategoriasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvCategoria);
        }
        public void bindCategoria(Categoria categoria){
            tvNombre.setText(categoria.getNombre());
        }


    }


}
