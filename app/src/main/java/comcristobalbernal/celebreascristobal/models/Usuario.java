package comcristobalbernal.celebreascristobal.models;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String contrasenya;
    private Byte admin;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasenya;
    }

    public Byte isAdmin() {
        return admin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasenya = contrasena;
    }

    public void setAdmin(Byte admin) {
        this.admin = admin;
    }

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasenya = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && admin == usuario.admin && Objects.equals(nombre, usuario.nombre) && Objects.equals(contrasenya, usuario.contrasenya);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasenya + '\'' +
                ", tipo=" + admin +
                '}';
    }
}
