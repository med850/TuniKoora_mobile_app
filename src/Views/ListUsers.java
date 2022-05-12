/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controller.UsersController;
import Entities.Users;
import Utils.Connexion;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.components.SpanButton;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.myapp.MyApplication;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author gold
 */
public class ListUsers extends Form{
    
    
   private Resources theme;
    Form current;

    
    
    public ListUsers(Form previous, Resources res) {
        theme = MyApplication.getTheme();
        setTitle("Menu Liste");
          getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

        
        
         
        
       
//        SpanLabel sp = new SpanLabel();
//        sp.setText(MenuService.getInstance().getAllMenus().toString());
//        add(sp);

        ArrayList<Users>list = UsersController.getInstance().getAllUsers();
        //System.out.println(list);
        
        for(Users m : list) {
            
            
            showEquipe( m, previous, res);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
  }

    private void showEquipe( Users m,Form previous, Resources res) {
        int height = Display.getInstance().convertToPixels(30);
        int width = Display.getInstance().convertToPixels (60);
        Button s= new Button();
        Container cnt = BorderLayout.north(s);
      
        Label cin = new Label("cin : "+m.getCin(),"NewsTopLine2");
         Label username = new Label("username : "+m.getUsername(),"NewsTopLine2");
          Label prenom = new Label("prenom : "+m.getPrenom(),"NewsTopLine2");
           Label tel = new Label("tel : "+m.getTel(),"NewsTopLine2");
           Label email = new Label("email : "+m.getEmail(),"NewsTopLine2");
        
        
        
//        Button inscrit = new Button("chart equipe/points");
        
      //  getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
//        Container content = BoxLayout.encloseY(
//                new Label("chart equipe/nb points", "LogoLabel"));
//        inscrit.addPointerPressedListener(l->{
//            new ProfileForm( res).show();
//        });
       
       
        
       
        Label lbDelete = new Label(" ");
        lbDelete.setUIID ("NewsTopLine");
        Style deleteStyle = new Style (lbDelete.getUnselectedStyle());
        deleteStyle.setFgColor(0xf21f1f);
        FontImage deleteImage = FontImage.createMaterial (FontImage. MATERIAL_DELETE, deleteStyle); 
        lbDelete.setIcon (deleteImage);
        lbDelete.setTextPosition (RIGHT);
        
        
        lbDelete.addPointerPressedListener(l->{
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce menu?", "Annuler","Oui")){
                dig.dispose();
            }
            else{
                dig.dispose();
                
                if(UsersController.getInstance().deleteUsers(m.getId())){
                    new ListUsers(previous, res).show();
                }
            }
        });
        
        
        Label lbUpdate = new Label(" ");
        lbUpdate.setUIID ("NewsTopLine");
        Style updateStyle = new Style (lbUpdate.getUnselectedStyle());
        updateStyle.setFgColor(0xf7ad02);
        FontImage updateImage = FontImage.createMaterial (FontImage. MATERIAL_EDIT, updateStyle); 
        lbUpdate.setIcon (updateImage);
        lbUpdate.setTextPosition (RIGHT);
        
//        lbUpdate.addPointerPressedListener(l->{
//            new updateEquipe(previous, res, m).show();
//        });
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(cin, lbDelete, lbUpdate),
                 BoxLayout.encloseX(username))
               // ,BoxLayout.encloseX(tel),BoxLayout.encloseY(inscrit))
              
          
                
                
       
        
         );
        add(cnt);
        
        
        
//        Form hi = new Form("Button");
//Button b = new Button("Link Button");
//b.getAllStyles().setBorder(Border.createEmpty());
//b.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
//hi.add(b);
//b.addActionListener((e) -> Log.p("Clicked"));
  }

}
    
    
    

