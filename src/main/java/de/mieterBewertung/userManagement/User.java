package de.mieterBewertung.userManagement;

import de.mieterBewertung.product.Product;

import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private String session;
    private List<Product> productList;

    public User(String username, String password, String email, Role role, String session) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", session='" + session + '\'' +
                '}';
    }
}
