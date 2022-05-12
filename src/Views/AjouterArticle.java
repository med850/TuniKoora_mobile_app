/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controller.ArticleController;
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
public class AjouterArticle extends BaseForm{
    
     Form current;
    
    public AjouterArticle(Resources res){
        
    
        
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField titre = new TextField("", "titre", 20, TextField.ANY);
        TextField description = new TextField("", "description", 20, TextField.NUMERIC);
        titre.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);

       
        Button inscrit = new Button("Ajouter produit");
        
      
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter produit", "LogoLabel"),
                 new FloatingHint(titre),
                createLineSeparator(),
                 new FloatingHint(description),
                createLineSeparator()

               
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                inscrit
               
        ));
        inscrit.requestFocus();
        inscrit.addActionListener(e ->{
            
            ArticleController.getInstance().register(titre, description, res);
             new afficherArticle(current, res).show();
            
        });
    }

    AjouterArticle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
