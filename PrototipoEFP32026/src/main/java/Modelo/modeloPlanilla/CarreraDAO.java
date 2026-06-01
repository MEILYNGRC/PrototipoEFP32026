/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.modeloPlanilla;

import Controlador.controladorPlanilla.Carrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Modelo.Conexion;
/**
 *
 * @author MEILYN GARCIA
 */
public class CarreraDAO {
    // INSERTAR
    public boolean insertar(Carrera carrera) {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "INSERT INTO carreras "
                + "(codigo_carrera, nombre_carrera, codigo_facultad, estatus_carrera) "
                + "VALUES (?, ?, ?, ?)";

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, carrera.getCodigoCarrera());
            stmt.setString(2, carrera.getNombreCarrera());
            stmt.setString(3, carrera.getCodigoFacultad());
            stmt.setString(4, carrera.getEstatusCarrera());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);
            return false;

        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    // BUSCAR
    public Carrera buscar(String codigo) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM carreras WHERE codigo_carrera = ?";

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, codigo);

            rs = stmt.executeQuery();

            if (rs.next()) {

                Carrera carrera = new Carrera();

                carrera.setCodigoCarrera(
                        rs.getString("codigo_carrera"));

                carrera.setNombreCarrera(
                        rs.getString("nombre_carrera"));

                carrera.setCodigoFacultad(
                        rs.getString("codigo_facultad"));

                carrera.setEstatusCarrera(
                        rs.getString("estatus_carrera"));

                return carrera;
            }

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);

        } finally {

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return null;
    }

    // MODIFICAR
    public boolean modificar(Carrera carrera) {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "UPDATE carreras "
                + "SET nombre_carrera = ?, "
                + "codigo_facultad = ?, "
                + "estatus_carrera = ? "
                + "WHERE codigo_carrera = ?";

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, carrera.getNombreCarrera());
            stmt.setString(2, carrera.getCodigoFacultad());
            stmt.setString(3, carrera.getEstatusCarrera());
            stmt.setString(4, carrera.getCodigoCarrera());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);
            return false;

        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    // ELIMINAR
    public boolean eliminar(String codigo) {

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "DELETE FROM carreras WHERE codigo_carrera = ?";

        try {

            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, codigo);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);
            return false;

        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }
}
