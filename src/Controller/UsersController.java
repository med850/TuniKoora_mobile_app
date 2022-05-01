/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utils.Connexion;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Map;


/**
 *
 * @author gold
 */
public class UsersController {
    
    public static UsersController instance = null;
    
    
    public static boolean resOk = true;
    
    private ConnectionRequest req;
    
    public static UsersController getInstance(){
        
        if(instance == null){
            
            instance = new UsersController();
          
        }
        
         return instance;
    }
    
    
    public UsersController(){
        
        req = new ConnectionRequest();
        
        
    }
    
    public void register(TextField cin, TextField username, TextField prenom, TextField tel, TextField email, TextField password, TextField repeatPassword, Resources res){
        
        String url = Connexion.BASE_URL + "/api/user/inscription?cin=" + cin.getText().toString() + "&username=" + username.getText().toString() + 
                "&prenom=" + prenom.getText().toString() + "&tel=" + tel.getText().toString() + "&email=" + email.getText().toString() + 
                "&password=" + password.getText().toString() + "&repeatpassword=" + repeatPassword.getText().toString();
        
                
        req.setUrl(url);
        
        if(cin.getText().equals("") && username.getText().equals("") && prenom.getText().equals("") && tel.getText().equals("") && email.getText().equals("") && password.getText().equals("")
                && repeatPassword.getText().equals("")){
            
                
            Dialog.show("Erreur", "Il faut remplir touts les champs", "OK", null);
           
        }
        
        req.addResponseListener((e)-> {
            
            byte[]data = (byte[])e.getMetaData();
            
            String responseData = new String(data);
            
            System.out.println("data ===>" +responseData);
            
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    
    
    
    
    
    
      public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Connexion.BASE_URL+"/api/login?username="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                           
                
//               if(user.size() >0 ) // l9a user
//                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
//                    new AjoutReclamationForm(rs).show();
//                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    
    
}
