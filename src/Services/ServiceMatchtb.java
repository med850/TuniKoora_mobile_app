/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Matchtb;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Preferences;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author arfao
 */
public class ServiceMatchtb {
     
 public ArrayList<Matchtb> Matchtb;

    public static ServiceMatchtb instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceMatchtb() {
        req = new ConnectionRequest();
    }

    public static ServiceMatchtb getInstance() {
        if (instance == null) {
            instance = new ServiceMatchtb();
        }
        return instance;
    }

    public boolean addMatchtb(Matchtb f) {

        Preferences UserId = null;
        String UserSessionId = UserId.get("UserId", null);

        String url = "http://localhost:8000/api/matchtb";
        req.setUrl(url);
        req.setPost(true);
        req.setHttpMethod("POST");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateMatchtb(Matchtb f) {
        String url = "http://localhost:8000/api/matchtb";
        req.setUrl(url);
        req.setHttpMethod("PUT");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean RemoveMatchtb(Matchtb f) {
       // String url = Statics.BASE_URL + "/reclamation/feedback/rem/" + f.getId();
       String url = "http://localhost:8000/api/matchtb";
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Matchtb> parseMatchtb(String jsonText) {
        try {
            Matchtb = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> MatchtbListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Map<String,Object> menusListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");  
            
            for (Map<String,Object> obj: list ) {

                Matchtb f = new Matchtb();
                f.setArbitreprincipale((String)obj.get("arbitrePrincipale"));
                f.setLocalisation((String)obj.get("localisation"));
                f.setTour((String)obj.get("tour"));
                    
                Matchtb.add(f);
            }

        } catch (IOException ex) {

        }
        return Matchtb;
    }

    public ArrayList<Matchtb> parseOneMatchtb(String jsonText) {
        try {
            Matchtb = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> MatchtbListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Matchtb f = new Matchtb();

            Map<String, Object> User = (Map<String, Object>) MatchtbListJson.get("idU");
            float idU = Float.parseFloat(User.get("id").toString());

            float id = Float.parseFloat(MatchtbListJson.get("id").toString());
            float rate = Float.parseFloat(MatchtbListJson.get("rate").toString());
           /* f.setUsername(User.get("username").toString());

            f.setId((int) id);
            f.setDescription(MatchtbListJson.get("description").toString());
            f.setRate((int) rate);

            f.setDate(MatchtbListJson.get("date").toString().substring(0, 10) + " " + MatchtbListJson.get("date").toString().substring(11, 19));

            f.setUser((int) idU);*/
            Matchtb.add(f);
        } catch (IOException ex) {

        }
        return Matchtb;
    }

    public ArrayList<Matchtb> getAllMatchtb() {
       String url = "http://localhost:8000/api/matchtb";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Matchtb = parseMatchtb(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(Matchtb.get(0).getArbitreprincipale());
        return Matchtb;
    }

    public ArrayList<Matchtb> FindMatchtb(Matchtb f) {
       String url = "http://localhost:8000/api/matchtb";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Matchtb = parseOneMatchtb(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Matchtb;
    }
}
