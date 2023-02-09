package comcristobalbernal.celebreascristobal.models;


import androidx.annotation.NonNull;

public class Frase {
    private static final String TAG = Class.class.getSimpleName();

    private int id;
    private String texto;
    private String fechaprogramada;
    private int idAutor;
    private int idCategoria;

    public Frase() {
    }

    public Frase(String texto, String fechaProgramada, int idAutor, int idCategoria) {
        this.texto = texto;
        this.fechaprogramada = fechaProgramada;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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
                ", autor=" + idAutor +
                ", categoria=" + idCategoria +
                '}';
    }
}
