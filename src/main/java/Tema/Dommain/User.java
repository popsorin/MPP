package Tema.Dommain;

public class User {
    private String user;
    private  String pasword;

    public User(String u,String p){
       user=u;
       pasword=p;
    }

    public String getUser() {
        return user;
    }

    public String getPasword() {
        return pasword;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }


}
