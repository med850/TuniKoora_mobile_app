/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import com.codename1.io.Preferences;

/**
 *
 * @author gold
 */
public class SessionManager {
    
        public static Preferences pref ; 
    
    
    
   
    private static int id ; 
    private static int cin ; 
    private static String userName ; 
    private static String Prenom ; 
    private static int tel ; 
    private static String email; 
    private static String passowrd ;
   

    public static Preferences getPref() {
        return pref;
    }
    
    
     public static int getId() {
        return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id",id);
    }
    
    
       public static int getCin() {
        return pref.get("cin",cin);
    }

    public static void setCin(int cin) {
        pref.set("cin",cin);
    }
    
    
    
       public static int getTel() {
        return pref.get("tel",tel);
    }

    public static void setTel(int tel) {
        pref.set("tel",tel);
    }
    
    
      public static String getUserName() {
        return pref.get("username",userName);
    }

    public static void setUserName(String userName) {
         pref.set("username",userName);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }
    
    
      public static String getPassword() {
        return pref.get("password",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }
    
    
    
       public static String getPrenom() {
        return pref.get("Prenom",Prenom);
    }

    public static void setPrenom(String Prenom) {
         pref.set("Prenom",Prenom);
    }
    
    
    
}
