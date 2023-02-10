package comcristobalbernal.celebreascristobal.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.interfaces.ICategoriaFrase;
import comcristobalbernal.celebreascristobal.models.Categoria;

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.CategoriasViewHolder> {
    private final List<Categoria> categorias;
    private ICategoriaFrase listener;

    public AdaptadorCategoria(List<Categoria> categorias,ICategoriaFrase listener){
        this.categorias = categorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_categoria,parent,false);
        return new CategoriasViewHolder(itemView,listener);
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

    public static class CategoriasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView tvNombre;
        private final ICategoriaFrase listener;

        public CategoriasViewHolder(@NonNull View itemView,ICategoriaFrase listener) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvCategoria);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }
        public void bindCategoria(Categoria categoria){
            tvNombre.setText(categoria.getNombre());
        }


        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onCategoriaSeleccionada(getAdapterPosition());
            }
        }
    }


}
