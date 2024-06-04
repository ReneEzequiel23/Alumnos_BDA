/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidad.AlumnoEntidad;
import java.util.List;

/**
 *
 * @author filor
 */
public interface IAlumnoDAO {
    
    public List<AlumnoEntidad> buscarAlumnosTabla() throws PersistenciaException;
    
    public List<AlumnoEntidad> getAll(int beginIndex, int pageSize) throws PersistenciaException;
    
    public AlumnoEntidad insertar(AlumnoEntidad alumno) throws PersistenciaException;
    
    public AlumnoEntidad editar(AlumnoEntidad alumno) throws PersistenciaException;
    
    public AlumnoEntidad eliminar(AlumnoEntidad alumno) throws PersistenciaException;
    
}
