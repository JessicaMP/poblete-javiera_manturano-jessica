package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Odontologo;

import java.util.List;

public interface iDao<T> {
    //todo el crud
    T guardar(T t);
    T buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(T t);

    List<T> buscarTodos();
    T buscarPorString(String string);

    List<Odontologo> listarOdontologos();
}
