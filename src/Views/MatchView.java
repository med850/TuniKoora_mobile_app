/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controller.MatchtbController;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import Entities.Matchtb;
import services.ServiceMatchtb;

/**
 *
 * @author gold
 */
public class MatchView extends BaseForm{
    
    public MatchView(Resources res){
        
    
    super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField localisation = new TextField("", "localisation", 20, TextField.NUMERIC);
        TextField arbitreprincipale = new TextField("", "arbitreprincipale", 20, TextField.ANY);
        TextField tour = new TextField("", "tour", 20, TextField.NUMERIC);
      
        localisation.setSingleLineTextArea(false);
        arbitreprincipale.setSingleLineTextArea(false);
        tour.setSingleLineTextArea(false);
         
        Button add = new Button("ajouter un match");
    
      
        
        Container content = BoxLayout.encloseY(
                new Label("Ajouter un match", "LogoLabel"),
                 new FloatingHint(localisation),
                createLineSeparator(),
                new FloatingHint(arbitreprincipale),
                createLineSeparator(),
                  new FloatingHint(tour),
                createLineSeparator()
        );
        
        
        ServiceMatchtb sv = ServiceMatchtb.getInstance();
        ArrayList<Matchtb> arr = sv.getAllMatchtb();
        int size = arr.size();
        String[][] o = new String[size][] ;
        
        System.out.println(arr.size());
           for (int i =0; i<arr.size(); i++)
           {
               System.out.println(arr.get(i).getLocalisation());
               o[i] = ( new String[]  {arr.get(i).getLocalisation(), arr.get(i).getArbitreprincipale(),arr.get(i).getTour()});
           }
Form hi = new Form("Table", new BorderLayout());
        new DefaultTableModel(new String[]{},new Object[][]{});
        TableModel model = new DefaultTableModel(new String[] { "localisation", "Arbitre", "tour"}, o) {
        public boolean isCellEditable(int row, int col) {
            return col != 0;
        }
    };
        
        
   Table table = new Table(model) {
    @Override
    protected Component createCell(Object value, int row, int column, boolean editable) { // (1)
        Component cell;
        if(row == 1 && column == 1) { // (2)
            Picker p = new Picker();
            p.setType(Display.PICKER_TYPE_STRINGS);
            p.setStrings("Row B can now stretch", "This is a good value", "So Is This", "Better than text field");
            p.setSelectedString((String)value); // (3)
            p.setUIID("TableCell");
            p.addActionListener((e) -> getModel().setValueAt(row, column, p.getSelectedString())); // (4)
            cell = p;
        } else {
            cell = super.createCell(value, row, column, editable);
        }
        if(row > -1 && row % 2 == 0) { // (5)
            // pinstripe effect 
            cell.getAllStyles().setBgColor(0xeeeeee);
            cell.getAllStyles().setBgTransparency(255);
        }
        return cell;
    }
 
    @Override
    protected TableLayout.Constraint createCellConstraint(Object value, int row, int column) {
        TableLayout.Constraint con =  super.createCellConstraint(value, row, column);
        if(row == 1 && column == 1) {
            con.setHorizontalSpan(2);
        }
        con.setWidthPercentage(33);
        return con;
    }
};
hi.add(BorderLayout.CENTER, table);     
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Container newContent = BoxLayout.encloseY(
                hi  
        );
        
        
        content.setScrollableY(true);
        add(BorderLayout.NORTH, content);
                add(BorderLayout.CENTER,newContent);

        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                add
               
        ));
        add.requestFocus();
        add.addActionListener(e ->{
            
            MatchtbController.getInstance().ajouter(localisation,arbitreprincipale, tour);
            
        });
    }

    
    
    
    
    
    
    

}
