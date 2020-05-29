/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.social.AppleLogin;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entite.Produit;
import com.mycompany.myapp.gui.Service.ServiceProduits;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class HomeForm extends Form {
        Form current;
         Container conte;
         Label StatPrix;
     Label StatNom;
     Label prix;
     Label nom;
     Label StatDescription;
     Label description;
     Label type;
     Label StatType;
     Label quantite;
     Label StatQuantite;
     Label region;
     Label StatRegion;
     Label StatQ;
       Image img;
    ImageViewer imv;
     EncodedImage enc;
        private Resources theme;
 public static String AppleClientSecretURL = null;
    public static String AppleTeamID = "HRNMHC7527";
    public static String AppleKeyID = "9K48F5P6SW";
public static String ApplePrivateKey = "-----BEGIN PRIVATE KEY-----\n" +
        "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg2Pv8N3waHrH6WU5a\n" +
        "87SA17FZpLtZYXFYfTnMwBiZ5Z+gCgYIKoZIzj0DAQehRANCAATECgHrChq5ccqj\n" +
        "2sKF8BmJEKgHefk5ueM02dCrp4A/Y/5E9J84sE5e1ScJbasH3zuk2C09eGyQFyf2\n" +
        "wT6tSjSz\n" +
        "-----END PRIVATE KEY-----";
    public static String AppleRedirectURI = "https://weblite.ca/cn1tests/signindemo";
    public static String AppleClientID = "ca.weblite.signindemosvc";
    public static String AppleClientSecret = "eyJraWQiOiIiLCJhbGciOiJFUzI1NiJ9.eyJpc3MiOiIiLCJpYXQiOjE1NzUzOTM1MjIsImV4cCI6MTU5MDk0NTUyMiwiYXVkIjoiaHR0cHM6Ly9hcHBsZWlkLmFwcGxlLmNvbSIsInN1YiI6IiJ9.NoxRRw8M-t6QA10mbscRWq8bCeRt3LA5Qcp2y_TEa59ExAzZgwlRLZOY5c3XO44vvh5tZQRSG06OT7C1L_ls1A";

AppleLogin login = new AppleLogin();
    {
        login.setClientId(AppleClientID);
        login.setClientSecret(AppleClientSecret);
        login.setRedirectURI(AppleRedirectURI);
        login.setKeyId(AppleKeyID);
        login.setTeamId(AppleTeamID);
        login.setPrivateKey(ApplePrivateKey);
        
    }
     public HomeForm() {
         setUIID("CatalogueForm");
         current = this; //Récupération de l'interface(Form) en cours
        setTitle("veuillez s'authentifier ");
        setLayout(BoxLayout.yCenter());
        TextField txte = new TextField("tayssir.khalifa@esprit.tn", "Login", 20, TextField.EMAILADDR);
        TextField txtp = new TextField("password", "Password", 20, TextField.PASSWORD);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

      Button btnValider = new Button("valider");
        btnValider.addActionListener(e -> new CatalogueForm().show());

        Button loginBtn = new Button("Sign in with Apple");
        AppleLogin.decorateLoginButton(loginBtn, 0x0, 0xffffff);
        AppleLogin.createAppleLogo(0x0, 15f);
         loginBtn.addActionListener(evt->{
          login.doLogin(new LoginCallback() {
                @Override
                public void loginFailed(String errorMessage) {
                    System.out.println("Login failed");
                    ToastBar.showErrorMessage(errorMessage);
                }

                @Override
                public void loginSuccessful() {
                    new CatalogueForm().show();
                }
            });
        });

ServiceProduits serviceTask=new ServiceProduits();
        ArrayList<Produit> lis=serviceTask.getAllProduits();
                   addAll(txte,txtp,btnValider,loginBtn);

}
     public Form getFormHome() {
         
        return current;
    }
}        //  for(int i=0;i<lis.size();i++){
                   // Produit produit = new Produit(lis.get(i).getId() ,lis.get(i).getName(),lis.get(i).getDescription(),lis.get(i).getPrix()
                           ///      ,lis.get(i).getQuantite(),lis.get(i).getImage()
                              //   ,lis.get(i).getType(),lis.get(i).getTaille()
                    //   , lis.get(i).getViews(),lis.get(i).getNbrAchat(),lis.get(i).getCategorie_id()
                        //    ,lis.get(i).getMarque(),lis.get(i).getDate(),lis.get(i).getSexe());
                 //  String url="http://localhost/sprint18/web/images/";

 //img = URLImage.createToStorage(enc,"img"+url+produit.getImage(),"img"+url+produit.getImage(),URLImage.RESIZE_SCALE);
            //    imv = new ImageViewer(img);
               /*  final Produit yo=lis.get(i);
                 conte=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                 Container cn1=new Container(new BorderLayout());
               imv = new ImageViewer(img);

                Label StatNom=new Label("Nom :"+lis.get(i).getName());
               Label   prix=new Label("Prix :"+lis.get(i).getPrix()+"DT");
     Label quantite=new Label(""+lis.get(i).getQuantite()+" Pieces Disponible");

               Button panier=new Button("voir mon panier");
               
               conte.addAll(imv);

                conte.addAll(StatNom);
                
                
                conte.addAll(prix);
               conte.add(quantite);

                conte.add(panier);
               cn1.add(BorderLayout.EAST, conte);
            cn1.add(BorderLayout.WEST,imv);

                add(cn1);}*/
        
     


     


   
