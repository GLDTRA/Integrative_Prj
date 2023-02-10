package com.group3.projecteducation.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.group3.projecteducation.repository.CategoriasRepository;
import com.group3.projecteducation.repository.CursosRepository;
import com.group3.projecteducation.model.Categoria;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok(categoriasRepository.findAll());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Optional<Categoria>> getByTitulo(@PathVariable String titulo){
        Optional<Categoria> categoria = categoriasRepository.findAllByTituloContainingIgnoreCase(titulo);
        if(categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(categoriasRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @GetMapping("/area/{area}")
    public ResponseEntity<Optional<Categoria>> getByArea(@PathVariable String area){
        Optional<Categoria> categoria = categoriasRepository.findAllByAreaContainingIgnoreCase(area);
        if(categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(categoriasRepository.findAllByAreaContainingIgnoreCase(area));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id){
        return  categoriasRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            }

    @PostMapping("/cadastrar")
    public ResponseEntity<Categoria>post(@Valid @RequestBody Categoria categoria ){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categoria));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Categoria> Put(@Valid @RequestBody Categoria categoria){
        return categoriasRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                .body(categoriasRepository.save(categoria))).orElse(ResponseEntity
                .status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id){
        Optional<Categoria> categoria = categoriasRepository.findById(id);
        if(categoria.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        categoriasRepository.deleteById(id);
    }
}
