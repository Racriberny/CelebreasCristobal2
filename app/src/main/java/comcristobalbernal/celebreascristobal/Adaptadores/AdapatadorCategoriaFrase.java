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
import comcristobalbernal.celebreascristobal.models.Frase;

public class AdapatadorCategoriaFrase extends RecyclerView.Adapter<AdapatadorCategoriaFrase.CategoriFraseViewHolder>{

    private final List<Frase> frases;

    private final Categoria categoria;

    public AdapatadorCategoriaFrase(List<Frase> frases, Categoria categoria){
        this.frases = frases;
        this.categoria = categoria;
    }

    @NonNull
    @Override
    public CategoriFraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_frase_autor, parent, false);

        return new CategoriFraseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriFraseViewHolder holder, int position) {
        Frase frase = frases.get(position);

        holder.bindCategoriaFrase(frase);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CategoriFraseViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvCategoria;
        private final TextView tvFrase;

        public CategoriFraseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCategoria = itemView.findViewById(R.id.tvCategoria);
            this.tvFrase = itemView.findViewById(R.id.tvFraseCateg);
        }

        public void bindCategoriaFrase(Frase frase) {
            tvCategoria.setText(categoria.getNombre());
            tvFrase.setText(frase.getTexto());
        }
    }
}
