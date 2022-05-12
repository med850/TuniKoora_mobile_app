/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

/**
 *
 * @author aroua
 */
   

import Controller.EquipeController;
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
 * @author CHAOUCH KHALIL
 */
public class AjouterEquipe extends BaseForm{
    
 Form current;
    public AjouterEquipe(Resources res){
        
    
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "nom", 20, TextField.ANY);
        TextField classement = new TextField("", "classement", 20, TextField.NUMERIC);
       
       // TextField user = new TextField("", "user", 20, TextField.NUMERIC);
        nom.setSingleLineTextArea(false);
        classement.setSingleLineTextArea(false);
       
      //  user.setSingleLineTextArea(false);
       
        Button inscrit = new Button("Ajouter equipe");
        
      
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter equipe", "LogoLabel"),
                 new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(classement),
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
            
            EquipeController.getInstance().register(nom,classement,res);
            
            
             new ListEquipe(current, res).show();
            
        });
    }

    
}

