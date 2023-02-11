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
import comcristobalbernal.celebreascristobal.models.Autor;

public class AdaptadorAutoresModificar extends RecyclerView.Adapter<AdaptadorAutoresModificar.AutoresViewHolderLista> {
    private List<Autor> autorList;

    public AdaptadorAutoresModificar(){
        autorList = new ArrayList<>();
    }

    @NonNull
    @Override
    public AutoresViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemmodicarautores,parent,false);
        return new AutoresViewHolderLista(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AutoresViewHolderLista holder, int position) {
        Autor autor = autorList.get(position);
        holder.bindAutores(autor);
    }

    @Override
    public int getItemCount() {
        return autorList.size();
    }

    public void setData(List<Autor> autorList){
        this.autorList = autorList;
    }


    public static class AutoresViewHolderLista extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView muerte;
        private TextView nacimiento;
        private TextView nombre;
        private TextView profession;

        public AutoresViewHolderLista(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idAutoresModificar);
            muerte = itemView.findViewById(R.id.muerteAutoresModificar);
            nacimiento = itemView.findViewById(R.id.nacimientoAutoresModificar);
            nombre = itemView.findViewById(R.id.nombreAutoresModificar);
            profession = itemView.findViewById(R.id.professionAutoresModificar);
        }

        public void bindAutores(Autor autor){
            id.setText(String.valueOf(autor.getId()));
            muerte.setText(autor.getMuerte());
            nacimiento.setText(String.valueOf(autor.getNacimiento()));
            nombre.setText(autor.getNombre());
            profession.setText(autor.getProfesion());
        }
    }
}
