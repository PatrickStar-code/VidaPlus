package com.Uninter.VidaPlus.Controller;

import com.Uninter.VidaPlus.Controller.Request.LoginUser;
import com.Uninter.VidaPlus.Records.RefreshTokenData;
import com.Uninter.VidaPlus.Records.TokenData;
import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import com.Uninter.VidaPlus.Repository.UserSystemRepository;
import com.Uninter.VidaPlus.Service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "Autenticação", description = "Endpoints de login e renovação de token")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserSystemRepository userRepository;

    @Operation(summary = "Autenticar usuário e gerar token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenData.class))),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> Login(@Valid @RequestBody LoginUser user) {
        try {
            var authentication = new UsernamePasswordAuthenticationToken(user.login(), user.password());
            var Token = authenticationManager.authenticate(authentication);

            String TokenJwt = tokenService.generateToken((UserSystemEntity) Token.getPrincipal());
            String refreshJwt = tokenService.generateRefreshToken((UserSystemEntity) Token.getPrincipal());

            return ResponseEntity.ok(new TokenData(TokenJwt, refreshJwt));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Operation(summary = "Gerar novos tokens a partir de um refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tokens renovados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenData.class))),
            @ApiResponse(responseCode = "400", description = "Refresh token inválido")
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenData> refreshToken(@Valid @RequestBody RefreshTokenData user) {
        var refreshToken = user.refreshToken();
        Long userId = Long.valueOf(tokenService.verifyToken(refreshToken));
        UserSystemEntity userFinded = userRepository.findById(userId).orElseThrow();

        String TokenJwt = tokenService.generateToken(userFinded);
        String refreshJwt = tokenService.generateRefreshToken(userFinded);

        return ResponseEntity.ok(new TokenData(TokenJwt, refreshJwt));
    }
}
