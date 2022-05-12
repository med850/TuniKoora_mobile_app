/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Produit;
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

/**
 *
 * @author CHAOUCH KHALIL
 */
public class ProduitController {
    public ArrayList<Produit> produit;
        public static ProduitController instance = null;
    public static boolean resultOK;
    
    public static boolean resOk = true;
    
    private ConnectionRequest req;
    
    
    public static ProduitController getInstance(){
        
        if(instance == null){
            
            instance = new ProduitController();
          
        }
        
         return instance;
    }
    
    
    public ProduitController(){
        
        req = new ConnectionRequest();
        
        
    }
    
    public void register(TextField nom, TextField prix, TextField qte, TextField description, TextField image, Resources res){
        
        String url = Connexion.BASE_URL + "/api/add/product?nom=" + nom.getText().toString() + "&prix=" + prix.getText().toString() + 
                "&qte=" + qte.getText().toString() + "&description=" + description.getText().toString() + "&image=" + image.getText().toString() ;
        
                
        req.setUrl(url);
        
        if(nom.getText().equals("") && prix.getText().equals("") && qte.getText().equals("") && description.getText().equals("") && image.getText().equals("") 
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
    
//      public ArrayList<Produit> parseProduits(String jsonText){
//        try {
//            produit= new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//           List< Map<String,Object>> list =(List< Map<String,Object>>) ProduitListJson.get("root");
//           for ( Map<String,Object> obj: list){
//             Produit p = new Produit();
//             float id = Float.parseFloat(obj.get("id_produit").toString());
//            // float vu = Float.parseFloat(obj.get("nbr_vu").toString());
//             float qte = Float.parseFloat(obj.get("Qte_produit").toString());
//
//             p.setId((int)id);
//             p.setNom(obj.get("nom_produit").toString());
//             p.setDescription(obj.get("description").toString());
//             p.setPrix((int) obj.get("prix_produit"));
//          //   p.setNbr_vu((int) vu);
//             p.setQte((int) qte);
//             produit.add(p);
//         
//        } }
//           catch (IOException ex) {
////            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
//             
//        }
//          return produit;
// }
//     public ArrayList<Produit> getAllOeuvres(){
//           String url = Connexion.BASE_URL + "/api/products";
//        req.setUrl(url);
//        req.setPost(false);
//
//                System.out.println(url);
//
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                produit = parseProduits(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return produit;
//    }
  
    
    
    
    
    
    
    
    
//    public ArrayList<Produit> parsep(String jsonText){
//        try {
//            produit=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = 
//               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            for(Map<String,Object> obj : list){
//                Produit p = new Produit();
//
//                float id = Float.parseFloat(obj.get("idp").toString());
//                p.setId((int)id);
//               p.setNom(obj.get("nomp").toString());
//               float prix = Float.parseFloat(obj.get("quantitep").toString());
//               p.setPrix((int)prix);
//               float qte = Float.parseFloat(obj.get("quantitep").toString());
//               p.setQte((int)qte);
//                p.setDescription(obj.get("descp").toString());
//                p.setImage(obj.get("dispop").toString());
////                  p.setCouleurP(obj.get("couleurp").toString());
////                 float quantiteP = Float.parseFloat(obj.get("quantitep").toString());
////                p.setQuantiteP((int)quantiteP);
////                  p.setTailleP(obj.get("taillep").toString());
//
//                produit.add(p);
//        System.out.println(p);
//
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//        return produit;
//    }
//     public ArrayList<Produit> getAllProduit(){
//        //String url = Statics.BASE_URL+"/tasks/";
//        req=new ConnectionRequest();
//        String url = Connexion.BASE_URL + "/api/products";
//        System.out.println(url);
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                produit = parsep(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return produit;
//    }

    
    
    
    
    
    public ArrayList<Produit> parseEquipes(String jsonText){
        try {
            produit=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> menusListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");
            for(Map<String,Object> obj : list){
                Produit m = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                m.setId((int)id);
                m.setNom(obj.get("nom").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                m.setPrix((int)prix);
                float qte = Float.parseFloat(obj.get("qte").toString());
                m.setQte((int)qte);
                m.setDescription(obj.get("description").toString());
                m.setImage(obj.get("image").toString());
                
                                

                produit.add(m);
            }
            
            
        } catch (IOException ex) {
            
        }
        return produit;
    }
    
    public ArrayList<Produit> getAllEquipes(){
        req=new ConnectionRequest();
        String url = Connexion.BASE_URL+ "/api/products";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produit = parseEquipes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produit;
    }
    
    
    
//     
    
    
    
    
    
    
    
    
//     public ArrayList<Produit>afficheUsers(){
//         
//          ArrayList<Produit>res = new ArrayList<>();
//         
//          String url = Connexion.BASE_URL + "/api/products";
//          req.setUrl(url);
//         
//        req.addResponseListener(new ActionListener<NetworkEvent>(){
//              @Override
//              public void actionPerformed(NetworkEvent evt) {
//               
//                   JSONParser jsonp ;
//                jsonp = new JSONParser();
//               
//                 try {
//                    Map<String,Object>mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                   
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapUsers.get("root");
//                   
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Produit re = new Produit();
//                       
//                       
//                       
//                        float id = Float.parseFloat(obj.get("id").toString());
//                       
//                        //int nom = Float.parseFloat(obj.get("nom").toString());
//                       
//                        String nom = obj.get("nom").toString();
//                       float prix = Float.parseFloat(obj.get("prix").toString());
//                       float qte = Float.parseFloat(obj.get("qte").toString());
//                        String description = obj.get("description").toString();
//                       
//                         //float tel = Float.parseFloat(obj.get("tel").toString());
//                       
//                        String image = obj.get("image").toString();
//                     
//                       
//                        re.setId((int)id);
//                        
//                        re.setNom(nom);
//                        re.setPrix((int)prix);
//                        re.setQte((int)qte);
//                        re.setDescription(description);
//                        //re.setCin((int)tel);
//                        re.setImage(image);
//                       
//                       
//                    }
//              }catch(Exception ex) {
//                   
//                    ex.printStackTrace();
//                }
//           
//           
//           
//              }
//           
//        });
//         
//            NetworkManager.getInstance().addToQueueAndWait(req);
//
//        return res;
//      }

     
      

    
    
    
    
    
    
    
    
    
    
    
    
    
          public boolean delete(int id) {
       String url = Connexion.BASE_URL + "/deleteeEquipeJSON/"+id;
    
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
