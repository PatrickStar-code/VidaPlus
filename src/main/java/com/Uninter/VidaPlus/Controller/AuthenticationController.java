package com.Uninter.VidaPlus.Controller;


import com.Uninter.VidaPlus.Controller.Request.LoginUser;
import com.Uninter.VidaPlus.Controller.Request.UserSystemRequest;
import com.Uninter.VidaPlus.Controller.Response.UserSystemResponse;
import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import com.Uninter.VidaPlus.Records.RefreshTokenData;
import com.Uninter.VidaPlus.Records.TokenData;
import com.Uninter.VidaPlus.Repository.UserSystemRepository;
import com.Uninter.VidaPlus.Service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserSystemRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> Login(@Valid @RequestBody LoginUser user){
        try {
            var authentication = new UsernamePasswordAuthenticationToken(user.login(), user.password());
            var Token = authenticationManager.authenticate(authentication);

            String TokenJwt =tokenService.generateToken((UserSystemEntity) Token.getPrincipal());
            String refreshJwt = tokenService.generateRefreshToken((UserSystemEntity) Token.getPrincipal());

            return ResponseEntity.ok(new TokenData (TokenJwt, refreshJwt));
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenData> refreshToken(@Valid @RequestBody RefreshTokenData user){
        var refreshToken = user.refreshToken();
        Long userId = Long.valueOf(tokenService.verifyToken(refreshToken));
        UserSystemEntity userFinded = userRepository.findById(userId).orElseThrow();

        String TokenJwt =tokenService.generateToken(userFinded);
        String refreshJwt = tokenService.generateRefreshToken(userFinded);

        return ResponseEntity.ok(new TokenData(TokenJwt, refreshJwt));
    }

}
