package Api;

/**
 *
 * @author cesar
 */
import static Api.JsonUtil.json;
import Entidades.*;
import static spark.Spark.*;

public class Main {

    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public static void main(String[] args) {
        port(8080);
        Service service = new Service();

        post("/usuario", (request, response) -> {
            String userName = request.queryParams("username");
            String password = request.queryParams("password_");

            try {
                Usuario elUsuario = new Usuario(userName, password);
                return service.post_U(elUsuario);
            } catch (Exception e) {
                return "Nombre no válido " + e;
            }
        });

        get("/usuario", (request, response) -> {
            return service.getUsers();
        }, json());

        get("/usuario/:username", (request, response) -> {
            String Id = request.params(":username");

            try {
                return service.getUsuario(Id);
            } catch (Exception e) {
                return "Nombre no válido " +e;
            }
        }, json());

        put("/usuario/:username", (request, response) -> {
            String NombreUsuario = request.params(":username");
            String PSswrd = request.queryParams("password");
            String passw = request.queryParams("old_passw");
            
            try {
                Usuario elUsuario = new Usuario(NombreUsuario, PSswrd);
                return service.Put_U(elUsuario, passw);
            } catch (Exception e) {
                return "Usuario no válido " + e;
            }
        });

        delete("/usuario/:username", (request, response) -> {
            String NombreU = request.params(":username");

            try {
                return service.Delete_U(NombreU);
            } catch (Exception e) {
                return "Nombre de usuario no válido " + e;
            }
        });

        //Espacio para las citas  
        post("/cita", (request, response) -> {
            String fecha = request.queryParams("fecha");
            String horaCita = request.queryParams("hora_cita");
            String id_Paciente = request.queryParams("id_paciente");
            String carnet = request.queryParams("carnet");
            String areaAsignada = request.queryParams("area");
            String ebais = request.queryParams("ebais");

            try {
                Cita cita = new Cita(fecha, horaCita, Integer.parseInt(id_Paciente), Integer.parseInt(carnet), 
                        areaAsignada, Integer.parseInt(ebais));
                return service.post_C(cita);
            } catch (NumberFormatException e) {
                return "Información de la cita no válida " + e;
            }
        });

        get("/cita", (request, response) -> {
            return service.getCitas();
        }, json());

        get("/cita/:numero_cita", (request, response) -> {
            String numCita = request.params(":numero_cita");

            try {
                return service.getCita(Integer.parseInt(numCita));
            } catch (Exception e) {
                return "Número cita no válido";
            }
        }, json());

        put("/cita/:numero_cita", (request, response) -> {
            String numeroCita = request.params(":numero_cita");
            String fecha = request.queryParams("fecha");
            String horaCita = request.queryParams("hora_cita");
            String id_Paciente = request.queryParams("id_paciente");
            String carnet = request.queryParams("carnet");
            String areaAsignada = request.queryParams("area");
            String ebais = request.queryParams("ebais");

            try {
                Cita cita = new Cita();
                cita.setNumeroCita(Integer.parseInt(numeroCita));
                cita.setFecha(fecha);
                cita.setHoraCita(horaCita);
                cita.setId_Paciente(Integer.parseInt(id_Paciente));
                cita.setCarnet(Integer.parseInt(carnet));
                cita.setAreaAsignada(areaAsignada);
                cita.setEbais(Integer.parseInt(ebais));
                
                return service.Put_C(cita);
            } catch (Exception e) {
                return "Id del paciente, carnet del doctor o ebais no válidos " + e;
            }
        });

        delete("/cita/:numero_cita", (request, response) -> {
            String numCita = request.params(":numero_cita");

            try {
                return service.Delete_C(Integer.parseInt(numCita));
            } catch (Exception e) {
                return "Número de cita inválido " + e;
            }
        });
        //finaliza citas

        post("/ebais", (request, response) -> {
            String NumTelefono = request.queryParams("telefono");
            String Provincia = request.queryParams("provincia");
            String Canton = request.queryParams("canton");
            String Distrito = request.queryParams("distrito");

            try {
                Ebais unEbais = new Ebais(Integer.parseInt(NumTelefono), Provincia, Canton, Distrito);
                return service.post_E(unEbais);
            } catch (Exception e) {
                return "Telefono de Ebais no válido " + e;
            }
        });

        get("/ebais", (request, response) -> {
            return service.getEbais();
        }, json());

        get("/ebais/:telefono", (request, response) -> {
            int Phone = Integer.parseInt(request.params(":telefono"));

            try {
                return service.get_elEbais(Phone);
            } catch (Exception e) {
                return "Telefono no válido " +e;
            }
        }, json());

        put("/ebais/:telefono", (request, response) -> {
            int NumTelefono = Integer.parseInt(request.queryParams("telefono"));
            String Provincia = request.queryParams("provincia");
            String Canton = request.queryParams("canton");
            String Distrito = request.queryParams("distrito");

            try {
                Ebais unEbais = new Ebais(NumTelefono, Provincia, Canton, Distrito);
                return service.Put_E(unEbais);
            } catch (Exception e) {
                return "Telefono inválido " +e;
            }
        });

        delete("/ebais/:telefono", (request, response) -> {
            int NumTelefono = Integer.parseInt(request.queryParams("telefono"));

            try {
                return service.Delete_E(NumTelefono);
            } catch (Exception e) {
                return "Teléfono no válido " +e;
            }
        });

        post("/medico", (request, response) -> {

            int Carnet = Integer.parseInt(request.queryParams("carnet"));
            String Nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellidos");
            String especialidad = request.queryParams("area");

            try {
                Medico TheDoct = new Medico(Carnet, Nombre, apellido, especialidad);
                return service.post_D(TheDoct);
            } catch (Exception e) {
                return "Carnet no válido " +e;
            }
        });

        get("/medico", (request, response) -> {
            return service.getDoctores();
        }, json());

        get("/medico/:carnet", (request, response) -> {
            int Crn = Integer.parseInt(request.params(":carnet"));

            try {
                return service.getDoctor(Crn);
            } catch (Exception e) {
                return "Carnet no válido " +e;
            }
        }, json());

        put("/medico/:carnet", (request, response) -> {
            int Carnet = Integer.parseInt(request.queryParams("carnet"));
            String Nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellidos");
            String Especialidad = request.queryParams("area");

            try {
                Medico Medico = new Medico(Carnet, Nombre, Nombre, apellido);
                return service.Put_D(Medico);
            } catch (Exception e) {
                return "Carnet no válido " +e;
            }
        });

        delete("/medico/:carnet", (request, response) -> {
             int Carnet = Integer.parseInt(request.queryParams("carnet"));

            try {
                return service.Delete_D(Carnet);
            } catch (Exception e) {
               return "Carnet no válido " +e;
            }
        });
        
        post("/paciente", (request, response) -> {

            int id = Integer.parseInt(request.queryParams("id paciente"));
            String nombre = request.queryParams("nombre");
            String direccion = request.queryParams("direccion");

            try {
                Paciente paciente = new Paciente(id, nombre, direccion);
                return service.post_P(paciente);
            } catch (Exception e) {
                return "Id no válido " +e;
            }
        });

        get("/paciente", (request, response) -> {
            return service.getPacientes();
        }, json());

        get("/paciente/:id", (request, response) -> {
            int Crn = Integer.parseInt(request.params(":id"));

            try {
                return service.getPaciente(Crn);
            } catch (Exception e) {
                return "Id no válido " +e;
            }
        }, json());

        put("/paciente/:id", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            String nombre = request.queryParams("nombre");
            String direccion = request.queryParams("direccion");

            try {
                Paciente paciente = new Paciente(id, nombre, direccion);
                return service.Put_P(paciente);
            } catch (Exception e) {
                return "Id no válido " +e;
            }
        });

        delete("/paciente/:id", (request, response) -> {
             int id = Integer.parseInt(request.queryParams("id paciente"));

            try {
                return service.Delete_P(id);
            } catch (Exception e) {
               return "Carnet no válido " +e;
            }
        });

        before((request, response) -> {
            String method = request.requestMethod();
            if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
                String authentication = request.headers("Authentication");
                if (!"Proyecto_Citas".equals(authentication)) {
                    halt(401, "User Unauthorized");
                }
            }
        });

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });
    }
}
