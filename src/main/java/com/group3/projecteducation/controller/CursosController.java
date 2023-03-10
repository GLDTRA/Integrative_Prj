package com.group3.projecteducation.controller;

import com.group3.projecteducation.model.Usuario;
import com.group3.projecteducation.repository.CategoriasRepository;
import com.group3.projecteducation.repository.CursosRepository;
import com.group3.projecteducation.model.Curso;
import com.group3.projecteducation.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursosController {
    @Autowired
    private CursosRepository cursosRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll(){
        return ResponseEntity.ok(cursosRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable Long id) {
        return cursosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Curso>> getByTitulo(@PathVariable String titulo){
        List<Curso> curso = cursosRepository.findAllByTituloContainingIgnoreCase(titulo);
        if(curso.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(cursosRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Curso>> getByDescricao(@PathVariable String descricao){
        List<Curso> curso = cursosRepository.findAllByDescricaoContainingIgnoreCase(descricao);
        if(curso.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(cursosRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Curso>> getByCategoria(@PathVariable Long id){
        List<Curso> curso = cursosRepository.findAllByCategoria(categoriasRepository.findById(id));
        if(curso.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(cursosRepository.findAllByCategoria(categoriasRepository.findById(id)));
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Curso> post(@Valid @RequestBody Curso curso){
        if(categoriasRepository.existsById(curso.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cursosRepository.save(curso));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Curso> put(@Valid @RequestBody Curso curso) {
        if (cursosRepository.existsById(curso.getId())) {
            if (categoriasRepository.existsById(curso.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(cursosRepository.save(curso));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Curso> postagem = cursosRepository.findById(id);
        if(postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        cursosRepository.deleteById(id);
    }
}
