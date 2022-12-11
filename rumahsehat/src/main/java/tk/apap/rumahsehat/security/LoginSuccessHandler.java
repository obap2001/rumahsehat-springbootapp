package tk.apap.rumahsehat.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.service.UserService;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        UserModel user = userService.getUserByUsername(username);

        String redirectURL = request.getContextPath();

        if (user.getRole().equals("pasien")) {
            redirectURL = "/pasien/forbidden";
        } else {
            redirectURL = "/";
        }

        response.sendRedirect(redirectURL);

    }

}
