/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author gold
 */
public class ProfileForm extends BaseForm {
    
    
      public ProfileForm(Resources res) {
        super( BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));

        
//        TextField cin = new TextField();
//        cin.setText(""+SessionManager.getCin());
//        cin.setUIID("TextFieldBlack");
//        addStringValue("Cin", cin);
        




        Label username = new Label();
        username.setText(SessionManager.getUserName());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        Label email = new Label();
        email.setText(SessionManager.getEmail());
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
//        TextField prenom = new TextField();
//        prenom.setText(SessionManager.getPrenom());
//        prenom.setUIID("TextFieldBlack");
//        addStringValue("Prenom", prenom);

//        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
//        cb1.setUIID("Label");
//        cb1.setPressedIcon(res.getImage("on-off-on.png"));
//        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
//        cb2.setUIID("Label");
//        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
       
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
}
