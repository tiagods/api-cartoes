package com.tiagods.cartoes.resource;

import com.tiagods.cartoes.model.OperationType;
import com.tiagods.cartoes.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 *  Recurso para disponibilização de dados via rest para tipos de Operacoes
 */

@RestController
@RequestMapping("/api/operations")
public class OperationTypeResource {

    @Autowired
    private OperationTypeService service;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@Valid @PathVariable Long id){
        return ResponseEntity.ok().body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody OperationType operationType){
        service.atualizar(operationType,id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody OperationType operationType){
        operationType = service.criar(operationType);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(operationType.getOperationTypeId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
