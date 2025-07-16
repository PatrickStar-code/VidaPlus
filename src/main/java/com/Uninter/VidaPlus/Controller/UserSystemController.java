package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.UserSystemMapper;
import com.Uninter.VidaPlus.Controller.Request.UserSystemRequest;
import com.Uninter.VidaPlus.Controller.Response.UserSystemResponse;
import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import com.Uninter.VidaPlus.Exceptions.NotFoundException;
import com.Uninter.VidaPlus.Service.UserSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/VidaPlus/user")
@Tag(name = "Usuário", description = "Operações relacionadas ao gerenciamento de usuários do sistema")
public class UserSystemController {

    private final UserSystemService service;

    @Operation(summary = "Buscar um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UserSystemResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserSystemResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                UserSystemMapper.toUserResponse(
                        service.getUser(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"))
                )
        );
    }

    @Operation(summary = "Deletar um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<UserSystemEntity> user = service.getUser(id);
        user.ifPresent(u -> service.deleteUser(id));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Registrar um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
            content = @Content(schema = @Schema(implementation = UserSystemResponse.class)))
    @PostMapping("/register")
    public ResponseEntity<UserSystemResponse> registerUser(@RequestBody UserSystemRequest userRequest, UriComponentsBuilder uriBuilder) {
        UserSystemEntity usuario = service.registerUser(userRequest);
        var uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getIdUserSystem()).toUri();
        return ResponseEntity.created(uri).body(UserSystemMapper.toUserResponse(usuario));
    }
}
