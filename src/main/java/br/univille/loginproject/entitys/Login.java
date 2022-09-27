package br.univille.loginproject.entitys;

public class Login {
    
    private String user;
    private String password;
    
    public String getUser() {
        return user;
    }
    public void setUser(String user) {

        if (!user.equals(null) || !user.equals(""))
            this.user = user;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        
        if(!password.equals(null) || !password.equals(""))
            this.password = password;
    }
}
