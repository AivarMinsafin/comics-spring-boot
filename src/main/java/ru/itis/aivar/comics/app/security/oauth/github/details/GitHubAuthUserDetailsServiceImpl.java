package ru.itis.aivar.comics.app.security.oauth.github.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.aivar.comics.app.models.User;
import ru.itis.aivar.comics.app.repositories.UserRepository;
import ru.itis.aivar.comics.app.security.details.UserDetailsImpl;

@Service("gitHubAuthUserDetailsService")
public class GitHubAuthUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).orElse(null);
        return user != null ? new UserDetailsImpl(user) : null;
    }
}
