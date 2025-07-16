package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.UnidadeMapper;
import com.Uninter.VidaPlus.Controller.Request.UnidadeRequest;
import com.Uninter.VidaPlus.Controller.Response.UnidadeResponse;
import com.Uninter.VidaPlus.Entity.UnidadeEntity;
import com.Uninter.VidaPlus.Service.UnidadeService;
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
@RequestMapping("/VidaPlus/unidade")
@RequiredArgsConstructor
@Tag(name = "Unidade", description = "Operações relacionadas às unidades de saúde")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @Operation(summary = "Listar todas as unidades de saúde")
    @ApiResponse(responseCode = "200", description = "Lista de unidades retornada com sucesso")
    @GetMapping("/")
    public ResponseEntity<List<UnidadeResponse>> getUnidades() {
        List<UnidadeResponse> unidades = unidadeService.getUnidades()
                .stream()
                .map(UnidadeMapper::toUnidadeReesponse)
                .toList();
        return ResponseEntity.ok(unidades);
    }

    @Operation(summary = "Buscar uma unidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade encontrada",
                    content = @Content(schema = @Schema(implementation = UnidadeResponse.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> getUnidade(@PathVariable Long id) {
        Optional<UnidadeEntity> unidade = unidadeService.getUnidade(id);
        if (unidade.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(UnidadeMapper.toUnidadeReesponse(unidade.get()));
    }

    @Operation(summary = "Criar uma nova unidade de saúde")
    @ApiResponse(responseCode = "200", description = "Unidade criada com sucesso",
            content = @Content(schema = @Schema(implementation = UnidadeResponse.class)))
    @PostMapping("/")
    public ResponseEntity<UnidadeResponse> createUnidade(@RequestBody UnidadeRequest unidade) {
        UnidadeEntity unidadeEntity = unidadeService.create(unidade);
        return ResponseEntity.ok(UnidadeMapper.toUnidadeReesponse(unidadeEntity));
    }
}
