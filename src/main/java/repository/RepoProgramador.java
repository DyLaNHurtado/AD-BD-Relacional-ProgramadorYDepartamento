package repository;

import database.DataBaseController;
import model.Programador;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoProgramador {


    public Optional<Programador> delete(Programador programador) {
        System.out.println("Eliminando programador con id: " + programador.getId());
        String query = "DELETE FROM programador WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        try {
            db.open();
            int res = db.delete(query, programador.getId());
            db.close();
            if (res > 0)
                return Optional.of(programador);
        } catch (SQLException e) {
            System.err.println("Error al eliminar programador" + e.getMessage());
        }
        return Optional.of(programador);
    }

    public Optional<Programador> update(Programador programador) {
        System.out.println("Actualizando programador con id: " + programador.getId());
        String query = "UPDATE programador SET id= ?, nombre = ?, aniosExp = ?, salario = ?, lenguajes = ?, departamento= ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        try {
            db.open();
            int res = db.update(query, programador.getId(), programador.getNombre(),
                    programador.getAniosExp(), programador.getSalario(),
                    String.join(";",programador.getLenguajes()),
                    programador.getDepartamento(), programador.getId());
            db.close();
            if (res > 0)
                return Optional.of(programador);
        } catch (SQLException e) {
            System.err.println("Error al actualizar programador" + e.getMessage());
        }
        return Optional.of(programador);

    }

    public Optional<Programador> insert(Programador programador) {
        System.out.println("Insertando programador");
        String query = "INSERT INTO programador VALUES (null, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        try {
            db.open();
            ResultSet res = db.insert(query ,programador.getNombre(), programador.getAniosExp(),
                    programador.getSalario(), String.join(";",programador.getLenguajes()),programador.getDepartamento())
                    .orElseThrow(() -> new SQLException("Error insertar Usuario"));
            // Para obtener su ID
            if (res.first()) {
                programador.setId(res.getLong(1));
                // una vez insertado comprobamos que esta correcto para devolverlo
                db.close();
                return Optional.of(programador);
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar programador" + e.getMessage());
        }
        return Optional.of(programador);
    }

    public Optional<Programador> selectById(long id) {
        System.out.println("Obteniendo programador con id: " + id);
        String query = "SELECT * FROM programador WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        Programador programador = null;
        try {
            db.open();
            ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar usuario con ID " + id));
            if (result.first()) {
                programador = new Programador(
                        result.getLong("id"),
                        result.getString("nombre"),
                        result.getInt("aniosExp"),
                        result.getDouble("salario"),
                        List.of(result.getString("lenguajes").split(";")),
                        result.getString("departamento")
                );
                db.close();
                return Optional.of(programador);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario con id: " + id + " - " + e.getMessage());
        }
        return Optional.ofNullable(programador);
    }

    public Optional<List<Programador>> getAll() {
        System.out.println("Obteniendo todos los usuarios");
        String query = "SELECT * FROM programador";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Programador> list = null;
        try {
            db.open();
            ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar registros de Usuarios"));
            list = new ArrayList<Programador>();
            while (result.next()) {
                list.add(
                        new Programador(
                                result.getLong("id"),
                                result.getString("nombre"),
                                result.getInt("aniosExp"),
                                result.getDouble("salario"),
                                List.of(result.getString("lenguajes").split(";")),
                                result.getString("departamento")
                        )
                );
            }
            db.close();
            return Optional.of(list);
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los usuarios: " + e.getMessage());
        }
        return Optional.ofNullable(list);
    }


    public static void initData() {
        System.out.println("Iniciamos los datos");
        DataBaseController controller = DataBaseController.getInstance();
        String sqlFile = System.getProperty("user.dir") +File.separator +"src"+File.separator+"main"+File.separator+"java"+ File.separator + "sql" + File.separator + "data.sql";
        System.out.println("\n***********************\n" +
                " SCRIPT SQL\n" +
                "***********************\n");
        System.out.println(sqlFile);
        try {
            controller.open();
            controller.initData(sqlFile);
            controller.close();
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el fichero de datos iniciales: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void checkServer() {
        System.out.println("Comprobamos la conexión al Servidor BD");
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello world'");
            if (rs.isPresent()) {
                rs.get().first();
                controller.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }
}
