/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Entities.Article;
import Utils.Connexion;
import Views.afficherArticle;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CHAOUCH KHALIL
 */
public class ArticleController {
    public ArrayList<Article> articles;
        public static ArticleController instance = null;
    public static boolean resultOK;
    
    public static boolean resOk = true;
    
    private ConnectionRequest req;
    
    
    public static ArticleController getInstance(){
        
        if(instance == null){
            
            instance = new ArticleController();
          
        }
        
         return instance;
    }
    
    
    public ArticleController(){
        
        req = new ConnectionRequest();
        
        
    }
    
    public void register(TextField titre, TextField description, Resources res){
        
        String url = Connexion.BASE_URL + "/api/addArticle?titre=" + titre.getText().toString() + "&description=" + description.getText().toString()  
                 ;
        
                
        req.setUrl(url);
        
        if(titre.getText().equals("") && description.getText().equals("") 
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
   
    
    
    
    
    
    public ArrayList<Article> parseArticle(String jsonText){
        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> menusListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");
            for(Map<String,Object> obj : list){
                Article m = new Article();
                float id = Float.parseFloat(obj.get("id").toString());
                m.setId((int)id);
                m.setTitre(obj.get("titre").toString());
                m.setDescription(obj.get("description").toString());             
                                

                articles.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return articles;
    }
    
    public ArrayList<Article> getAllArticle(){
        req=new ConnectionRequest();
        String url = Connexion.BASE_URL+ "/showArticle";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticle(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
    
   
    
          public boolean delete(int id) {
       String url = Connexion.BASE_URL + "/deleteArticle/"+id;
    
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
          
          
          
          
         
}