package com.portfolio.arwc.Controller;

import com.portfolio.arwc.Entity.Educacion;
import com.portfolio.arwc.Interface.IEducacionService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("educaciones")
@CrossOrigin (origins = "https://frontend-arwc.web.app")
public class EducacionController {
    @Autowired IEducacionService iEducacionService;
    
    @GetMapping("/traer")
    public List<Educacion> getEducacion(){
        return iEducacionService.getEducacion();
    }
    
    @GetMapping("/traer/{id}")
    public Educacion getEducacionID(@PathVariable Long id){
        return iEducacionService.findEducacion(id);   
    }
    
    @GetMapping("/traer/persona/{id}")
    public List<Educacion> getEducacionByUser(@PathVariable Long id) {
        return iEducacionService.getEducacionByPersonaID(id);
    }
    
    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping("/crear")
    public Educacion createEducacion(@RequestBody Educacion educacion){
        iEducacionService.saveEducacion(educacion);
        return educacion;
    }
    
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/borrar/{id}")
    public Long deleteEducacion(@PathVariable Long id){
        iEducacionService.deleteEducacion(id);
        return id;
    }
    
    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping("/editar/{id}")
    public Educacion editEducacion(
            @PathVariable Long id, @RequestBody Educacion p){
        Educacion educacion = iEducacionService.findEducacion(id);
        educacion.setLugar(p.getLugar());
        educacion.setTitulo(p.getTitulo());
        educacion.setStartDate(p.getStartDate());
        educacion.setEndDate(p.getEndDate());
        educacion.setDescripcion(p.getDescripcion());
        educacion.setImg(p.getImg());
        educacion.setImgBool(p.getImgBool());
        educacion.setPersona(p.getPersona());
        
        
        iEducacionService.saveEducacion(educacion);
        return educacion;
    }
}
