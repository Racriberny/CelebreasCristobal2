package comcristobalbernal.celebreascristobal.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.models.Frase;

public class AdaptadorModificarFrases extends RecyclerView.Adapter<AdaptadorModificarFrases.FrasesViewHolderLista> {
    private List<Frase> frases;

    public AdaptadorModificarFrases(){
        frases = new ArrayList<>();
    }

    @NonNull
    @Override
    public FrasesViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemmodificarfrases,parent,false);
        return new FrasesViewHolderLista(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FrasesViewHolderLista holder, int position) {
        Frase frase = frases.get(position);
        holder.bindFrases(frase);
    }

    @Override
    public int getItemCount() {
        return frases.size();
    }

    public void setData(List<Frase> frases){
        this.frases = frases;
    }


    public static class FrasesViewHolderLista extends RecyclerView.ViewHolder{

        private TextView idFrases;
        private TextView tvFrases;

        public FrasesViewHolderLista(@NonNull View itemView) {
            super(itemView);
            idFrases = itemView.findViewById(R.id.idModificarFrases);
            tvFrases = itemView.findViewById(R.id.tvFrasesModificar);
        }
        public void bindFrases(Frase frase){
            idFrases.setText(String.valueOf(frase.getId()));
            tvFrases.setText(frase.getTexto());
        }
    }
}
