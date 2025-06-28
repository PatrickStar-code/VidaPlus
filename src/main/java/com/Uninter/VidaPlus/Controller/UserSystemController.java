package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Mapper.UserSystemMapper;
import com.Uninter.VidaPlus.Controller.Request.UserSystemRequest;
import com.Uninter.VidaPlus.Controller.Response.UserSystemResponse;
import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import com.Uninter.VidaPlus.Exceptions.NotFoundException;
import com.Uninter.VidaPlus.Service.UserSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/VidaPlus/user")
public class UserSystemController {

    private final UserSystemService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserSystemResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(UserSystemMapper.toUserResponse(service.getUser(id).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<UserSystemEntity> user = service.getUser(id);
        if(user.isPresent()) {
            service.deleteUser(id);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserSystemResponse> registerUser(@RequestBody UserSystemRequest userRequest, UriComponentsBuilder uriBuilder) {
        UserSystemEntity usuario = service.registerUser(userRequest);
        var uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getIdUserSystem()).toUri();
        return ResponseEntity.created(uri).body(UserSystemMapper.toUserResponse(usuario));
    }
}
