package com.bzetab.MSKEstibas.controller;

import com.bzetab.MSKEstibas.model.Estiba;
import com.bzetab.MSKEstibas.model.dto.RespuestaGeneral;
import com.bzetab.MSKEstibas.service.EstibaImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/wsmskestiba/estiba")
public class EstibaController {
    private EstibaImp estibaImp;

    //agrego '/id/' para evitar ambiguedades con nrodocumento y nombre
    @GetMapping("/id/{idestiba}")
    public ResponseEntity<Estiba> obtenerEstibaPorID(@PathVariable("idestiba") int idestiba){
        Estiba estiba = estibaImp.obtenerEstibaPorId(idestiba);
        return ResponseEntity.ok(estiba);
    }

    @GetMapping("/nrodocumento/{nroDocumento}")
    public ResponseEntity<Estiba> obtenerEstibaPorNroDocumento(@PathVariable("nroDocumento") String nroDocumento){
        Estiba nrodocumento = estibaImp.obtenerEstibaPorNroDocumento(nroDocumento);
        return ResponseEntity.ok(nrodocumento);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Estiba> obtenerEstibaPorNombre(@PathVariable("nombre") String nombre){
        Estiba nombreEstiba = estibaImp.obtenerEstibaPorNombre(nombre);
        return ResponseEntity.ok(nombreEstiba);
    }

    @GetMapping("")
    public ResponseEntity<List<Estiba>> listarEstibas(){
        List<Estiba> estibas = estibaImp.listarEstibas();
        return ResponseEntity.ok(estibas);
    }

    @PostMapping
    public ResponseEntity<RespuestaGeneral>insertarEstiba(@RequestBody Estiba estiba){
        String mensaje = "Estiba registrado correctamente";
        boolean resultado = true;
        try{
            estibaImp.guardarEstiba(estiba);
        }catch(Exception ex){
            mensaje = "Error: Ocurrio un error al conectarse a la BD";
            resultado = false;
        }
        return ResponseEntity.ok(RespuestaGeneral.builder().respuesta(resultado).mensaje(mensaje).build());
    }

    @PutMapping("/{idestiba}")
    public ResponseEntity<Estiba>actualizarEstiba(@PathVariable("idestiba") int idestiba, @RequestBody Estiba estiba){
        estiba = estibaImp.actualizarEstiba(estiba);
        return ResponseEntity.ok(estiba);
    }

    @DeleteMapping("/{idestiba}")
    public ResponseEntity<String>eliminarEstiba(@PathVariable("idestiba") int idestiba){
        String estibaEliminar = estibaImp.eliminarEstiba(idestiba);
        return ResponseEntity.ok(estibaEliminar);
    }
}
