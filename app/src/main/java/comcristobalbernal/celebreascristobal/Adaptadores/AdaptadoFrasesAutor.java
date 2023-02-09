package comcristobalbernal.celebreascristobal.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cristobalbernal.frasecelebrescristobal.R;
import com.cristobalbernal.frasecelebrescristobal.models.Autor;
import com.cristobalbernal.frasecelebrescristobal.models.Frase;

import java.util.List;

public class AdaptadoFrasesAutor extends RecyclerView.Adapter<AdaptadoFrasesAutor.FrasesAutorViewHolder> {
    private final List<Frase> frases;
    private final Autor autor;

    public AdaptadoFrasesAutor(List<Frase> frases, Autor autor) {
        this.frases = frases;
        this.autor = autor;
    }

    @NonNull
    @Override
    public FrasesAutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_frase_autor, parent, false);
        return new FrasesAutorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FrasesAutorViewHolder holder, int position) {
        Frase frase = frases.get(position);
        holder.bindAutorFrase(frase);
    }

    @Override
    public int getItemCount() {
        return frases.size();
    }


    public static class FrasesAutorViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvFrase;
        public FrasesAutorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFrase = itemView.findViewById(R.id.tvAutorFrase);
        }
        public void bindAutorFrase(Frase frase){
            tvFrase.setText(frase.getTexto());
        }
    }
}
