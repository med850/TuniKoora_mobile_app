/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.LivraisonController;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author 
 */
public class AjouterLivraison extends BaseForm{
    

    Form current;
    
    public AjouterLivraison(Resources res){
        
    
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField ref = new TextField("", "ref", 20, TextField.ANY);
        TextField localisation = new TextField("", "localisation", 20, TextField.ANY);
        TextField etat = new TextField("", "etat", 20, TextField.ANY);
        
       // TextField user = new TextField("", "user", 20, TextField.NUMERIC);
        ref.setSingleLineTextArea(false);
        localisation.setSingleLineTextArea(false);
        etat.setSingleLineTextArea(false);
        
      //  user.setSingleLineTextArea(false);
       
        Button inscrit = new Button("Ajouter livraison");
        
      
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter livraison", "LogoLabel"),
                 new FloatingHint(ref),
                createLineSeparator(),
                new FloatingHint(localisation),
                createLineSeparator(),
                  new FloatingHint(etat),
                createLineSeparator()
                 
//                new FloatingHint(user),
//                createLineSeparator()
               
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                inscrit
               
        ));
        inscrit.requestFocus();
        inscrit.addActionListener(e ->{
            
            LivraisonController.getInstance().register(ref, localisation, etat, res);
             new ListLivraison(current, res).show();
            
        });
    }
}