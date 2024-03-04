package controlador.DAO.API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controlador.DAO.CursoDAO;
import modelo.Curso;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOAPIImpl implements CursoDAO {
    @Override
    public void crearTablasCur() {

    }

    @Override
    public void insert(Curso cur) {
        String url = "https://quieromititulo.com:9000/api/curso";

        RequestBody body = RequestBody.create(new Gson().toJson(cur), MediaType.parse("application/json"));
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = cliente.newCall(request);

        try(Response response = call.execute()){
            if(response.isSuccessful())
                System.out.println("agregado");
            else
                System.out.println("no agregado");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Curso cur) {
        String url = "https://quieromititulo.com:9000/api/curso/";

        RequestBody body = RequestBody.create(new Gson().toJson(cur), MediaType.parse("application/json"));
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Call call = cliente.newCall(request);

        try(Response response = call.execute()){
            if(response.isSuccessful())
                System.out.println("update");
            else
                System.out.println("no update");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer idCur) {
        String url = "https://quieromititulo.com:9000/api/curso/"+idCur;

        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        Call call = cliente.newCall(request);

        try(Response response = call.execute()){
            if(response.isSuccessful())
                System.out.println("borrado");
            else
                System.out.println("no borrado");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Curso readUno(Integer idCur) {
        String url = "https://quieromititulo.com:9000/api/curso/"+idCur;
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Curso c=null;

        try(Response response = cliente.newCall(request).execute()){
            if(response.isSuccessful()){
                Gson gson = new Gson();
                c = gson.fromJson(response.body().string(), new TypeToken<Curso>() {}.getType());
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Curso> listaCurDAO() {
        String url = "https://quieromititulo.com:9000/api/cursos";
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        List<Curso> listaCur = new ArrayList<>();

        try(Response response = cliente.newCall(request).execute()){
            if(response.isSuccessful()){
                Gson gson= new Gson();
                listaCur = gson.fromJson(response.body().string(), new TypeToken<List<Curso>>() {}.getType());

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return listaCur;
    }

    @Override
    public List<Curso> ordenarCurAlfDAO() {
        return null;
    }

    @Override
    public List<Curso> coincidenciaExactaId(int idd) {
        return null;
    }

    @Override
    public List<Curso> contienePalabraClaveId(int idd) {
        return null;
    }

    @Override
    public List<Curso> empiezaPorId(int idd) {
        return null;
    }

    @Override
    public List<Curso> terminaEnId(int idd) {
        return null;
    }

    @Override
    public List<Curso> coincidenciaExactaNombre(String name) {
        return null;
    }

    @Override
    public List<Curso> contienePalabraClaveNombre(String name) {
        return null;
    }

    @Override
    public List<Curso> empiezaPorNombre(String name) {
        return null;
    }

    @Override
    public List<Curso> terminaEnNombre(String name) {
        return null;
    }
}
