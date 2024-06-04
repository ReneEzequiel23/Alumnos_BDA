/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crud;

import entidad.AlumnoEntidad;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.AlumnoNegocio;
import negocio.IAlumnoNegocio;
import persistencia.AlumnoDAO;
import persistencia.ConexionBD;
import persistencia.IAlumnoDAO;
import persistencia.IConexionBD;
import persistencia.PersistenciaException;
import presentacion.frmCRUD;

/**
 *
 * @author filor
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Alumnos alumno = new Alumnos();

        IConexionBD conexionBD = new ConexionBD();
        IAlumnoDAO alumnoDAO = new AlumnoDAO(conexionBD);
        IAlumnoNegocio alumnoNegocio = new AlumnoNegocio(alumnoDAO);
        frmCRUD crud = new frmCRUD(alumnoNegocio);
        
        crud.show();

        
    }
    
}
