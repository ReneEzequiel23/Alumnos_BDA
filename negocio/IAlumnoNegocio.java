/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.AlumnoTablaDTO;
import dtos.EditarAlumnoDTO;
import dtos.EliminaAlumnoDTO;
import dtos.InsertarAlumnoDTO;
import java.util.List;
import persistencia.PersistenciaException;

/**
 *
 * @author filor
 */
public interface IAlumnoNegocio {
   public List<AlumnoTablaDTO> buscarAlumnosTabla() throws NegocioException;
   
   public List<AlumnoTablaDTO> getAll(int beginIndex, int pageSize) throws NegocioException;
   
   public InsertarAlumnoDTO insertar (InsertarAlumnoDTO alumno) throws NegocioException;
   
   public EditarAlumnoDTO editar(EditarAlumnoDTO alumno) throws NegocioException;
   
   public EliminaAlumnoDTO eliminar(EliminaAlumnoDTO alumno) throws NegocioException;
}
