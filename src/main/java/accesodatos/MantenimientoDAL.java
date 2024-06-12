package accesodatos;

import entidades.Equipo;
import entidades.Mantenimiento;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MantenimientoDAL {

    //CREAR
    public static int crear(Mantenimiento mantenimiento) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "INSERT INTO MantenimientoPC (Fecha, Descripcion, Costo, TipoMantenimiento, EquipoID) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setTimestamp(1, Timestamp.valueOf(mantenimiento.getFecha()));
                statement.setString(2, mantenimiento.getDescripcion());
                statement.setDouble(3, mantenimiento.getCosto());
                statement.setString(4, mantenimiento.getTipoMantenimiento());
                statement.setInt(5, mantenimiento.getEquipoId());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el mantenimiento", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    //MODIFICAR
    public static int modificar(Mantenimiento mantenimiento) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "UPDATE MantenimientoPC SET Fecha=?, Descripcion=?, Costo=?, TipoMantenimiento=?, EquipoID=? WHERE MantenimientoID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setTimestamp(1, fechstamp);
                statement.setString(2, mantenimiento.getDescripcion());
                statement.setDouble(3, mantenimiento.getCosto());
                statement.setString(4, mantenimiento.getTipoMantenimiento());
                statement.setInt(5, mantenimiento.getEquipoId());
                statement.setInt(6, mantenimiento.getMantenimientoId());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al modificar el mantenimiento", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    //ELIMINAR
    public static int eliminar(Mantenimiento mantenimiento) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "DELETE FROM MantenimientoPC WHERE MantenimientoID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, mantenimiento.getMantenimientoId());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar el mantenimiento", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }
    //BUCAR
    public static ArrayList<Mantenimiento> buscar(Mantenimiento mantenimientoSearch) {
        ArrayList<Mantenimiento> mantenimientos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "SELECT p.MantenimientoID, p.Fecha, p.Descripcion, p.Costo, p.TipoMantenimiento,p.EquipoID, c.Modelo AS ModeloEq FROM MantenimientoPC p";
            sql+=" INNER JOIN Equipos c ON c.EquipoID= p.EquipoID  ";
            sql+=" WHERE p.Fecha LIKE ? ";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "%" + mantenimientoSearch.getFecha() + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Mantenimiento mantenimiento = new Mantenimiento();
                        mantenimiento.setMantenimientoId(resultSet.getInt("MantenimientoID"));
                        mantenimiento.setFecha(resultSet.getString("Fecha"));
                        mantenimiento.setDescripcion(resultSet.getString("Descripcion"));
                        mantenimiento.setCosto(resultSet.getDouble("Costo"));
                        mantenimiento.setTipoMantenimiento(resultSet.getString("TipoMantenimiento"));
                        mantenimiento.setEquipoId(resultSet.getInt("EquipoID"));
                        Equipo equipo= new Equipo();
                        equipo.setModelo(resultSet.getString("ModeloEq"));
                        mantenimiento.setEquipo(equipo);
                        mantenimientos.add(mantenimiento);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al buscar mantenimientos", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return mantenimientos;
    }
}
