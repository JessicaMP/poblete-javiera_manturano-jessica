package model;

public class Odontologo {

    private Interger id;
    private String nombre;
    private String apellido;
    private Integer matricula;

    public Odontologo(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public Interger getId() {
        return id;
    }

    public void setId(Interger id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Interger getMatricula() {
        return matricula;
    }

    public void setMatricula(Interger matricula) {
        this.matricula = matricula;
    }
}