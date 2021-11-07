package repository;

import database.DataBaseController;
import model.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoDepartamento {

    public Optional<Departamento> delete(Departamento departamento) {
        System.out.println("Eliminando departamento con id: " + departamento.getId());
        String query = "DELETE FROM departamento WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        try {
            db.open();
            int res = db.delete(query, departamento.getId());
            db.close();
            if (res > 0)
                return Optional.of(departamento);
        } catch (SQLException e) {
            System.err.println("Error al eliminar departamento" + e.getMessage());
        }
        return Optional.of(departamento);
    }

    public Optional<Departamento> update(Departamento departamento) {
        System.out.println("Actualizando departamento con id: " + departamento.getId());
        String query = "UPDATE departamento SET id= ?, nombre = ?, presupuesto = ?, jefe = ?, programadorList = ? WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        try {
            db.open();
            int res = db.update(query, departamento.getId(), departamento.getNombre(),
                    departamento.getPresupuesto(), departamento.getJefe(),
                    departamento.getProgramadorList(), departamento.getId());
            db.close();
            if (res > 0)
                return Optional.of(departamento);
        } catch (SQLException e) {
            System.err.println("Error al actualizar departamento" + e.getMessage());
        }
        return Optional.of(departamento);
    }

    public Optional<Departamento> insert(Departamento departamento) {
        System.out.println("Insertando departamento");
        String query = "INSERT INTO departamento VALUES (null, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        try {
            db.open();
            ResultSet res = db.insert(query, departamento.getNombre(), departamento.getPresupuesto(),
                    departamento.getJefe(), departamento.getProgramadorList()).orElseThrow(() -> new SQLException("Error insertar Departamento"));
            // Para obtener su ID
            if (res.first()) {
                departamento.setId(res.getLong(1));
                // una vez insertado comprobamos que esta correcto para devolverlo
                db.close();
                return Optional.of(departamento);
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar departamento" + e.getMessage());
        }
        return Optional.of(departamento);
    }

    public Optional<Departamento> selectById(long id) {
        System.out.println("Obteniendo departamento con id: " + id);
        String query = "SELECT * FROM departamento WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        Departamento departamento = null;
        try {
            db.open();
            ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
            if (result.first()) {
                departamento=new Departamento(
                        result.getLong("id"),
                        result.getString("nombre"),
                        result.getInt("presupuesto"),
                        result.getString("jefe"),
                        result.getString("programadorList")
                );
                db.close();
                return Optional.of(departamento);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener departamento con id: " + id + " - " + e.getMessage());
        }
        return Optional.ofNullable(departamento);
    }

    public Optional<List<Departamento>> getAll() {
        System.out.println("Obteniendo todos los departamentos");
        String query = "SELECT * FROM departamento";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Departamento> list = null;
        try {
            db.open();
            ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar registros de departamentos"));
            list = new ArrayList<>();
            while (result.next()) {
                list.add(
                        new Departamento(
                                result.getLong("id"),
                                result.getString("nombre"),
                                result.getInt("presupuesto"),
                                result.getString("jefe"),
                                result.getString("programadorList")
                        )
                );
            }
            db.close();
            return Optional.of(list);
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los departamentos: " + e.getMessage());
        }
        return Optional.ofNullable(list);
    }


}

