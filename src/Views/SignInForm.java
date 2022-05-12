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
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author gold
 */
public class SignInForm extends BaseForm{
    
    
        public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("tunikoora.png"), "LogoLabel"));
        
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Connexion");
//        Button signUp = new Button("Sign Up");
//        
    
        //mp oublié
//        Button  mp = new Button("oublier mot de passe?","CenterLabel");
//        
//        
//        signUp.addActionListener(e -> new AjouterUsers(res).show());
//        signUp.setUIID("Link");
//        Label doneHaveAnAccount = new Label("Vous n'avez aucune compte?");
        
        
        
        
        
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                  createLineSeparator(),
                  signIn
//                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),mp
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        
        signIn.addActionListener(e -> 
        {
               UsersController.getInstance().signin(email, password, res);
               
              // new ProfileForm(res);
              
              new ProfileForm(res).show();
              
               
           
        });
        
        
        
        //Mp oublie event
        
//        mp.addActionListener((e) -> {
//           
//            new ActivateForm(res).show();
//            
//            
//        });
        
    }
    
    
    
    
}
