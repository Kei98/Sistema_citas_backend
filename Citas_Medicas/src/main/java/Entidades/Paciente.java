package Entidades;

/**
 *
 * @author kei98
 */
public class Paciente {
    private int id_paciente;
    private String nombre;
    private String direccion;

    public Paciente() {
    }

    public Paciente(int id_paciente, String nombre, String direccion) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
