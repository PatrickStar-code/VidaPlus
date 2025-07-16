package com.Uninter.VidaPlus.Service;


import com.Uninter.VidaPlus.Controller.Request.UpdatePassword;
import com.Uninter.VidaPlus.Controller.Request.UserSystemRequest;
import com.Uninter.VidaPlus.Entity.PacienteEntity;
import com.Uninter.VidaPlus.Entity.ProfissionalSaudeEntity;
import com.Uninter.VidaPlus.Entity.UserSystemEntity;
import com.Uninter.VidaPlus.Exceptions.IdConflictException;
import com.Uninter.VidaPlus.Exceptions.NotFoundException;
import com.Uninter.VidaPlus.Exceptions.PasswordConfirmIncorrectException;
import com.Uninter.VidaPlus.Exceptions.ValueExistException;
import com.Uninter.VidaPlus.Repository.PacienteRepository;
import com.Uninter.VidaPlus.Repository.ProfissionalSaudeRepository;
import com.Uninter.VidaPlus.Repository.UserSystemRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserSystemService implements UserDetailsService {

    private final UserSystemRepository userRepository;
    private final PasswordEncoder encriptador;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalSaudeRepository profissionalRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLoginIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado!"));
    }



    @Transactional
    public Optional<UserSystemEntity> updateIsActive(Long id) {
        UserSystemEntity user = userRepository.findById(id).orElseThrow( () -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"));
        user.setStatusAtivo(!user.getStatusAtivo());
        return Optional.of(userRepository.save(user));
    }


    @Transactional
    public Optional<Void> deleteUser(long id) {
        UserSystemEntity user = userRepository.findById(id).orElseThrow( () -> new NotFoundException("NOT_FOUND", "Usuário nao encontrado!"));
        userRepository.delete(user);
        return Optional.empty();
    }

    @Transactional
    public UserSystemEntity registerUser(@Valid UserSystemRequest data) {
        validateExclusiveIds(data);
        validateEmail(data.email());
        validateLogin(data.login());

        var passwordEncrypted = encriptador.encode(data.passwordHash());
        var user = new UserSystemEntity(data, passwordEncrypted);

        associatePacienteIfPresent(user, data.idPaciente());
        associateProfissionalIfPresent(user, data.idProfissional());

        return userRepository.save(user);
    }

    private void validateExclusiveIds(UserSystemRequest data) {
        if (data.idPaciente() != null && data.idProfissional() != null) {
            throw new IdConflictException("ID_CONFLICT", "Conflito de IDs: paciente e profissional não podem ser definidos simultaneamente.");
        }

        if (data.idPaciente() != null && userRepository.findByPacienteIdPaciente(data.idPaciente()).isPresent()) {
            throw new IdConflictException("ID_CONFLICT", "ID do paciente já cadastrado.");
        }

        if (data.idProfissional() != null && userRepository.findByProfissionalIdProfissional(data.idProfissional()).isPresent()) {
            throw new IdConflictException("ID_CONFLICT", "ID do profissional já cadastrado.");
        }
    }

    private void validateEmail(String email) {
        if (userRepository.findByEmailIgnoreCase(email).isPresent()) {
            throw new ValueExistException("EMAIL_EXIST", "Email já cadastrado!");
        }
    }

    private void validateLogin(String login) {
        if (userRepository.findByLoginIgnoreCase(login).isPresent()) {
            throw new ValueExistException("LOGIN_EXIST", "Login já cadastrado!");
        }
    }

    private void associatePacienteIfPresent(UserSystemEntity user, Long idPaciente) {
        if (idPaciente != null) {
            PacienteEntity paciente = pacienteRepository.findById(idPaciente)
                    .orElseThrow(() -> new NotFoundException("NOT_FOUND", "Paciente não encontrado"));
            user.setPaciente(paciente);
        }
    }

    private void associateProfissionalIfPresent(UserSystemEntity user, Long idProfissional) {
        if (idProfissional != null) {
            ProfissionalSaudeEntity profissional = profissionalRepository.findById(idProfissional)
                    .orElseThrow(() -> new NotFoundException("NOT_FOUND", "Profissional não encontrado"));
            user.setProfissional(profissional);
        }
    }


    @Transactional
    public Optional<UserSystemEntity> updatePassword(UpdatePassword updatePassword, Long id) {
        Optional<UserSystemEntity> user = userRepository.findById(id);
        if(user.isPresent()){
            UserSystemEntity userEntity = user.get();
            if(!encriptador.matches(updatePassword.confirmPassword(), userEntity.getPassword())){
                 throw new PasswordConfirmIncorrectException("PASSWORD_NOT_MATCH", "Senhas nao conferem!");
             }else{
                 userEntity.setPassword(encriptador.encode(updatePassword.newPassword()));
                 return Optional.of(userRepository.save(userEntity));
             }
        }
        return Optional.empty();
    }


    public Optional<UserSystemEntity> getUser(Long id) {
        Optional<UserSystemEntity> user = userRepository.findById(id);
        if(user.isPresent()){
            return Optional.of(user.get());
        }
        return Optional.empty();
    }

    public UserSystemEntity findById(Long user) {
        return userRepository.findById(user).orElseThrow(() -> new NotFoundException("NOT_FOUND", "Usuário não encontrado!"));
    }
}

