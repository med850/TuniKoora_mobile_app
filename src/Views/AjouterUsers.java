/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;


import Controller.UsersController;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author gold
 */
public class AjouterUsers extends BaseForm{
    
    public AjouterUsers(Resources res){
        
    
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField cin = new TextField("", "Cin", 20, TextField.NUMERIC);
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.NUMERIC);
        TextField tel = new TextField("", "tel", 20, TextField.NUMERIC);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        cin.setSingleLineTextArea(false);
        username.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
         tel.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button inscrit = new Button("Créer un compte");
        
      
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter un compte", "LogoLabel"),
                 new FloatingHint(cin),
                createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                  new FloatingHint(prenom),
                createLineSeparator(),
                 new FloatingHint(tel),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                inscrit
               
        ));
        inscrit.requestFocus();
        inscrit.addActionListener(e ->{
            
            UsersController.getInstance().register(cin, username, prenom, tel, email, password, password, res);
            
        });
    }
}
