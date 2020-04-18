package Api;

/**
 *
 * @author cesar
 */
import Entidades.*;
import Data.DataBaseManager;
import java.util.List;

public class Service {

    DataBaseManager Controller = new DataBaseManager();

    public List<Usuario> getUsers() {
        Controller.OpenDB();
        List<Usuario> Usuarios = Controller.getUsers();
        Controller.CloseDB();
        return Usuarios;
    }

    public Usuario getUsuario(String UserName) {
        Controller.OpenDB();
        Usuario User = Controller.getUsuario(UserName);
        Controller.CloseDB();
        return User;
    }

    public String post_U(Usuario UserName) {
        Controller.OpenDB();
        Usuario Query = Controller.getUsuario(UserName.getUser());
        Controller.CloseDB();

        if (Query == null) {
            Controller.OpenDB();
            Controller.AddU(UserName);
            Controller.CloseDB();
            return "Usuario de nombre " + UserName.getUser() + " ha sido creado!";
        } else {
            return "Usuario existente";
        }
    }

    public String Put_U(Usuario UserName, String old_passw) {
        Controller.OpenDB();
        Usuario Query = Controller.getUsuario(UserName.getUser());
        Controller.CloseDB();

        if (Query != null) {
            Controller.OpenDB();
            Controller.UpdateU(UserName, old_passw);
            Controller.CloseDB();
            return "Usuario " + UserName.getUser() + " ha sido Actualizado!";
        } else {
            return "Usuario inexistente";
        }
    }

    public String Delete_U(String UserName) {
        Controller.OpenDB();
        Usuario TheUser = Controller.getUsuario(UserName);
        Controller.CloseDB();

        if (TheUser != null) {
            Controller.OpenDB();
            Controller.DeleteU(UserName);
            Controller.CloseDB();
            return "Usuario de nombre " + UserName + " ha sido Eliminado!";
        } else {
            return "Usuario no Encontrado";
        }
    }

    public List<Cita> getCitas() {
        Controller.OpenDB();
        List<Cita> dates = Controller.getCitas();
        Controller.CloseDB();
        return dates;
    }

    public Cita getCita(int NumbCita) {
        Controller.OpenDB();
        Cita TheDate = Controller.getCita(NumbCita);
        Controller.CloseDB();
        return TheDate;
    }

    public String post_C(Cita cita) {
        Controller.OpenDB();
        Cita Query = Controller.getCita(0);
        List<Cita> citas = Controller.getCitas();
        Controller.CloseDB();

        if (Query == null) {
            for(Cita c: citas){
                if(c.getFecha().equals(cita.getFecha()) && c.getHoraCita().equals(cita.getHoraCita())){
                    if(c.getCarnet() == cita.getCarnet()){
                        return "Doctor ya ocupado";
                    }else if(c.getId_Paciente() == cita.getId_Paciente()){
                        return "Registro repetido";
                    }else if(cita.getCarnet() == cita.getId_Paciente()){
                        return "El doctor no se puede atender a sí mismo";
                    }
                }
                
            }
            Controller.OpenDB();
            Controller.AddC(cita);
            Controller.CloseDB();
            return "Numero de Cita " + cita.getNumeroCita() + " ha sido creado!";
        } else {
            return "Numero de Cita existente";
        }
    }

    public String Put_C(Cita cita) {
        Controller.OpenDB();
        Cita Query = Controller.getCita(cita.getNumeroCita());
        List<Cita> citas = Controller.getCitas();
        Controller.CloseDB();

        if (Query != null) {
            for(Cita c: citas){
                if(c.getFecha().equals(cita.getFecha()) && c.getHoraCita().equals(cita.getHoraCita())){
                    if(c.getCarnet() == cita.getCarnet()){
                        return "Doctor ya ocupado";
                    }else if(c.getId_Paciente() == cita.getId_Paciente()){
                        return "Registro repetido";
                    }else if(c.getCarnet() == cita.getId_Paciente()){
                        return "El doctor no se puede atender a sí mismo";
                    }
                }
            }
            Controller.OpenDB();
            Controller.UpdateC(cita);
            Controller.CloseDB();
            return "Numero de Cita " + cita.getNumeroCita() + " ha sido Actualizado!";
        } else {
            return "Numero de Cita existente";
        }
    }

    public String Delete_C(int NumbCita) {
        Controller.OpenDB();
        Cita UnaCita = Controller.getCita(NumbCita);
        Controller.CloseDB();

        if (UnaCita != null) {
            Controller.OpenDB();
            Controller.DeleteC(NumbCita);
            Controller.CloseDB();
            return "Número de cita " + NumbCita + " ha sido Eliminado!";
        } else {
            return "Número de cita no Encontrado";
        }
    }


    public List<Ebais> getEbais() {
        Controller.OpenDB();
        List<Ebais> LosEbais = Controller.getEbais();
        Controller.CloseDB();
        return LosEbais;
    }

