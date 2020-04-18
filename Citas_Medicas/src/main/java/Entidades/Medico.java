package Entidades;

/**
 *
 * @author Grupo 3
 */
public class Medico {

    public Medico() {
    }

    private int Carnet;
    private String Name;
    private String LastName;
    private String ProfessionalArea;

    public Medico(int Carnet, String Name, String LastName, String AreaProfession) {
        this.Carnet = Carnet;
        this.Name = Name;
        this.LastName = LastName;
        this.ProfessionalArea = AreaProfession;
    }

    public int getCarnet() {
        return Carnet;
    }

    public void setCarnet(int Carnet) {
        this.Carnet = Carnet;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getProfessionalArea() {
        return ProfessionalArea;
    }

    public void setProfessionalArea(String ProfessionalArea) {
        this.ProfessionalArea = ProfessionalArea;
    }

    

}
