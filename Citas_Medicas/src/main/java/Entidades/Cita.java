package Entidades;

/**
 *
 * @author Grupo 3
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cita {
    private static int numC = 0;
    private int numeroCita;
    private String fecha;
    private String horaCita;
    private int id_Paciente;
    private int carnet;
    private String areaAsignada;
    private int ebais ;
    
    
    public Cita() {
        
    }

    public Cita(String fecha, String horaCita, int id_Paciente, int carnet, String areaAsignada, int ebais) {
        this.numeroCita = ++numC;
        this.fecha = fecha;
        this.horaCita = horaCita;
        this.id_Paciente = id_Paciente;
        this.carnet = carnet;
        this.areaAsignada = areaAsignada;
        this.ebais = ebais;

    }
    
 /**
 * Método para obtener fecha del ordenador
 * @return 
 */
    public String Fecha() {
        Date DateFormat = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(DateFormat);
    }
    
    
    public int getNumeroCita() {
        return this.numeroCita;
    }

    public void setNumeroCita(int numeroCita) {
        this.numeroCita = numeroCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public int getId_Paciente() {
        return id_Paciente;
    }

    public void setId_Paciente(int id_Paciente) {
        this.id_Paciente = id_Paciente;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getAreaAsignada() {
        return areaAsignada;
    }

    public void setAreaAsignada(String areaAsignada) {
        this.areaAsignada = areaAsignada;
    }

    public int getEbais() {
        return ebais;
    }

    public void setEbais(int ebais) {
        this.ebais = ebais;
    }

}