    public Ebais get_elEbais(int NumbTelefono) {
        Controller.OpenDB();
        Ebais elEbais = Controller.get_unEbais(NumbTelefono);
        Controller.CloseDB();
        return elEbais;
    }

    public String post_E(Ebais TheEbais) {
        Controller.OpenDB();
        Ebais Query = Controller.get_unEbais(TheEbais.getTelefono());
        Controller.CloseDB();

        if (Query == null) {
            Controller.OpenDB();
            Controller.AddE(TheEbais);
            Controller.CloseDB();
            return "Ebais con #  " + TheEbais.getTelefono() + " ha sido creado!";
        } else {
            return "Ebais existente";
        }
    }

    public String Put_E(Ebais TheEbais) {
        Controller.OpenDB();
        Ebais Query = Controller.get_unEbais(TheEbais.getTelefono());
        Controller.CloseDB();

        if (Query != null) {
            Controller.OpenDB();
            Controller.UpdateE(TheEbais);
            Controller.CloseDB();
            return "Ebais con #  " + TheEbais.getTelefono() + " ha sido Actualizado!";
        } else {
            return "Ebais existente";
        }
    }

    public String Delete_E(int numbTel) {
        Controller.OpenDB();
        Ebais el_Ebais = Controller.get_unEbais(numbTel);
        Controller.CloseDB();

        if (el_Ebais != null) {
            Controller.OpenDB();
            Controller.DeleteE(numbTel);
            Controller.CloseDB();
            return "Ebais con # " + numbTel + " ha sido Eliminado!";
        } else {
            return "Telefono no Encontrado";
        }
    }

    public List<Medico> getDoctores() {
        Controller.OpenDB();
        List<Medico> Medicos = Controller.getDoctores();
        Controller.CloseDB();
        return Medicos;
    }

    public Medico getDoctor(int Id) {
        Controller.OpenDB();
        Medico Doc = Controller.getDoctor(Id);
        Controller.CloseDB();
        return Doc;
    }

    public String post_D(Medico Medicos) {
        Controller.OpenDB();
        Medico Query = Controller.getDoctor(Medicos.getCarnet());
        Controller.CloseDB();

        if (Query == null) {
            Controller.OpenDB();
            Controller.AddM(Medicos);
            Controller.CloseDB();
            return "Doctor con Carnet " + Medicos.getCarnet() + " ha sido creado!";
        } else {
            return "Carnet existente";
        }
    }

    public String Put_D(Medico Medicos) {
        Controller.OpenDB();
        Medico Query = Controller.getDoctor(Medicos.getCarnet());
        Controller.CloseDB();

        if (Query != null) {
            Controller.OpenDB();
            Controller.UpdateM(Medicos);
            Controller.CloseDB();
            return "Numero de Carnet : " + Medicos.getCarnet() + " ha sido Actualizado!";
        } else {
            return "Carnet existente";
        }
    }

    public String Delete_D(int Carnet) {
        Controller.OpenDB();
        Medico TheDoctor = Controller.getDoctor(Carnet);
        Controller.CloseDB();

        if (TheDoctor != null) {
            Controller.OpenDB();
            Controller.DeleteM(Carnet);
            Controller.CloseDB();
            return "Numero de Carnet : " + Carnet + " ha sido Eliminado!";
        } else {
            return "Carnet no Encontrado";
        }
    }
    
    public List<Paciente> getPacientes() {
        Controller.OpenDB();
        List<Paciente> pacientes = Controller.getPacientes();
        Controller.CloseDB();
        return pacientes;
    }

    public Paciente getPaciente(int Id) {
        Controller.OpenDB();
        Paciente paciente = Controller.getPaciente(Id);
        Controller.CloseDB();
        return paciente;
    }

    public String post_P(Paciente paciente) {
        Controller.OpenDB();
        Paciente query = Controller.getPaciente(paciente.getId_paciente());
        Controller.CloseDB();

        if (query == null) {
            Controller.OpenDB();
            Controller.AddP(paciente);
            Controller.CloseDB();
            return "Paciente con id " + paciente.getId_paciente() + " ha sido creado!";
        } else {
            return "Id existente";
        }
    }

    public String Put_P(Paciente paciente) {
        Controller.OpenDB();
        Paciente query = Controller.getPaciente(paciente.getId_paciente());
        Controller.CloseDB();

        if (query != null) {
            Controller.OpenDB();
            Controller.UpdateP(paciente);
            Controller.CloseDB();
            return "Id de paciente : " + paciente.getId_paciente() + " ha sido Actualizado!";
        } else {
            return "Id inexistente";
        }
    }

    public String Delete_P(int id_paciente) {
        Controller.OpenDB();
        Paciente paciente = Controller.getPaciente(id_paciente);
        Controller.CloseDB();

        if (paciente != null) {
            Controller.OpenDB();
            Controller.DeleteP(id_paciente);
            Controller.CloseDB();
            return "Id de paciente : " + id_paciente + " ha sido Eliminado!";
        } else {
            return "Id no Encontrado";
        }
    }

}