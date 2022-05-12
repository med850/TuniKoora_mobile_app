/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entities.Users;
import Utils.Connexion;
import Views.AjouterUsers;
import Views.Home;
import Views.SessionManager;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
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
 * @author gold
 */
public class UsersController {
    
    public ArrayList<Users>us;
    
     private static final String url1 = "http://localhost/api/user/listes";
    
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
    
    
    
    
    
    
    
    
      public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Connexion.BASE_URL+"/api/login?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false);
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
                
                           
                    //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
            //    SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                
                
                System.out.println("Email d'utilisateur connecté :"+SessionManager.getEmail());
                System.out.println("Username d'utilisateur connecté :"+SessionManager.getUserName());
                
                
                
                
                
                
                
//               if(user.size() >0 ) // l9a user
//                 
//                    new Home(rs);
//                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
      
      
      
     

    
//    public ArrayList<Users> getAllPublication() {
//        String url = Connexion.BASE_URL + "/api/user/listes";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//
//               
//                try {
//                    us = parseUsers(new String(req.getResponseData()));
//                } catch (ParseException ex) {
//                   
//                }
//                
//
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return us;
//    }
//
//      
           
    
//      public ArrayList<Users>afficheUsers(){
//          
//          ArrayList<Users>res = new ArrayList<>();
//          
//         // String url = Connexion.BASE_URL + "/api/user/listes";
//        
//       //  req.setPost(false);
//        // req.addArgument("format", "json");
//
//          req.setUrl(url1);
//          
//        req.addResponseListener(new ActionListener<NetworkEvent>(){
//              @Override
//              public void actionPerformed(NetworkEvent evt) {
//                
//                   JSONParser jsonp ;
//                jsonp = new JSONParser();
//                
//                 try {
//                     Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//
//                   // Map<String,Object>mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
//                    
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Users re = new Users();
//                        
//                        
//                        
//                        float id = Float.parseFloat(obj.get("id").toString());
//                        
//                        float cin = Float.parseFloat(obj.get("cin").toString());
//                        
//                        String username = obj.get("username").toString();
//                        
//                        String prenom = obj.get("prenom").toString();
//                        
//                         float tel = Float.parseFloat(obj.get("tel").toString());
//                        
//                        String email = obj.get("email").toString();
//                     
//                        
//                        re.setId((int)id);
//                        re.setCin((int)cin);
//                        re.setUsername(username);
//                        re.setPrenom(prenom);
//                        re.setCin((int)tel);
//                        re.setEmail(email);
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
//      
//      

//            public ArrayList<Users>parseUsers(String jsonText) throws IOException{
//
//                users = new ArrayList<>();
//
//                JSONParser j = new JSONParser();
//                Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//                List <Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
//
//                for(Map<String,Object> obj : list){
//
//                    Users u = new Users();
//                    float id  = Float.parseFloat(obj.get("id").toString());
//
//                    u.setId((int)id);
//                    u.setCin(((int)Float.parseFloat(obj.get("cin").toString())));
//                    u.setUsername(obj.get("username").toString());
//                    u.setPrenom(obj.get("prenom").toString());
//                    u.setTel(((int)Float.parseFloat(obj.get("tel").toString())));
//                    u.setEmail(obj.get("email").toString());
//
//
//                }
//
//                return users;
//            }
//
//
//
//
//            public ArrayList<Users>getAllUsers(){
//
//                String url = Connexion.BASE_URL +"/api/user/listes";
//                req.setUrl(url);
//                req.setPost(false);
//                req.addResponseListener (new ActionListener<NetworkEvent>(){
//                    @Override
//                    public void actionPerformed(NetworkEvent evt) {
//
//
//                        try {
//                            users = parseUsers(new String(req.getResponseData()));
//                        } catch (IOException ex) {
//                            Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        req.removeResponseListener(this);
//                    }
//
//      });
//          
//          NetworkManager.getInstance().addToQueueAndWait(req);
//          return users;
//      }
////      
//      
//      
//      public ArrayList<Users> parseEvents(String jsonText){
//        try {
//            us=new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            
//             
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
//            
//            //Parcourir la liste des tâches Json
//            for(Map<String,Object> obj : list){
//                //Création des tâches et récupération de leurs données
//                Users u = new Users();
//                float id = Float.parseFloat(obj.get("id").toString());
//                u.setId((int)id);
//               // u.setUsername(obj.get("username").toString());
//              //  u.setPassword(obj.get("password").toString());
//              //  u.setVerifpassword(obj.get("verifpassword").toString()); 
//                u.setEmail(obj.get("email").toString()); 
//              //  u.setFullname(obj.get("fullname").toString()); 
//                us.add(u);
//            }
//            
//            
//        } catch (IOException ex) {
//            
//        }
//         /*
//            A ce niveau on a pu récupérer une liste des tâches à partir
//        de la base de données à travers un service web
//        
//        */
//        return us;
//    }
    
//    public ArrayList<Users> getAllUsers(){
//         String url = Connexion.BASE_URL +"/api/user/listes";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                users = parseEvents(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return users;
//    }
//      
   public boolean deleteUsers(int id) {
        String url = Connexion.BASE_URL + "/deleteUsersJSON/"+id;
     
        req.setUrl(url);
 
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 resOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                 req.removeResponseListener(this);
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resOk;
     }
         
       
      
       
           
           
           
//               public ArrayList<Users>affichageReclamations() {
//        ArrayList<Users> result = new ArrayList<>();
//        
//        
//        //  String url = "http://127.0.0.1:8000/api/user/listes";
//
//        String url = Connexion.BASE_URL + "/api/user/listes";
//          req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp ;
//                jsonp = new JSONParser();
//                
//                try {
//                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    
//                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
//                    
//                    for(Map<String, Object> obj : listOfMaps) {
//                        Users re = new Users();
//                        
//                        //dima id fi codename one float 5outhouha
//                         
//                        
//                        float id = Float.parseFloat(obj.get("id").toString());
//                        
//                        float cin = Float.parseFloat(obj.get("cin").toString());
//                        
//                        String username = obj.get("username").toString();
//                        
//                        String prenom = obj.get("prenom").toString();
//                        
//                         float tel = Float.parseFloat(obj.get("tel").toString());
//                        
//                        String email = obj.get("email").toString();
//                     
//                        
//                        re.setId((int)id);
//                        re.setCin((int)cin);
//                        re.setUsername(username);
//                        re.setPrenom(prenom);
//                        re.setCin((int)tel);
//                        re.setEmail(email);
////                        
//                        
//                        //Date 
////                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
////                        
////                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
////                        
////                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////                        String dateString = formatter.format(currentTime);
////                        re.setDate(dateString);
//                        
//                        //insert data into ArrayList result
//                        result.add(re);
//                       
//                    
//                    }
//                    
//                }catch(Exception ex) {
//                    
//                    ex.printStackTrace();
//                }
//            
//            }
//        });
//        
//      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
//
//        return result;
//        
//        
//    }
//    
    
         
         public ArrayList<Users> parseUsers(String jsonText){
        try {
            us=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> menusListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");
            for(Map<String,Object> obj : list){
                Users m = new Users();
               // float id = Float.parseFloat(obj.get("id").toString());
                float cin = Float.parseFloat(obj.get("cin").toString());
                 m.setCin((int)cin);
                  m.setUsername(obj.get("username").toString());
                   m.setPrenom(obj.get("prenom").toString());
                
//                float username = Float.parseFloat(obj.get("username").toString());
//                float prenom = Float.parseFloat(obj.get("prenom").toString());
                float tel = Float.parseFloat(obj.get("tel").toString());
                m.setTel((int)tel);
               // float email = Float.parseFloat(obj.get("email").toString());
                m.setPrenom(obj.get("email").toString());



             //    m.setId((int)id);
                
                 
                
                  
                


                us.add(m);
                
                
                
                
                
                

               // m.setId((int)id);
//                m.setNom(obj.get("nom").toString());
              
            }
            
            
        } catch (IOException ex) {
            
        }
        return us;
    }
    
    public ArrayList<Users> getAllUsers(){
        req=new ConnectionRequest();
        String url = Connexion.BASE_URL+"/showUsersJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                us = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return us;
    }
         
         
    
//    
//       public ArrayList<Users> parseUsers(String jsonText) throws ParseException  {
//
//        try {
//
//            us = new ArrayList<>();
//
//            JSONParser j = new JSONParser();
//            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
//            for (Map<String, Object> obj : list) {
//                Users users = new Users();
//                         
//
//                
//                //LocalDate datepub1 = LocalDate.parse(str2);
//               // Date datepub1 = Date.valueOf(str2);
//                float id = Float.parseFloat(obj.get("id").toString());                        
//                users.setId((int)id); 
//                // users.setCin((int)cin); 
//               // Date datepub1 = formater.parse(str2);
//               // Pubs.setDate_Pub(datepub1);
//                users.setUsername(obj.get("username").toString());
//                users.setPrenom(obj.get("prenom").toString());
//                // users.setEmail(obj.get("email").toString());
//                users.setEmail(obj.get("email").toString());
//               
//
//
//                us.add(users);
//            }
//
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//
//        return us;
//    }
//         
         
         
}
