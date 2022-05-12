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
    

import Entities.Equipe;
import Utils.Connexion;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EquipeController {
        public ArrayList<Equipe> equipes;
    public static boolean resultOK;

     public static EquipeController instance = null;
    
    
    public static boolean resOk = true;
    
    private ConnectionRequest req;
    
    public static EquipeController getInstance(){
        
        if(instance == null){
            
            instance = new EquipeController();
          
        }
        
         return instance;
    }
    
    
    public EquipeController(){
        
        req = new ConnectionRequest();
        
        
    }
    
    public void register(TextField nom, TextField classement,  Resources res){
        
        String url = Connexion.BASE_URL + "/api/add/equipe?nom=" + nom.getText().toString() + "&classement=" + classement.getText().toString()  ;
        
                
        req.setUrl(url);
        
        if(nom.getText().equals("") && classement.getText().equals("")  
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
     public ArrayList<Equipe> parseEquipes(String jsonText){
        try {
            equipes=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> menusListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");
            for(Map<String,Object> obj : list){
                Equipe m = new Equipe();
                float id = Float.parseFloat(obj.get("id").toString());
                float classement = Float.parseFloat(obj.get("classement").toString());

                m.setId((int)id);
                m.setNom(obj.get("nom").toString());
                m.setClassement((int)classement);

                equipes.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return equipes;
    }
    
    public ArrayList<Equipe> getAllEquipes(){
        req=new ConnectionRequest();
        String url = Connexion.BASE_URL+"/showEquipeJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                equipes = parseEquipes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return equipes;
    }
    public boolean updateEquipe(Equipe m) {
        System.out.println(m);
        System.out.println("********");
       String url = Connexion.BASE_URL + "/updateEquipeJSON/"+m.getId()+"?nom=" + m.getNom() + "&classement=" + m.getClassement();
       //String url = Statics.BASE_URL + "create";
    
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
    public boolean deleteEquipe(int id) {
       String url = Connexion.BASE_URL + "/deleteEquipeJSON/"+id;
    
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

