package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.ProfissionalSaudeMapper;
import com.Uninter.VidaPlus.Controller.Request.ProfissionalSaudeRequest;
import com.Uninter.VidaPlus.Controller.Response.ProfissionalSaudeResponse;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Service.ProfissionalSaudeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VidaPlus/profissional-saude")
@RequiredArgsConstructor
@Tag(name = "Profissional de Saúde", description = "Operações relacionadas aos profissionais de saúde cadastrados")
public class ProfissionalSaudeController {

    private final ProfissionalSaudeService profissionalSaudeService;

    @Operation(summary = "Listar todos os profissionais de saúde")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/")
    public ResponseEntity<List<ProfissionalSaudeResponse>> getProfissionalSaude() {
        List<ProfissionalSaudeEntity> profissionalSaudeEntities = profissionalSaudeService.getProfissionalSaude();
        List<ProfissionalSaudeResponse> profissionalSaudeResponses = profissionalSaudeEntities
                .stream()
                .map(ProfissionalSaudeMapper::toProfissionalSaudeResponse)
                .toList();
        return ResponseEntity.ok(profissionalSaudeResponses);
    }

    @Operation(summary = "Buscar profissional de saúde por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profissional encontrado",
                    content = @Content(schema = @Schema(implementation = ProfissionalSaudeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> getProfissionalSaude(@PathVariable Long id) {
        Optional<ProfissionalSaudeEntity> profissionalSaudeEntity = profissionalSaudeService.getProfissionalSaudeById(id);
        if (profissionalSaudeEntity.isEmpty()) return ResponseEntity.notFound().build();
        ProfissionalSaudeResponse response = ProfissionalSaudeMapper.toProfissionalSaudeResponse(profissionalSaudeEntity.get());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Excluir profissional de saúde por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Profissional excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfissionalSaude(@PathVariable Long id) {
        Optional<ProfissionalSaudeEntity> profissionalSaudeEntity = profissionalSaudeService.getProfissionalSaudeById(id);
        if (profissionalSaudeEntity.isEmpty()) return ResponseEntity.notFound().build();
        profissionalSaudeService.deleteProfissionalSaude(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cadastrar um novo profissional de saúde")
    @ApiResponse(responseCode = "200", description = "Profissional cadastrado com sucesso",
            content = @Content(schema = @Schema(implementation = ProfissionalSaudeResponse.class)))
    @PostMapping("/")
    public ResponseEntity<ProfissionalSaudeResponse> registerProfissionalSaude(
            @RequestBody ProfissionalSaudeRequest profissionalSaudeRequest) {
        ProfissionalSaudeEntity entity = profissionalSaudeService.registerProfissionalSaude(profissionalSaudeRequest);
        return ResponseEntity.ok(ProfissionalSaudeMapper.toProfissionalSaudeResponse(entity));
    }

    @Operation(summary = "Atualizar dados de um profissional de saúde")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profissional atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = ProfissionalSaudeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalSaudeResponse> updateProfissionalSaude(
            @PathVariable Long id,
            @RequestBody ProfissionalSaudeRequest profissionalSaudeRequest) {

        Optional<ProfissionalSaudeEntity> profissionalSaudeEntity = profissionalSaudeService.getProfissionalSaudeById(id);
        if (profissionalSaudeEntity.isEmpty()) return ResponseEntity.notFound().build();

        Optional<ProfissionalSaudeEntity> updatedEntity = profissionalSaudeService.editProfissionalSaude(id, profissionalSaudeRequest);
        return ResponseEntity.ok(ProfissionalSaudeMapper.toProfissionalSaudeResponse(updatedEntity.get()));
    }
}
