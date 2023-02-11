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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.litimte_categoria_frases, parent, false);

        return new CategoriFraseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriFraseViewHolder holder, int position) {
        Frase frase = frases.get(position);

        holder.bindCategoriaFrase(frase);
    }

    @Override
    public int getItemCount() {
        return frases.size();
    }

    public class CategoriFraseViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvFrase;

        public CategoriFraseViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvFrase = itemView.findViewById(R.id.tvFragCateg);
        }

        public void bindCategoriaFrase(Frase frase) {
            tvFrase.setText(frase.getTexto());
        }
    }
}
