/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author gold
 */
public class BaseForm extends Form{
    
    private Form current;
        public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
      //  tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new Home(res).show());
      tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
       tb.addMaterialCommandToSideMenu("Ajouter Users", FontImage.MATERIAL_UPDATE, e -> new AjouterUsers(res).show());

   
          tb.addMaterialCommandToSideMenu("Ajouter des produits", FontImage.MATERIAL_UPDATE, e -> new AjouterProduit(res).show());
           tb.addMaterialCommandToSideMenu("Ajouter des articles", FontImage.MATERIAL_UPDATE, e -> new AjouterArticle( res).show());
             tb.addMaterialCommandToSideMenu("Ajouter des equipes", FontImage.MATERIAL_UPDATE, e -> new AjouterEquipe( res).show());
              tb.addMaterialCommandToSideMenu("Ajouter des matches", FontImage.MATERIAL_UPDATE, e -> new AjouterLivraison(res).show());
              
              
              
              //  tb.addMaterialCommandToSideMenu("Liste des livraison", FontImage.MATERIAL_UPDATE, e -> new ListLivraison(current, res).show());

                     tb.addMaterialCommandToSideMenu("Liste Users", FontImage.MATERIAL_UPDATE, e -> new ListUsers(current, res).show());

              tb.addMaterialCommandToSideMenu("Déconnexion", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res).show());

    }
    
    
}
