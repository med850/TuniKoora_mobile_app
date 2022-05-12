/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controller.ProduitController;
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
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author CHAOUCH KHALIL
 */
public class AjouterProduit extends BaseForm{
    
    Form current;
    
    public AjouterProduit(Resources res){
        
    
        
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "nom", 20, TextField.ANY);
        TextField prix = new TextField("", "prix", 20, TextField.NUMERIC);
        TextField qte = new TextField("", "qte", 20, TextField.NUMERIC);
        TextField description = new TextField("", "description", 20, TextField.ANY);
        TextField image = new TextField("", "image", 20, TextField.ANY);
       // TextField user = new TextField("", "user", 20, TextField.NUMERIC);
        nom.setSingleLineTextArea(false);
        prix.setSingleLineTextArea(false);
        qte.setSingleLineTextArea(false);
         description.setSingleLineTextArea(false);
        image.setSingleLineTextArea(false);
      //  user.setSingleLineTextArea(false);
       
        Button inscrit = new Button("Ajouter produit");
        
      
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter produit", "LogoLabel"),
                 new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prix),
                createLineSeparator(),
                  new FloatingHint(qte),
                createLineSeparator(),
                 new FloatingHint(description),
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
            
            ProduitController.getInstance().register(nom, prix, qte, description, image, res);
          //  sendMail(previous,"produit ajouter");
             new listProd(current, res).show();
            
        });
    }

    AjouterProduit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
//     public void sendMail(Form previous,String MailTo) {
//        try {
//           
//            Properties props = new Properties();
//                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
//props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
//props.put("mail.smtps.auth", "true"); //enable authentication
//             
//           
//           
//           
//            Session session = Session.getInstance(props,null);
//            MimeMessage msg = new MimeMessage(session);
//           
//            msg.setFrom(new InternetAddress("Réclamation reçue <mohamedkhalil.chaouch@esprit.tn>"));
//            msg.setRecipients(javax.mail.Message.RecipientType.TO,MailTo );
//            msg.setSubject("SHARED");
//            msg.setSentDate(new Date(System.currentTimeMillis()));
//           
//           String mp = "khoukelc2222";//mp taw narj3lo
//         
//           
//           
//           msg.setText("produit ajouter");
//           
//          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
//           
//          st.connect("smtp.gmail.com",465,"<mohamedkhalil.chaouch@esprit.tn","khoukelc2222");
//           
//          st.sendMessage(msg, msg.getAllRecipients());
//           
//          System.out.println("server response : "+st.getLastServerResponse());
//         
//        }catch(Exception e ) {
//            e.printStackTrace();
//        }
//    }
//    
}
