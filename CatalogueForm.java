/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.Log;

import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.progress.ArcProgress;
import com.codename1.progress.CircleFilledProgress;
import com.codename1.progress.CircleProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Stroke;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entite.Produit;
import java.util.ArrayList;
import com.mycompany.myapp.gui.Service.ServiceProduits;
import java.io.IOException;
import java.util.Date;
/**
 *
 * @author asus
 */
public class CatalogueForm extends Form implements LocalNotificationCallback{
    private Form ListView,display ;
       private Database db;
      private Form details;
      private Resources theme;
       Toolbar menu,menu1;
       Container conte;
        EncodedImage enc;
        private Produit lui;
        Form current = this;
    Image img;
    ImageViewer imv;
    public CatalogueForm()
            
    {
        Button not=new Button();
        
         LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
     
        n.setAlertImage("/messenger.png");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );



/*setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    final CircleProgress p = new CircleProgress();
    p.setProgress(100);
    p.setClockwise(true);
    p.setStartAngle(CircleProgress.START_9_OCLOCK);
    add(p);
    
    final ArcProgress p2 = new ArcProgress();
    p2.setProgress(70);
   add(p2);

    final CircleFilledProgress p3 = new CircleFilledProgress();
    p3.setProgress(70);
    add(p3);
    
    Slider slider = new Slider();
    slider.setEditable(true);
    slider.addDataChangedListener(new DataChangedListener() {

        @Override
        public void dataChanged(int type, int index) {
            p.setProgress(index);
            p2.setProgress(index);
            p3.setProgress(index);
        }
    });
   add(slider);
    
*/


         try {
            db=Database.openOrCreate("db_produits");
           db.execute("create table if not exists panier (id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT,prix FLOAT);");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setTitle("Produits");

        menu=getToolbar();
        menu.addMaterialCommandToSideMenu("Profil",FontImage.MATERIAL_PERSON, e -> new HomeForm().show() );
  menu.addMaterialCommandToSideMenu("Catalogue",FontImage.MATERIAL_CATEGORY, e -> new CatalogueForm().show() );
menu.addMaterialCommandToSideMenu("Wish List",FontImage.MATERIAL_STAR, e -> new panierForm().show() );
    menu.addMaterialCommandToSideMenu("Best Seller",FontImage.MATERIAL_ADD_CHART, e -> new StatistiqueForm().show() );
   menu.addMaterialCommandToSideMenu("Find Us",FontImage.MATERIAL_PLACE, e -> new mapForm().show() );
 getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new HomeForm().show());

        ServiceProduits serviceTask=new ServiceProduits();
        ArrayList<Produit> lis=serviceTask.getAllProduits();
               

     for(int i=0;i<lis.size();i++){
         
         String path = "file://C:/xampp/htdocs/sprint18/web/images/"  +lis.get(i).getImage();
                    Image setimg = FileEncodedImage.create(path,300, 300);
                    ImageViewer iv = new ImageViewer(setimg);
     Produit produit = new Produit(lis.get(i).getId() ,lis.get(i).getName()
             ,lis.get(i).getDescription()
             ,lis.get(i).getPrix()
       ,lis.get(i).getQuantite(),lis.get(i).getImage()
         ,lis.get(i).getType(),lis.get(i).getTaille()
         , lis.get(i).getViews(),lis.get(i).getNbrAchat(),lis.get(i).getCategorie_id()
          ,lis.get(i).getMarque(),lis.get(i).getDate(),lis.get(i).getSexe());
         final Produit yo=lis.get(i);
       conte=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Stroke borderStroke = new Stroke(10, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            RoundBorder border=RoundBorder.create().color(0x99CCCC).strokeColor(5).
            strokeOpacity(120).stroke(borderStroke).rectangle(true);
      Container cn1=new Container(new BorderLayout());
              //  imv = new ImageViewer(img);
     Label StatNom=new Label("Nom :"+lis.get(i).getName());
       Label   prix=new Label("Prix :"+lis.get(i).getPrix()+"DT");
     Label quantite=new Label(""+lis.get(i).getQuantite()+" Pieces Disponible");
     MultiButton m = new MultiButton();
            m.setTextLine1(lis.get(i).getName());
            m.setTextLine2(""+lis.get(i).getPrix());
             Image setimg2 = FileEncodedImage.create(path,300, 300);
                    ImageViewer pic = new ImageViewer(setimg2);
      conte.add(iv);
                conte.addAll(StatNom);
                conte.addAll(prix);
                conte.add(quantite);


                
                cn1.add(BorderLayout.EAST, conte);
                add(cn1);
         getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component conte : getContentPane()) {
            conte.setHidden(false);
            conte.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component conte : getContentPane()) {
            MultiButton mb = (MultiButton)conte;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);      

          
                
 StatNom.addPointerPressedListener(e->{
 String path1 = "file://C:/xampp/htdocs/sprint18/web/images/"  +produit.getImage();
                    Image setimg1 = FileEncodedImage.create(path,500, 500);
                    ImageViewer iv1 = new ImageViewer(setimg1);
           Form details=new Form("Details",BoxLayout.y());
          Label Nom=new Label("Nom :"+produit.getName());  
          Label description=new Label("description :"+produit.getDescription());
           Label   prixx=new Label("Prix :"+produit.getPrix()+"DT");
          Label quantitee=new Label(""+produit.getQuantite()+" Pieces Disponible");
            Label type=new Label("type :"+produit.getType()); 
           Label taille=new Label("taille :"+produit.getTaille()); 
                    Label viws=new Label("views :"+produit.getViews());       
                    Label nbachat=new Label("nbachat :"+produit.getNbrAchat());  


  


            details.getToolbar().addCommandToOverflowMenu("add to mu wish list", null, add->{
                     try {
                db.execute("insert into panier (nom,prix) values ('"+produit.getName()+"','"+produit.getPrix()+"')");
Dialog.show("Alert", "produit ajouter au panier", new Command("OK"));         
                     } catch (IOException ex) {
                ex.printStackTrace();
            } });
                     details.getToolbar().addCommandToOverflowMenu("Display", null, disp->{
                
                    display=new panierForm();
                  
                display.show();
                
                
            });
                     
                


           


details.addAll(iv1,Nom,description,prixx,quantitee,type,taille,viws,nbachat);
            details.show();
            details.add(not);
                    

            });
                
                }

}
  public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

    @Override
    public void localNotificationReceived(String notificationId) {
        System.out.println("Received local notification "+notificationId+" in callback localNotificationReceived");
    }

}

