/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class panierForm extends Form {
     private Database db;
            Toolbar menu,menu1;
             TableLayout tableLayout;
              int spanButton = 2;
 private Resources theme;
    public panierForm(){
        Dialog dlg = new Dialog("Authentication");
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
    title.getUnselectedStyle().setFgColor(0xff);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    
    TextArea ta = new TextArea("Commentaire vide");
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    


        setUIID("CatalogueForm");
        setTitle("your wish list");
        Button btns=new Button("suuprimer");
         Container c = new Container(BoxLayout.y());
          try {
            db=Database.openOrCreate("db_produits");
           db.execute("create table if not exists panier (id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT,prix FLOAT);");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         try {
                    Cursor cur=db.executeQuery("select * from panier");
                   
                    while(cur.next()){
                        Row r=cur.getRow();
                         Label nom=new Label("produit wished "+r.getString(0)+" : "+ "Nom :"+r.getString(1)+"prix :"+r.getInteger(2)); 
                         nom.addPointerPressedListener(e->{
           if(Dialog.show("Valider", "Voulez vous vraiment supprimer ce produit ? ", "Confirmer", "Annuler"))
                    {
                        try {
 db.execute("DELETE FROM panier WHERE id = '"+r.getString(0)+"'");
 
Dialog.show("Alert", "produit supprimer", new Command("OK")); 
show();
                        } catch (IOException ex) {
                            System.err.println("fail");
                            System.out.println(ex);
                        }
                    }
                    
                                               

                             
                         });

                       c.addAll(nom);
                   

                       
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
         
menu=getToolbar();
menu.addMaterialCommandToSideMenu("Profil",FontImage.MATERIAL_PERSON, e -> new HomeForm().show() );
  menu.addMaterialCommandToSideMenu("Catalogue",FontImage.MATERIAL_CATEGORY, e -> new CatalogueForm().show() );
menu.addMaterialCommandToSideMenu("Wish List",FontImage.MATERIAL_STAR, e -> new panierForm().show() );
    menu.addMaterialCommandToSideMenu("Best Seller",FontImage.MATERIAL_ADD_CHART, e -> new StatistiqueForm().show() );
       menu.addMaterialCommandToSideMenu("Find Us",FontImage.MATERIAL_MAP, e -> new mapForm().show() );

         addAll(c);
                show();

}
    
}
