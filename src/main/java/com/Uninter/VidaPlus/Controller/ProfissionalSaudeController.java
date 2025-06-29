package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.ProfissionalSaudeMapper;
import com.Uninter.VidaPlus.Controller.Request.ProfissionalSaudeRequest;
import com.Uninter.VidaPlus.Controller.Response.ProfissionalSaudeResponse;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Service.ProfissionalSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/profissional-saude")
@RequiredArgsConstructor
public class ProfissionalSaudeController {

    private final ProfissionalSaudeService profissionalSaudeService;

    @GetMapping("/")
    public ResponseEntity<List<ProfissionalSaudeResponse>> getProfissionalSaude() {
        List<ProfissionalSaudeEntity> profissionalSaudeEntities = profissionalSaudeService.getProfissionalSaude();
        List<ProfissionalSaudeResponse> profissionalSaudeResponses = profissionalSaudeEntities.stream().map(ProfissionalSaudeMapper::toProfissionalSaudeResponse).toList();
        return ResponseEntity.ok(profissionalSaudeResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> getProfissionalSaude(@PathVariable Long id) {
        Optional<ProfissionalSaudeEntity> profissionalSaudeEntity = profissionalSaudeService.getProfissionalSaudeById(id);
        if(profissionalSaudeEntity.isEmpty()) return ResponseEntity.notFound().build();
        ProfissionalSaudeResponse profissionalSaudeResponse = ProfissionalSaudeMapper.toProfissionalSaudeResponse(profissionalSaudeEntity.get());
        return ResponseEntity.ok(profissionalSaudeResponse);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfissionalSaude(@PathVariable Long id) {
        Optional<ProfissionalSaudeEntity> profissionalSaudeEntity = profissionalSaudeService.getProfissionalSaudeById(id);
        if(profissionalSaudeEntity.isEmpty()) return ResponseEntity.notFound().build();
        profissionalSaudeService.deleteProfissionalSaude(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<ProfissionalSaudeResponse> registerProfissionalSaude(@RequestBody ProfissionalSaudeRequest profissionalSaudeRequest) {
        ProfissionalSaudeEntity profissionalSaudeEntity1 = profissionalSaudeService.registerProfissionalSaude(profissionalSaudeRequest);
        return ResponseEntity.ok(ProfissionalSaudeMapper.toProfissionalSaudeResponse(profissionalSaudeEntity1));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> updateProfissionalSaude(@PathVariable Long id, @RequestBody ProfissionalSaudeRequest profissionalSaudeRequest) {
        Optional<ProfissionalSaudeEntity> profissionalSaudeEntity = profissionalSaudeService.getProfissionalSaudeById(id);
        if (profissionalSaudeEntity.isEmpty()) return ResponseEntity.notFound().build();
        Optional<ProfissionalSaudeEntity> profissionalSaudeEntity1 = profissionalSaudeService.editProfissionalSaude(id, profissionalSaudeRequest);
        return ResponseEntity.ok(ProfissionalSaudeMapper.toProfissionalSaudeResponse(profissionalSaudeEntity1.get()));
    }
}
