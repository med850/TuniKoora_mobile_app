/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.ProduitController;
import Entities.Produit;
import com.codename1.ui.Form;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
//import Entities.Equipe;
//import Controller.EquipeController;
import com.codename1.ui.Button;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class listProd extends Form {
    private Resources theme;
    Form current;

    public listProd(Form previous, Resources res) {
        setTitle("Menu Liste");

//        SpanLabel sp = new SpanLabel();
//        sp.setText(MenuService.getInstance().getAllMenus().toString());
//        add(sp);

        ArrayList<Produit>list = ProduitController.getInstance().getAllEquipes();
        //System.out.println(list);
        
        for(Produit m : list) {
            
            
            showEquipe( m, previous, res);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
  }

    private void showEquipe( Produit m,Form previous, Resources res) {
        int height = Display.getInstance().convertToPixels(30);
        int width = Display.getInstance().convertToPixels (60);
        Button s= new Button();
        Container cnt = BorderLayout.north(s);
        Label lbTitre = new Label("Nom : "+m.getNom(),"NewsTopLine2");
        Label lbDprix = new Label("prix : " +m.getPrix(),"NewsTopLine2"+ "DT");
        Label lbqte = new Label("qte : "+m.getQte(),"NewsTopLine2");
        Label lbDescription = new Label("description : " + m.getDescription()    ,"NewsTopLine2");
        Label lbimage = new Label("image : "+ m.getImage() ,"NewsTopLine2");
       
        
       
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
                
                if(ProduitController.getInstance().delete(m.getId())){
                    new listProd(previous, res).show();
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
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(lbTitre, lbDelete, lbUpdate), BoxLayout.encloseX(lbDescription), BoxLayout.encloseX(lbDprix)
        ,BoxLayout.encloseX(lbqte)));
        add(cnt);
    }
    
    
    
    
    
    

}