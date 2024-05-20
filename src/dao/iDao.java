package dao;

import model.Odontologo;

import java.util.List;

public interface iDao<T> {
    //todo el crud
    T guardar(T t);
    T buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(T t);

    List<T> buscarTodos();

    List<Odontologo> listarOdontologos();
}
