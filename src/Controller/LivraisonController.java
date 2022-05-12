/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Livraison;
import Controller.LivraisonController;
import Utils.Connexion;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.events.ActionListener ;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 *
 * @author 
 */
public class LivraisonController {
    public ArrayList<Livraison> livraisons;
    public static boolean resultOK;

    
     public static LivraisonController instance = null;
    
    
    public static boolean resOk = true;
    
    private ConnectionRequest req;
    
    public static LivraisonController getInstance(){
        
        if(instance == null){
            
            instance = new LivraisonController();
          
        }
        
         return instance;
    }
    
    
    public LivraisonController(){
        
        req = new ConnectionRequest();
        
        
    }
    
    public ArrayList<Livraison> parseLivraisons(String jsonText){
        try {
            livraisons=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> menusListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");
            for(Map<String,Object> obj : list){
                Livraison m = new Livraison();
                float id = Float.parseFloat(obj.get("id").toString());
                

                m.setId((int)id);
                m.setRef(obj.get("ref").toString());
                m.setLocalisation(obj.get("localisation").toString());

                livraisons.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return livraisons;
    }
    
    public ArrayList<Livraison> getAllLivraisons(){
        req=new ConnectionRequest();
        String url = Connexion.BASE_URL+"/showLivraisonJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                livraisons = parseLivraisons(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return livraisons;
    }

    public boolean deleteLivraison(int id) {
        String url = Connexion.BASE_URL + "/deleteLivraisonJSON/"+id;
     
        req.setUrl(url);
 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultOK;
     }

    
    public void register(TextField ref, TextField localisation, TextField etat,  Resources res){
        
        String url = Connexion.BASE_URL + "/api/add/livraison?ref=" + ref.getText().toString() + "&localisation=" + localisation.getText().toString() + 
                "&etat=" + etat.getText().toString()  ;
        
                
        req.setUrl(url);
        
        if(ref.getText().equals("") && localisation.getText().equals("") && etat.getText().equals("")
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
