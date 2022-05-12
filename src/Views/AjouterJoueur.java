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
  

import Controller.JoueurController;
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
public class AjouterJoueur extends BaseForm{
    
 Form current;
    public AjouterJoueur(Resources res){
        
    
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "prenom", 20, TextField.ANY);
        TextField poste = new TextField("", "poste", 20, TextField.ANY);
        TextField tel = new TextField("", "tel", 20, TextField.NUMERIC);
        TextField nb_but = new TextField("", "nb_but", 20, TextField.NUMERIC);
        TextField image = new TextField("", "image", 20, TextField.ANY);
       // TextField user = new TextField("", "user", 20, TextField.NUMERIC);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        poste.setSingleLineTextArea(false);
         tel.setSingleLineTextArea(false);

                           nb_but.setSingleLineTextArea(false);
        image.setSingleLineTextArea(false);
      //  user.setSingleLineTextArea(false);
       
        Button inscrit = new Button("Ajouter joueur");
        
      
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter joueur", "LogoLabel"),
                 new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                  new FloatingHint(poste),
                createLineSeparator(),
                 new FloatingHint(tel),
                createLineSeparator(),
                
                new FloatingHint(nb_but),
                createLineSeparator(),
                new FloatingHint(image),
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
            
            JoueurController.getInstance().register(nom, prenom, poste, tel,nb_but, image, res);
            
              //   new ListEquipe(current, res).show();
            
        });
    }

    
}
    

