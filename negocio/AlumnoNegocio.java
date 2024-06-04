/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.AlumnoTablaDTO;
import dtos.EditarAlumnoDTO;
import dtos.EliminaAlumnoDTO;
import dtos.InsertarAlumnoDTO;
import entidad.AlumnoEntidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IAlumnoDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author filor
 */
public class AlumnoNegocio implements IAlumnoNegocio {

    private IAlumnoDAO alumnoDAO;

    public AlumnoNegocio(IAlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    @Override
    public List<AlumnoTablaDTO> buscarAlumnosTabla() throws NegocioException {
        try {

            List<AlumnoEntidad> alumnos = this.alumnoDAO.buscarAlumnosTabla();
            return this.convertirAlumnoTablaDTO(alumnos);
        } catch (PersistenciaException ex) {
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }
    
    @Override
    public List<AlumnoTablaDTO> getAll(int beginIndex, int pageSize) throws NegocioException {
        try {

            List<AlumnoEntidad> alumnos = this.alumnoDAO.getAll(beginIndex, pageSize);
            return this.convertirAlumnoTablaDTO(alumnos);
        } catch (PersistenciaException ex) {
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }
    
     @Override
    public InsertarAlumnoDTO insertar(InsertarAlumnoDTO alumno) throws NegocioException {
        try {
            
            alumnoDAO.insertar(new AlumnoEntidad(alumno.getNombres(), alumno.getApellidoPaterno(), alumno.getApellidoMaterno()));
            
        } catch (PersistenciaException ex) {
            Logger.getLogger(AlumnoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return alumno;
    }

    private List<AlumnoTablaDTO> convertirAlumnoTablaDTO(List<AlumnoEntidad> alumnos) throws NegocioException {
        if (alumnos == null) {
            throw new NegocioException("No se pudieron obtener los alumnos.");
        }

        List<AlumnoTablaDTO> alumnosDTO = new ArrayList<>();
        for (AlumnoEntidad alumno : alumnos) {
            AlumnoTablaDTO dto = new AlumnoTablaDTO();
            dto.setIdAlumno(alumno.getIdAlumno());
            dto.setNombres(alumno.getNombres());
            dto.setApellidoPaterno(alumno.getApellidoPaterno());
            dto.setApellidoMaterno(alumno.getApellidoMaterno());
            dto.setEstatus(alumno.isActivo() == true ? "Activo" : "Inactivo");
            alumnosDTO.add(dto);

        }
        return alumnosDTO;
    }

    @Override
    public EditarAlumnoDTO editar(EditarAlumnoDTO alumno) throws NegocioException {
        try {
        alumnoDAO.editar(new AlumnoEntidad(alumno.getIdAlumno(),alumno.getNombres(),alumno.getApellidoPaterno(),alumno.getApellidoMaterno(),!alumno.isActivo(),alumno.isActivo()));
        } catch (PersistenciaException ex) {
            Logger.getLogger(AlumnoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
    }

    @Override
    public EliminaAlumnoDTO eliminar(EliminaAlumnoDTO alumno) throws NegocioException {
    try {
        alumnoDAO.eliminar(new AlumnoEntidad(alumno.getIdAlumno()));
        } catch (PersistenciaException ex) {
            Logger.getLogger(AlumnoNegocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;    
    }   

}
