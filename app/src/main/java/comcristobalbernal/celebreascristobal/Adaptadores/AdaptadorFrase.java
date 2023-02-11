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
import comcristobalbernal.celebreascristobal.models.Frase;


public class AdaptadorFrase extends RecyclerView.Adapter<AdaptadorFrase.FraseViewHolder>{

    private List<Frase> frases;

    public AdaptadorFrase(){
        frases = new ArrayList<>();
    }

    public void setData(List<Frase> frases){
        this.frases = frases;
    }
    @NonNull
    @Override
    public FraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_frase,parent,false);
        return new FraseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FraseViewHolder holder, int position) {
        Frase frase = frases.get(position);
        holder.bindFrases(frase);
    }

    @Override
    public int getItemCount() {
        return frases.size();
    }

    public static class FraseViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvFrase;

        public FraseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFrase = itemView.findViewById(R.id.tvTextoFrase);
        }
        public void bindFrases(Frase frase) {
            tvFrase.setText(frase.getTexto());
        }
    }

}
