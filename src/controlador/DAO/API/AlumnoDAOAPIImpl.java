package controlador.DAO.API;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controlador.DAO.AlumnoDAO;
import modelo.Alumno;
import okhttp3.*;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOAPIImpl implements AlumnoDAO {


    @Override
    public void crearTablasAlum() {

    }

    @Override
    public void insert(Alumno alum) {
        String url = "http://localhost:9000/api/agregarAlumno";

        //Hibernate.initialize(alum.getCurso().getListaAlumnos());

        RequestBody body = RequestBody.create(new Gson().toJson(alum), MediaType.parse("application/json"));
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
    public void update(Alumno alum) {

    }

    @Override
    public void delete(String dni) {

    }

    @Override
    public Alumno readUno(String idAlum) {
        return null;
    }

    @Override
    public List<Alumno> listaAlumDAO() {
        String url = "http://localhost:9000/api/alumnos";
        OkHttpClient cliente = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        List<Alumno> listaAlum = new ArrayList<>();

        try(Response response = cliente.newCall(request).execute()){
            if(response.isSuccessful()){
                Gson gson= new Gson();
                listaAlum = gson.fromJson(response.body().string(), new TypeToken<List<Alumno>>() {}.getType());

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return listaAlum;
    }

    @Override
    public List<Alumno> ordenarAlumAlfDAO() {
        return null;
    }

    @Override
    public void insertNota(String dni, double nota) {

    }

    @Override
    public void insertNota(Alumno a, double nota) {

    }

    @Override
    public List<Alumno> listaAlumAproDAO() {
        return null;
    }

    @Override
    public List<Alumno> listaAlumSusDAO() {
        return null;
    }

    @Override
    public List<Alumno> coincidenciaExactaNombre(String name) {
        return null;
    }

    @Override
    public List<Alumno> contienePalabraClaveNombre(String name) {
        return null;
    }

    @Override
    public List<Alumno> empiezaPorNombre(String name) {
        return null;
    }

    @Override
    public List<Alumno> terminaEnNombre(String name) {
        return null;
    }

    @Override
    public List<Alumno> coincidenciaExactaDni(String dni) {
        return null;
    }

    @Override
    public List<Alumno> contienePalabraClaveDni(String dni) {
        return null;
    }

    @Override
    public List<Alumno> empiezaPorDni(String dni) {
        return null;
    }

    @Override
    public List<Alumno> terminaEnDni(String dni) {
        return null;
    }

    @Override
    public List<Alumno> notaMediaAlum(Double mediia) {
        return null;
    }

    @Override
    public List<Alumno> profesorTutorAlum(String nombreTutor) {
        return null;
    }

    @Override
    public List<Alumno> buscarCursoAlum(List<String> listaCur) {
        return null;
    }
}
