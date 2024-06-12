package accesodatos;

import entidades.Equipo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class EquipoDAL {

    //CREAR
    public static int crear(Equipo equipo) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "INSERT INTO Equipos (NumeroSerie, Marca, Modelo, FechaAdquisicion,Ubicacion) VALUES (?, ?, ?, ?,?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, equipo.getNumeroSerie());
                statement.setString(2, equipo.getMarca());
                statement.setString(3, equipo.getModelo());
                statement.setDate(4,new java.sql.Date(equipo.getFechaAdqui().getDate()));
                statement.setString(5, equipo.getUbicacion());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al crear el producto", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos" + e);
        }
    }

    //MODIFICAR
    public static int modificar(Equipo equipo) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "UPDATE Equipos SET NumeroSerie=?, Marca=?, Modelo=?, FechaAdquisicion=?, Ubicacion=? WHERE EquipoID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, equipo.getNumeroSerie());
                statement.setString(2, equipo.getMarca());
                statement.setString(3, equipo.getModelo());
                statement.setDate(4,new java.sql.Date(equipo.getFechaAdqui().getDate()));
                statement.setString(5, equipo.getUbicacion());
                statement.setInt(6, equipo.getEquipoId());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al modificar el producto", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    //ELIMINAR
    public static int eliminar(Equipo equipo) {
        try (Connection conn = ComunDB.obtenerConexion()) {

            String sql = "DELETE FROM Equipos WHERE EquipoID=?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, equipo.getEquipoId());
                int rowsAffected = statement.executeUpdate();
                return rowsAffected;
            } catch (SQLException e) {
                throw new RuntimeException("Error al eliminar el producto", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
    }

    //BUSCAR
    public static ArrayList<Equipo> buscar(Equipo equipoSearch) {
        ArrayList<Equipo> equipos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "SELECT p.EquipoID, p.NumeroSerie, p.Marca, p.Modelo, p.FechaAdquisicion, p.Ubicacion "
                    + "FROM Equipos p "
                    + "WHERE p.Modelo LIKE ? ";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "%" + equipoSearch.getModelo() + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Equipo equipo = new Equipo();
                        equipo.setEquipoId(resultSet.getInt("EquipoID"));
                        equipo.setNumeroSerie(resultSet.getString("NumeroSerie"));
                        equipo.setMarca(resultSet.getString("Marca"));
                        equipo.setModelo(resultSet.getString("Modelo"));
                        equipo.setFechaAdqui(resultSet.getDate("FechaAdquisicion"));
                        equipo.setUbicacion(resultSet.getString("Ubicacion"));
                        equipos.add(equipo);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al buscar productos", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return equipos;
    }

    //OBTENER TODOS
    public static ArrayList<Equipo> obtenerTodos() {
        ArrayList<Equipo> equipos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion()) {
            String sql = "SELECT EquipoID, NumeroSerie, Marca, Modelo, FechaAdquisicion, Ubicacion FROM Equipos";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int equipoId = resultSet.getInt("EquipoID");
                        String numeroSerie = resultSet.getString("NumeroSerie");
                        String marca = resultSet.getString("Marca");
                        String modelo = resultSet.getString("Modelo");
                        Date fechaAdquisicion = resultSet.getDate("FechaAdquisicion");
                        String ubicacion = resultSet.getString("Ubicacion");
                        Equipo equipo = new Equipo(equipoId,numeroSerie,marca,modelo,fechaAdquisicion,ubicacion);
                        equipos.add(equipo);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error al obtener los equipos", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la conexión a la base de datos", e);
        }
        return equipos;
    }
}
