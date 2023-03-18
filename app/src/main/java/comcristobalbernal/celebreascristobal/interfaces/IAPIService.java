package comcristobalbernal.celebreascristobal.interfaces;
import java.util.Date;
import java.util.List;

import comcristobalbernal.celebreascristobal.models.Autor;
import comcristobalbernal.celebreascristobal.models.Categoria;
import comcristobalbernal.celebreascristobal.models.Frase;
import comcristobalbernal.celebreascristobal.models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface IAPIService {

    //Get devuelve
    @GET("frase/all")
    Call<List<Frase>> getFrases();

    @POST("frase/add")
    Call<Boolean> addFrase(@Body Frase frase);

    @GET("autor/all")
    Call<List<Autor>> getAutor();
    //Post añade

    @POST("autor/add")
    Call<Boolean> addAutor(@Body Autor autor);

    @GET("categoria/all")
    Call<List<Categoria>> getCategoria();

    @POST("categoria/add")
    Call<Boolean> addCategoria(@Body Categoria categoria);

    @GET("usuario/all")
    Call<List<Usuario>> getUsuario();

    @POST("usuario/add")
    Call<Boolean> addUsuario(@Body Usuario usuario);


    @POST("usuario/login")
    Call<Boolean> logUsuario (
            @Body Usuario user
    );

    @PUT("categoria/update")
    Call<Boolean> modificarCategoria(
            @Body Categoria categoria
    );
    @PUT("autor/update")
    Call<Boolean> modificarAutores(
            @Body Autor autor
    );
    @PUT("frase/update")
    Call<Boolean> modificarFrases(
            @Body Frase frase
    );
}
