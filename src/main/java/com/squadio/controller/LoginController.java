package com.squadio.controller;

import com.squadio.entity.User;
import com.squadio.service.UserService;
import com.squadio.util.JWTUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    @ManagedProperty(value = "#{userServiceImpl}")
    protected UserService userService;
    private User loginUser;

    @PostConstruct
    public void init() {
        loginUser = new User();
    }

    public String login() {
//        User user = userService.login(loginUser.getUserName(), loginUser.getPassword());

        if ("demo".equals(loginUser.getUserName()) && "demo".equals(loginUser.getPassword())) {
            // Generate JWT token
            String jwtToken = JWTUtil.generateJWTToken(loginUser.getUserName());

            // Store token in session
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("token", jwtToken);

            return "home.xhtml"; // Redirect to home page
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid username or password"));
            return ""; // Stay on the same page
        }
    }

    public String verifyToken() {
        if (JWTUtil.verifyToken()) {
            return "success.xhtml";
        }else{
            return "error.xhtml";
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
