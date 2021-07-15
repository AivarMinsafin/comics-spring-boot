package ru.itis.aivar.comics.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.aivar.comics.app.dto.forms.UserSignUpForm;
import ru.itis.aivar.comics.app.exceptions.NotFoundException;
import ru.itis.aivar.comics.app.models.User;
import ru.itis.aivar.comics.app.repositories.RoleRepository;
import ru.itis.aivar.comics.app.repositories.UserRepository;
import ru.itis.aivar.comics.app.utils.email.EmailUtil;
import ru.itis.aivar.comics.app.utils.email.MailsGenerator;

import java.util.Collections;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;
    private MailsGenerator mailsGenerator;
    private EmailUtil emailUtil;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.confirmation.subject}")
    private String subject;

    @Value("${spring.mail.username}")
    private String from;

    public SignUpServiceImpl(UserRepository userRepository, MailsGenerator mailsGenerator, EmailUtil emailUtil, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailsGenerator = mailsGenerator;
        this.emailUtil = emailUtil;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void signUp(UserSignUpForm form) {
        User newUser = User.builder()
                .email(form.getEmail())
                .username(form.getUsername())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .roles(Collections.singleton(
                        roleRepository.findRoleByName("ROLE_USER")
                ))
                .confirmCode(UUID.randomUUID().toString())
                .state(User.State.ACTIVE)
                .build();

        userRepository.save(newUser);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());
        emailUtil.sendMail(newUser.getEmail(), subject, from, confirmMail);
    }

    @Override
    public void confirm(String confirmCode) {
        User user = userRepository.findByConfirmCode(confirmCode).orElseThrow(NotFoundException::new);
        if (user.getState() == User.State.NOT_CONFIRMED){
            user.setState(User.State.ACTIVE);
        }
        userRepository.save(user);
    }
}
