/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author aroua
 */


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
 * @author CHAOUCH KHALIL
 */
public class JoueurController {
    
     public static JoueurController instance = null;
    
    
    public static boolean resOk = true;
    
    private ConnectionRequest req;
    
    public static JoueurController getInstance(){
        
        if(instance == null){
            
            instance = new JoueurController();
          
        }
        
         return instance;
    }
    
    
    public JoueurController(){
        
        req = new ConnectionRequest();
        
        
    }
    
    public void register(TextField nom, TextField prenom, TextField poste, TextField tel,TextField nb_but, TextField image, Resources res){
        
        String url = Connexion.BASE_URL + "/api/add/joueur?nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + 
                "&poste=" + poste.getText().toString() + "&tel=" + tel.getText().toString()  + "&nb_but=" + tel.getText().toString() + "&image=" + image.getText().toString() ;
        
                
        req.setUrl(url);
        
        if(nom.getText().equals("") && prenom.getText().equals("") && poste.getText().equals("") && tel.getText().equals("")  && nb_but.getText().equals("") && image.getText().equals("") 
               ){
            
                
            Dialog.show("Erreur", "Il faut remplir touts les champs", "OK", null);
           
        }
        
        req.addResponseListener((e)-> {
            
            byte[]data = (byte[])e.getMetaData();
            
            String responseData = new String(data);
            
            System.out.println("data ===>" +responseData);
            
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }    
    /*
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
    }*/
    
    
    
    
   
    
    
    
    
    
}

