package Data;

/**
 *
 * @author Grupo 3
 */
import Entidades.*;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    private final String Host = "localhost:3306"; //Puede cambiar
    private final String DATABASE = "Vida_Online";
    private final String ULR = "jdbc:mysql://" + Host + "/" + DATABASE + "?autoReconnect=true&useSSL=false";
    private final String USER = "root"; //Puede cambiar
    private final String PASS = "GitKei98*"; //Cambia
    private final String DRIVER = "com.mysql.jdbc.Driver";
    Connection Connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void OpenDB() {

        try {

            Class.forName(DRIVER);
            Connection = (Connection) DriverManager.getConnection(ULR, USER, PASS);
            statement = (Statement) Connection.createStatement();

        } catch (Exception ex) {

            System.err.println("Error para abrir la Base de Datos " + ex);

        }

    }

    public void AddU(Usuario Users) {
        try {

            String sql = "INSERT INTO Usuario VALUES (?,?)";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setString(1, Users.getUser());
            preparedStatement.setString(2, Users.getPassword());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void UpdateU(Usuario Users, String old_passw) {
        try {
            Usuario old = getUsuario(Users.getUser());
            if (old.changePassword(old_passw)) {
                String sql = "UPDATE Usuario SET password_ = ? WHERE username = ?";

                PreparedStatement preparedStatement;

                preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);
                
                preparedStatement.setString(1, Users.getPassword());
                preparedStatement.setString(2, Users.getUser());
                
                preparedStatement.executeUpdate();

                preparedStatement.close();
            }else{
                System.out.println("Contraseña incorrecta");
            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void DeleteU(String UserName) {

        try {

            String sql = "DELETE FROM Usuario WHERE username = '" + UserName + "'";
            statement.execute(sql);

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

    }

    public List<Usuario> getUsers() {
        List Users = new ArrayList();

        try {

            String sql = "SELECT * FROM Usuario";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Usuario usuarios = new Usuario();

                usuarios.setUser(resultSet.getString("username"));
                usuarios.setPassword(resultSet.getString("password_"));
                Users.add(usuarios);

            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return Users;
    }

    public Usuario getUsuario(String UserName) {
        Usuario usuarios = null;
        try {
            /*
            Cuando el identificador es un String MySQL lo reconoce como una columna
            por eso se debe colocar entre comillas simples
            */
            String sql = "SELECT * FROM Usuario WHERE username = '" + UserName + "'";
            System.out.println(sql);
            resultSet = statement.executeQuery(sql);
            if (resultSet.first()) {

                usuarios = new Usuario();

                usuarios.setUser(resultSet.getString(1));
                usuarios.setPassword(resultSet.getString(2));

                System.out.println(usuarios);
            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return usuarios;
    }

    public void AddC(Cita UnaCita) {
        try {

            String sql = "INSERT INTO Cita VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, UnaCita.getFecha());
            preparedStatement.setString(3, UnaCita.getHoraCita());
            preparedStatement.setInt(4, UnaCita.getId_Paciente());
            preparedStatement.setInt(5, UnaCita.getCarnet());
            preparedStatement.setString(6, UnaCita.getAreaAsignada());
            preparedStatement.setInt(7, UnaCita.getEbais());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void UpdateC(Cita LaCita) {
        try {

            String sql = "UPDATE Cita SET fecha = ?, horaCita = ?, id_Paciente = ?,"
                    + "carnet = ?, areaAsignada = ?, ebais = ? WHERE numeroCita = ?";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setString(1, LaCita.getFecha());
            preparedStatement.setString(2, LaCita.getHoraCita());
            preparedStatement.setInt(3, LaCita.getId_Paciente());
            preparedStatement.setInt(4, LaCita.getCarnet());
            preparedStatement.setString(5, LaCita.getAreaAsignada());
            preparedStatement.setInt(6, LaCita.getEbais());
            preparedStatement.setInt(7, LaCita.getNumeroCita());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void DeleteC(int N_LasCitas) {

        try {

            String sql = "DELETE FROM Cita WHERE NumeroCita = " + N_LasCitas;
            statement.execute(sql);

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

    }

    public List<Cita> getCitas() {
        List Citas_K = new ArrayList();

        try {

            String sql = "SELECT * FROM Cita";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Cita unasCitas = new Cita();

                unasCitas.setNumeroCita(resultSet.getInt("numeroCita"));
                unasCitas.setFecha(resultSet.getString("fecha"));
                unasCitas.setHoraCita(resultSet.getString("horaCita"));
                unasCitas.setId_Paciente(resultSet.getInt("id_Paciente"));
                unasCitas.setCarnet(resultSet.getInt("carnet"));
                unasCitas.setAreaAsignada(resultSet.getString("areaAsignada"));
                unasCitas.setEbais(resultSet.getInt("ebais"));

                Citas_K.add(unasCitas);

            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return Citas_K;
    }

    public Cita getCita(int NumbCita) {
        Cita LaCita = null;

        try {
            String sql = "SELECT * FROM Cita WHERE NumeroCita = " + NumbCita;
            resultSet = statement.executeQuery(sql);

            if (resultSet.first()) {

                LaCita = new Cita();

                //  LaCita.setNUMCITA(resultSet.getInt(1));
                LaCita.setNumeroCita(resultSet.getInt("numeroCita"));
                LaCita.setFecha(resultSet.getString("fecha"));
                LaCita.setHoraCita(resultSet.getString("horaCita"));
                LaCita.setId_Paciente(resultSet.getInt("id_Paciente"));
                LaCita.setCarnet(resultSet.getInt("carnet"));
                LaCita.setAreaAsignada(resultSet.getString("areaAsignada"));
                LaCita.setEbais(resultSet.getInt("ebais"));

                System.out.println(LaCita);
            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return LaCita;
    }

    public void AddE(Ebais El_ebais) {
        try {

            String sql = "INSERT INTO Ebais VALUES (?,?,?,?)";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setInt(1, El_ebais.getTelefono());
            preparedStatement.setString(2, El_ebais.getProvincia());
            preparedStatement.setString(3, El_ebais.getCanton());
            preparedStatement.setString(4, El_ebais.getDistrito());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void UpdateE(Ebais El_ebais) {
        try {

            String sql = "UPDATE Ebais SET Provicia = ?, Canton = ?, Distrito = ? WHERE Telefono = ?";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setString(1, El_ebais.getProvincia());
            preparedStatement.setString(2, El_ebais.getCanton());
            preparedStatement.setString(3, El_ebais.getDistrito());
            preparedStatement.setInt(4, El_ebais.getTelefono());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void DeleteE(int Phone) {

        try {

            String sql = "DELETE FROM Ebais WHERE Telefono = " + Phone;
            statement.execute(sql);

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos");

        }

    }

    public List<Ebais> getEbais() {
        List Ebais_k = new ArrayList();

        try {

            String sql = "SELECT * FROM Ebais";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Ebais LosEbais = new Ebais();

                LosEbais.setTelefono(resultSet.getInt("telefono"));
                LosEbais.setProvincia(resultSet.getString("provincia"));
                LosEbais.setCanton(resultSet.getString("canton"));
                LosEbais.setDistrito(resultSet.getString("distrito"));

                Ebais_k.add(LosEbais);

            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return Ebais_k;
    }

    public Ebais get_unEbais(int NumTel) {
        Ebais unEbais = null;

        try {
            String sql = "SELECT * FROM Ebais WHERE Telefono = " + NumTel;
            resultSet = statement.executeQuery(sql);

            if (resultSet.first()) {

                unEbais = new Ebais();

                unEbais.setTelefono(resultSet.getInt(1));
                unEbais.setProvincia(resultSet.getString(2));
                unEbais.setCanton(resultSet.getString(3));
                unEbais.setDistrito(resultSet.getString(4));

                System.out.println(unEbais);
            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return unEbais;
    }

    public void AddP(Paciente paciente) {
        try {

            String sql = "INSERT INTO Paciente VALUES (?,?,?)";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setInt(1, paciente.getId_paciente());
            preparedStatement.setString(2, paciente.getNombre());
            preparedStatement.setString(3, paciente.getDireccion());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void UpdateP(Paciente paciente) {
        try {

            String sql = "UPDATE Paciente SET nombre = ?, direccion = ? WHERE id_paciente = ?";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getDireccion());
            preparedStatement.setInt(3, paciente.getId_paciente());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void DeleteP(int paciente_id) {

        try {

            String sql = "DELETE FROM Paciente WHERE id_paciente = " + paciente_id;
            statement.execute(sql);

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

    }

    public List<Paciente> getPacientes() {
        List pacientes = new ArrayList();

        try {

            String sql = "SELECT * FROM Paciente";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Paciente info_paci = new Paciente();

                info_paci.setId_paciente(resultSet.getInt("id_paciente"));
                info_paci.setNombre(resultSet.getString("nombre"));
                info_paci.setDireccion(resultSet.getString("direccion"));

                pacientes.add(info_paci);

            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return pacientes;
    }

    public Paciente getPaciente(int id_paciente) {
        Paciente info_paci = null;

        try {
            String sql = "SELECT * FROM Paciente WHERE id_paciente = " + id_paciente;
            resultSet = statement.executeQuery(sql);

            if (resultSet.first()) {

                info_paci = new Paciente();

                info_paci.setId_paciente(resultSet.getInt("id_paciente"));
                info_paci.setNombre(resultSet.getString("nombre"));
                info_paci.setDireccion(resultSet.getString("direccion"));

                System.out.println(id_paciente);
            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return info_paci;
    }

    public void AddM(Medico Docs) {
        try {

            String sql = "INSERT INTO Medico VALUES (?,?,?,?)";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setInt(1, Docs.getCarnet());
            preparedStatement.setString(2, Docs.getName());
            preparedStatement.setString(3, Docs.getLastName());
            preparedStatement.setString(4, Docs.getProfessionalArea());

            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void UpdateM(Medico Docs) {
        try {

            String sql = "UPDATE Medico SET Name = ?, LastName = ? , ProfessionalArea = ?  WHERE Carnet = ?";

            PreparedStatement preparedStatement;

            preparedStatement = (PreparedStatement) Connection.prepareStatement(sql);

            preparedStatement.setString(1, Docs.getName());
            preparedStatement.setString(2, Docs.getLastName());
            preparedStatement.setString(3, Docs.getProfessionalArea());
            preparedStatement.setInt(4, Docs.getCarnet());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }
    }

    public void DeleteM(int IdClinic) {

        try {

            String sql = "DELETE FROM Medico WHERE Carnet = " + IdClinic;
            statement.execute(sql);

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

    }

    public List<Medico> getDoctores() {
        List Docts = new ArrayList();

        try {

            String sql = "SELECT * FROM Medico";
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Medico LosMedicos = new Medico();

                LosMedicos.setCarnet(resultSet.getInt("carnet"));
                LosMedicos.setName(resultSet.getString("nombre"));
                LosMedicos.setLastName(resultSet.getString("apellido"));
                LosMedicos.setProfessionalArea(resultSet.getString("areaM"));
                Docts.add(LosMedicos);

            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return Docts;
    }

    public Medico getDoctor(int Carnet) {
        Medico LosMedicos = null;

        try {
            String sql = "SELECT * FROM Medico WHERE carnet = " + Carnet;
            resultSet = statement.executeQuery(sql);

            if (resultSet.first()) {

                LosMedicos = new Medico();

                LosMedicos.setCarnet(resultSet.getInt(1));
                LosMedicos.setName(resultSet.getString(2));
                LosMedicos.setLastName(resultSet.getString(3));
                LosMedicos.setProfessionalArea(resultSet.getString(4));

                System.out.println(LosMedicos);
            }

        } catch (SQLException ex) {

            System.err.println("Hubo un error al acceder a la Base de Datos" + ex);

        }

        return LosMedicos;
    }

    public void CloseDB() {

        try {

            statement.close();
            Connection.close();

        } catch (SQLException ex) {

            System.err.println("Hubo un error al cerrar la Base de Datos" + ex);

        }

    }

}
