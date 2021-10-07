package com.works.services;

import com.works.entities.Logger;
import com.works.entities.Role;
import com.works.entities.Users;
import com.works.repositories.LogRepository;
import com.works.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends SimpleUrlLogoutSuccessHandler implements UserDetailsService, LogoutSuccessHandler {

    final UserRepository uRepo;
    final LogRepository lRepo;
    public UserService(UserRepository uRepo, LogRepository lRepo) {
        this.uRepo = uRepo;
        this.lRepo = lRepo;
    }


    // security login
    @Override
    public UserDetails loadUserByUsername( String email ) {
        UserDetails userDetails = null;
        Optional<Users> oUser = uRepo.findByUseremailEqualsIgnoreCaseAllIgnoreCase(email);
        if ( oUser.isPresent() ) {
            Users us = oUser.get();
            userDetails = new org.springframework.security.core.userdetails.User(
                    us.getUseremail(),
                    us.getPassword(),
                    us.isEnabled(),
                    us.isTokenExpired(),
                    true,
                    true,
                    getAuthorities( us.getRoles() ));
        }else {
            throw new UsernameNotFoundException("Kullanıcı adı yada şifre hatalı");
        }
        return userDetails;
    }


    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority( role.getName() ));
        }
        return authorities;
    }


    public Users register( Users us ) throws AuthenticationException {

        Optional<Users> uOpt = uRepo.findByUseremailEqualsIgnoreCaseAllIgnoreCase(us.getUseremail());
        if ( uOpt.isPresent() ) {
            throw new AuthenticationException("Bu kullanıcı daha önce kayıtlı!");
        }
        us.setPassword( encoder().encode( us.getPassword() ) );
        return uRepo.save(us);
    }


    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("onLogoutSuccess Call ");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
    }


    // user info
    public void info(HttpServletRequest req, HttpServletResponse res, Logger logger) throws IOException {
        String email ="";
        try{
            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            email = aut.getName(); // username
        }catch (Exception e){
        }
        if (email != null) {
            System.out.println(email);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        System.out.println("ip " + ip);

        String session = req.getSession().getId();
        System.out.println("session :" + session);

        Optional<Users> user = uRepo.findByUseremailEqualsIgnoreCaseAllIgnoreCase(email);
        if (user.isPresent()) {
            logger.setLname(user.get().getUsername());
            logger.setLsurname(user.get().getUsersurname());
            String roles = "";
            for (Role item : user.get().getRoles()) {
                roles += item.getName() + ", ";
            }
            if (roles.length() > 0) {
                roles = roles.substring(0, roles.length() - 2);
            }
            logger.setLroles(roles);
        }

        logger.setLemail(email);
        logger.setLsessionId(session);
        logger.setLIp(ip);

        logger.setLUrl(req.getRequestURI());
        logger.setLDate(LocalDateTime.now());

        lRepo.save(logger);

        System.out.println(logger);
    }



}

