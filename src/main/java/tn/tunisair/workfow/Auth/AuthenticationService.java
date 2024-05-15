package tn.tunisair.workfow.Auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.tunisair.workfow.Email.EmailService;
import tn.tunisair.workfow.Email.EmailTemplateName;
import tn.tunisair.workfow.Entities.Token;
import tn.tunisair.workfow.Entities.User;
import tn.tunisair.workfow.Repositories.RoleRepository;
import tn.tunisair.workfow.Repositories.TokenRepository;
import tn.tunisair.workfow.Repositories.UserRepository;
import tn.tunisair.workfow.Security.JwtService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        try {
            var userRole = roleRepository.findByName("USER").orElseThrow(() ->
                    new IllegalStateException("ROLE USER was not initiated"));
            var user = tn.tunisair.workfow.Entities.User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .accountLocked(false)
                    .enabled(false)
                    .roles(List.of(userRole))
                    .build();
            userRepository.save(user);
            sendValidationEmail(user);
        } catch (Exception ex) {
            // Log or handle the exception appropriately
            ex.printStackTrace();
            throw new MessagingException("Error occurred during user registration", ex);
        }
    }

    private void sendValidationEmail(User user) throws MessagingException {
        try {
            var newToken = generateAndSaveActivationToken(user);
            emailService.sendEmail(
                    user.getEmail(),
                    user.fullName(),
                    EmailTemplateName.ACTIVATE_ACCOUNT,
                    activationUrl,
                    newToken,
                    "Account activation"
            );
        } catch (Exception ex) {
            // Log or handle the exception appropriately
            ex.printStackTrace();
            throw new MessagingException("Error occurred while sending validation email", ex);
        }
    }

    private String generateAndSaveActivationToken(User user) {
        try {
            String generatedToken = generateActivationCode(6);
            var token = Token.builder()
                    .token(generatedToken)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(LocalDateTime.now().plusMinutes(15))
                    .user(user)
                    .build();
            tokenRepository.save(token);
            return generatedToken;
        } catch (Exception ex) {
            // Log or handle the exception appropriately
            ex.printStackTrace();
            throw ex;
        }
    }

    private String generateActivationCode(int length) {
        try {
            String characters = "0123456789";
            StringBuilder codeBuilder = new StringBuilder();
            SecureRandom secureRandom = new SecureRandom();
            for (int i = 0; i < length; i++) {
                int randomIndex = secureRandom.nextInt(characters.length());
                codeBuilder.append(characters.charAt(randomIndex));
            }
            return codeBuilder.toString();
        } catch (Exception ex) {
            // Log or handle the exception appropriately
            ex.printStackTrace();
            throw ex;
        }
    }
}
