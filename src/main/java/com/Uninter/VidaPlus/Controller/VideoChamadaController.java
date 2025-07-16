package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.VideoChamadaMapper;
import com.Uninter.VidaPlus.Controller.Request.ObservacaoRequest;
import com.Uninter.VidaPlus.Controller.Response.VideoChamadaResponse;
import com.Uninter.VidaPlus.Entity.VideoChamadaEntity;
import com.Uninter.VidaPlus.Service.VideoChamadaService;
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
@RequestMapping("/VidaPlus/videoChamada")
@RequiredArgsConstructor
@Tag(name = "Videochamada", description = "Gerenciamento de videochamadas entre pacientes e profissionais")
public class VideoChamadaController {

    private final VideoChamadaService videoChamadaService;

    @Operation(summary = "Listar todas as videochamadas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/")
    public ResponseEntity<List<VideoChamadaResponse>> getVideoChamada() {
        List<VideoChamadaEntity> videoChamada = videoChamadaService.getVideoChamada();
        List<VideoChamadaResponse> videoChamadaResponse = videoChamada.stream()
                .map(VideoChamadaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(videoChamadaResponse);
    }

    @Operation(summary = "Buscar videochamada por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videochamada encontrada",
                    content = @Content(schema = @Schema(implementation = VideoChamadaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Videochamada não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VideoChamadaResponse> getVideoChamadaById(@PathVariable Long id) {
        Optional<VideoChamadaEntity> videoChamada = videoChamadaService.getVideoChamadaById(id);
        if (videoChamada.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(VideoChamadaMapper.toResponse(videoChamada.get()));
    }

    @Operation(summary = "Atualizar observação de uma videochamada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Videochamada atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = VideoChamadaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Videochamada não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<VideoChamadaResponse> updateVideoChamada(
            @PathVariable Long id,
            @RequestBody ObservacaoRequest observacao) {
        Optional<VideoChamadaEntity> videoChamadaEntity = videoChamadaService.getVideoChamadaById(id);
        if (videoChamadaEntity.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(
                VideoChamadaMapper.toResponse(videoChamadaService.editVideoChamada(id, observacao))
        );
    }
}
