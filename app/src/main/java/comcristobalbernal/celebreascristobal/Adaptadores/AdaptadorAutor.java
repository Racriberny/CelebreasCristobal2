package comcristobalbernal.celebreascristobal.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import comcristobalbernal.celebreascristobal.R;
import comcristobalbernal.celebreascristobal.models.Autor;

public class AdaptadorAutor extends RecyclerView.Adapter<AdaptadorAutor.AutorViewHolder>{
    private final List<Autor> autorList;
    

    public AdaptadorAutor(List<Autor> autors){
        this.autorList = autors;
    }

    @NonNull
    @Override
    public AutorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_autores,parent,false);
        return new AutorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AutorViewHolder holder, int position) {
        Autor autor = autorList.get(position);
        holder.bindAutores(autor);
    }

    @Override
    public int getItemCount() {
        return autorList.size();
    }


    public static class AutorViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNombreAutor;
        private final TextView tvProfession;
        public AutorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreAutor = itemView.findViewById(R.id.tvNombreAutor);
            tvProfession = itemView.findViewById(R.id.tvProfession);
        }

        public void bindAutores(Autor autor){
            tvNombreAutor.setText(autor.getNombre());
            tvProfession.setText(autor.getProfesion());
        }

    }

}
