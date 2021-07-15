package ru.itis.aivar.comics.app.security.oauth.github.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.aivar.comics.app.dto.GitHubUserDto;
import ru.itis.aivar.comics.app.models.User;
import ru.itis.aivar.comics.app.repositories.RoleRepository;
import ru.itis.aivar.comics.app.repositories.UserRepository;
import ru.itis.aivar.comics.app.security.details.UserDetailsImpl;
import ru.itis.aivar.comics.app.services.OauthService;

import java.util.Collections;

@Component
public class GitHubAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("gitHubAuthUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private OauthService oauthService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GitHubCodeAuthentication codeAuthentication = (GitHubCodeAuthentication) authentication;

        String code = codeAuthentication.getCode();

        GitHubUserDto userDto = oauthService.getGitHubUser(code);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());

        User user;
        if (userDetails == null) {
            user = User.builder()
                    .hashPassword(passwordEncoder.encode("OAuthGitHub"))
                    .roles(Collections.singleton(roleRepository.findRoleByName("ROLE_USER")))
                    .state(User.State.ACTIVE)
                    .build();
        } else {
            user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
            if (user == null){
                throw new GitHubAuthenticationException("Something goes wrong...");
            }
        }
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        user = userRepository.save(user);

        codeAuthentication.setUserDetails(new UserDetailsImpl(user));
        codeAuthentication.setAuthenticated(true);

        return codeAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GitHubCodeAuthentication.class.equals(aClass);
    }
}
