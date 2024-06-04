/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud;

import entidad.AlumnoEntidad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import persistencia.IConexionBD;
import persistencia.PersistenciaException;

/**
 *
 * @author filor
 */
public class Alumnos {

    final String SERVER = "localhost";
    final String BASE_DATOS = "crud";
    final String CADENA_CONEXION = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS;
    final String USUARIO = "root";
    final String CONTRASEÑA = "10279Fig";
    private IConexionBD conexionBD;

    public void insertar() {
        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);

            String sentenciaSql = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno) VALUES(?,?,?);";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "Mario");
            preparedStatement.setString(2, "Quevedo");
            preparedStatement.setString(3, "Lopez");

            preparedStatement.executeUpdate();

            ResultSet resultado = preparedStatement.getGeneratedKeys();

            while (resultado.next()) {
                System.out.println(resultado.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void leer() {
        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);

            String sentenciaSql = "SELECT * FROM alumnos WHERE idALumno = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, 1);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int idAlumno = resultado.getInt("idAlumno");
                String nombres = resultado.getString("nombres");
                String apellidoPaterno = resultado.getString("apellidoPaterno");
                String apellidoMaterno = resultado.getString("apellidoMaterno");
                boolean eliminado = resultado.getBoolean("eliminado");
                boolean activo = resultado.getBoolean("activo");

                System.out.println("ID del alumno: " + idAlumno);
                System.out.println("Nombres: " + nombres);
                System.out.println("Apellido paterno: " + apellidoPaterno);
                System.out.println("Apellido materno: " + apellidoMaterno);
                System.out.println("Eliminado: " + eliminado);
                System.out.println("Activo: " + activo);

            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public void editar() throws PersistenciaException {

        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);

            String sentenciaSql = "UPDATE alumnos SET nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, eliminado = ?, activo = ? WHERE idAlumno = ?";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "Roger");
            preparedStatement.setString(2, "Rabits");
            preparedStatement.setString(3, "Domingo");
            preparedStatement.setBoolean(4, false);
            preparedStatement.setBoolean(5, true);
            preparedStatement.setInt(6, 2);

            preparedStatement.executeUpdate();

            ResultSet resultado = preparedStatement.getGeneratedKeys();

            while (resultado.next()) {
                System.out.println(resultado.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

     public void eliminar() throws PersistenciaException {

        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);

            String sentenciaSql = "DELETE FROM alumnos WHERE ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,3);

            preparedStatement.executeUpdate();

            ResultSet resultado = preparedStatement.getGeneratedKeys();

            while (resultado.next()) {
                System.out.println(resultado.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
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
}
