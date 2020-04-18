package Entidades;

/**
 *
 * @author Grupo 3
 */
public class Ebais {

    public Ebais() {
    }

    private int Telefono;
    private String Provincia;
    private String Canton;
    private String Distrito;

    public Ebais(int Telefono, String Provincia, String Canton, String Distrito) {
        this.Telefono = Telefono;
        this.Provincia = Provincia;
        this.Canton = Canton;
        this.Distrito = Distrito;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }

    public String getCanton() {
        return Canton;
    }

    public void setCanton(String Canton) {
        this.Canton = Canton;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String Distrito) {
        this.Distrito = Distrito;
    }

}
