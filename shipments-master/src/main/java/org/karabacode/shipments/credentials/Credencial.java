package org.karabacode.shipments.credentials;

public class Credencial {
    private String username;
    private String password;

    public Credencial() {

    }

    public Credencial(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credencial [username=" + username + ", password=" + password + "]";
    }
}
