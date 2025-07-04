package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.UnidadeMapper;
import com.Uninter.VidaPlus.Controller.Request.UnidadeRequest;
import com.Uninter.VidaPlus.Controller.Response.UnidadeResponse;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import com.Uninter.VidaPlus.Service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/unidade")
@RequiredArgsConstructor
public class UnidadeController {

    private final UnidadeService unidadeService;

    @GetMapping("/")
    public ResponseEntity<List<UnidadeResponse> > getUnidades() {
        List<UnidadeResponse> unidades = unidadeService.getUnidades().stream().map(UnidadeMapper::toUnidadeReesponse).toList();
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> getUnidade(@PathVariable Long id) {
        Optional<UnidadeEntity> unidade = unidadeService.getUnidade(id);
        if (unidade.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(UnidadeMapper.toUnidadeReesponse(unidade.get()));
    }



    @PostMapping("/")
    public ResponseEntity<UnidadeResponse> createUnidade(@RequestBody UnidadeRequest unidade) {

        UnidadeEntity unidadeEntity = unidadeService.create(unidade);
        return ResponseEntity.ok(UnidadeMapper.toUnidadeReesponse(unidadeEntity));
    }
}
