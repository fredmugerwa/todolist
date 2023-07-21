package com.company.views;

import com.company.constant.Hyperlink;
import com.company.models.User;
import com.company.services.UserService;
import com.company.services.impl.UserServiceImpl;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@RequestScoped
public class LoginBean {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        UserService loginService = new UserServiceImpl();
        User user = loginService.loginUser(username, password);

        if (user != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            externalContext.getSessionMap().put("currentUser", user);
            return Hyperlink.TODO_VIEW;
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null));
            return "";
        }
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getSessionMap().put("currentUser", null);
        externalContext.redirect(externalContext.getRequestContextPath() + Hyperlink.LOGIN_VIEW);
    }
}