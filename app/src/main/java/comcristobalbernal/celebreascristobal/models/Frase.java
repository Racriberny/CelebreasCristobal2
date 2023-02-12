package comcristobalbernal.celebreascristobal.models;


import androidx.annotation.NonNull;

public class Frase {
    private static final String TAG = Class.class.getSimpleName();

    private int id;
    private String texto;
    private String fechaprogramada;
    private int autorId;
    private int categoriaId;

    public Frase() {
    }

    public Frase(String texto, String fechaProgramada, int autor_id, int categoria_id) {
        this.texto = texto;
        this.fechaprogramada = fechaProgramada;
        this.autorId = autor_id;
        this.categoriaId = categoria_id;
    }

    public Frase(int id,String texto){
        this.id = id;
        this.texto = texto;
    }
    public int getId() {
        return id;
    }

    /**
     * No permitimos modificar el id desde fuera ya que es de tipo autoincrement
     */
    private void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaprogramada() {
        return fechaprogramada;
    }

/*
    public void setFechaProgramada(String fecha) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(fecha);
            if(!fecha.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            Log.e(TAG, ex.getMessage());
        }
        if(date == null) {
            Log.e(TAG, "Formato de fecha incorrecto. Patrón [yyyy-MM-dd]. Fecha = "+fecha);
            this.fechaProgramada = null;
        } else {
            this.fechaProgramada = fecha;
        }
    }
     */

    public int getCategoriaId() {
        return categoriaId;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Frase frase = (Frase) o;
        return id == frase.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Frase{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fechaProgramada='" + fechaprogramada + '\'' +
                ", autor=" + autorId +
                ", categoria=" + categoriaId +
                '}';
    }
}
