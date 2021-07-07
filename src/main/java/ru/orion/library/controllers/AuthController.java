package ru.orion.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.orion.library.enums.AccountRole;
import ru.orion.library.forms.AccountForm;
import ru.orion.library.forms.LoginForm;
import ru.orion.library.models.Account;
import ru.orion.library.repositories.AccountRepository;
import ru.orion.library.security.details.UserDetailsImpl;
import ru.orion.library.security.provider.JwtProvider;
import ru.orion.library.services.AccountService;
import ru.orion.library.transfer.JwtTokenDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

/*    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm form){
        return ResponseEntity.ok(loginService.login(form));
    }*/
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    @Autowired               //TODO ПЕРЕНЕСТИ НА УРОВЕНЬ СЕРВИСОВ
    private AccountRepository accountRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginForm form) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getLogin(), form.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtProvider.generateJwtToken(userDetails.getUsername());

        AccountRole roles = (AccountRole) userDetails.getAuthorities().toArray()[0];

        return ResponseEntity.ok(JwtTokenDto
                .builder()
                .token(jwt)
                .id(userDetails.getAccount().getId())
                .login(userDetails.getUsername())
                .role(roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody AccountForm form) {
        if (accountRepository.existsByLogin(form.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        // Create new user's account
       accountService.singUp(form);
       return ResponseEntity.ok("User registered successfully!");


       /* if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }*/

    }

}
