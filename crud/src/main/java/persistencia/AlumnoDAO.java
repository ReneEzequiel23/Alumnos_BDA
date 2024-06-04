/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidad.AlumnoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filor
 */
public class AlumnoDAO implements IAlumnoDAO {

    private IConexionBD conexionBD;

    public AlumnoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<AlumnoEntidad> buscarAlumnosTabla() throws PersistenciaException {
        try {

            List<AlumnoEntidad> alumnosLista = null;

            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo FROM alumnos";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);

            while (resultado.next()) {
                if (alumnosLista == null) {
                    alumnosLista = new ArrayList<>();
                }
                AlumnoEntidad alumno = this.convertirEntidad(resultado);
                alumnosLista.add(alumno);
            }
            conexion.close();
            return alumnosLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error al leer la base de datos");
        }
    }

    @Override
    public AlumnoEntidad insertar(AlumnoEntidad alumno) throws PersistenciaException {

        try {
            Connection conexion = this.conexionBD.crearConexion();

            String sentenciaSql = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno) VALUES(?,?,?);";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, alumno.getNombres());
            preparedStatement.setString(2, alumno.getApellidoPaterno());
            preparedStatement.setString(3, alumno.getApellidoMaterno());

            preparedStatement.executeUpdate();

            ResultSet resultado = preparedStatement.getGeneratedKeys();

            while (resultado.next()) {
                System.out.println(resultado.getInt(1));
                alumno = this.convertirEntidad(resultado);
            }
            conexion.commit();
            
            if (preparedStatement != null) {
                preparedStatement.isClosed();
            }
            
            try{
            conexion.rollback();
            
            }catch(SQLException e){
                e.printStackTrace();
            }
            
            return alumno;

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            throw new PersistenciaException("Ocurrio un error al leer la base de datos");
            
        }
    }

    private AlumnoEntidad convertirEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idAlumno");
        String nombre = resultado.getString("nombres");
        String paterno = resultado.getString("apellidoPaterno");
        String materno = resultado.getString("apellidoMaterno");
        boolean eliminado = resultado.getBoolean("eliminado");
        boolean activo = resultado.getBoolean("activo");
        return new AlumnoEntidad(id, nombre, paterno, materno, eliminado, activo);
    }

    @Override
    public AlumnoEntidad editar(AlumnoEntidad alumno) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();

            String sentenciaSql = "UPDATE alumnos SET nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, eliminado = ?, activo = ? WHERE idAlumno = ?";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, alumno.getNombres());
            preparedStatement.setString(2, alumno.getApellidoPaterno());
            preparedStatement.setString(3, alumno.getApellidoMaterno());
            preparedStatement.setBoolean(4, alumno.isEliminado());
            preparedStatement.setBoolean(5, alumno.isActivo());
            preparedStatement.setInt(6, alumno.getIdAlumno());

            preparedStatement.executeUpdate();

            ResultSet resultado = preparedStatement.getGeneratedKeys();

            while (resultado.next()) {
                System.out.println(resultado.getInt(1));
                alumno = this.convertirEntidad(resultado);
            }

            conexion.close();
            return alumno;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return alumno;
    }

    @Override
    public AlumnoEntidad eliminar(AlumnoEntidad alumno) throws PersistenciaException {
        try {
            Connection conexion = this.conexionBD.crearConexion();

            String sentenciaSql = "DELETE FROM alumnos WHERE idAlumno=?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, alumno.getIdAlumno());

            preparedStatement.executeUpdate();

            ResultSet resultado = preparedStatement.getGeneratedKeys();

            while (resultado.next()) {
                System.out.println(resultado.getInt(1));
                alumno = this.convertirEntidad(resultado);
            }

            conexion.close();
            return alumno;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return alumno;
    }

    public List<AlumnoEntidad> getAll(int beginIndex, int pageSize) throws PersistenciaException {
        try {
            List<AlumnoEntidad> alumnosLista = null;

            Connection conexion = this.conexionBD.crearConexion();
            String sentenciaSql = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo FROM alumnos ASC ORDER BY idAlumno LIMIT ? OFFSET ?;";
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, beginIndex);
            preparedStatement.setInt(2, pageSize);

            preparedStatement.executeUpdate();

            ResultSet resultado = preparedStatement.getGeneratedKeys();

            while (resultado.next()) {
                if (alumnosLista == null) {
                    alumnosLista = new ArrayList<>();
                }
                AlumnoEntidad alumno = this.convertirEntidad(resultado);
                alumnosLista.add(alumno);
            }
            return alumnosLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrio un error al leer la base de datos");
        }
    }
}
