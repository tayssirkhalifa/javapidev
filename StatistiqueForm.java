/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entite.Produit;
import com.mycompany.myapp.gui.Service.ServiceProduits;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class StatistiqueForm extends Form{
                Toolbar menu,menu1;

    public StatistiqueForm()
    {
        
menu=getToolbar();
menu.addMaterialCommandToSideMenu("Profil",FontImage.MATERIAL_PERSON, e -> new HomeForm().show() );
  menu.addMaterialCommandToSideMenu("Catalogue",FontImage.MATERIAL_CATEGORY, e -> new CatalogueForm().show() );
menu.addMaterialCommandToSideMenu("Wish List",FontImage.MATERIAL_STAR, e -> new panierForm().show() );
    menu.addMaterialCommandToSideMenu("Best Seller",FontImage.MATERIAL_ADD_CHART, e -> new StatistiqueForm().show() );
       menu.addMaterialCommandToSideMenu("Find Us",FontImage.MATERIAL_MAP, e -> new mapForm().show() );

        Container Container1 = new Container();
            Container1.getUnselectedStyle().setBackgroundType(Style.BACKGROUND_GRADIENT_RADIAL);
            Container1.getUnselectedStyle().setBackgroundGradientEndColor(0xFFBCCA);
            Container1.getUnselectedStyle().setBackgroundGradientStartColor(0xFFBCCA);
        ServiceProduits serviceTask=new ServiceProduits();
        ArrayList<Produit> lis=serviceTask.getAllProduits();
               

     for(int i=0;i<lis.size();i++){
     Produit produit = new Produit(lis.get(i).getId() ,lis.get(i).getName()
             ,lis.get(i).getDescription()
             ,lis.get(i).getPrix()
       ,lis.get(i).getQuantite(),lis.get(i).getImage()
         ,lis.get(i).getType(),lis.get(i).getTaille()
         , lis.get(i).getViews(),lis.get(i).getNbrAchat(),lis.get(i).getCategorie_id()
          ,lis.get(i).getMarque(),lis.get(i).getDate(),lis.get(i).getSexe());
        
    // Generate the values
        //double[] values = new double[]{12, 14, 11, 10, 19};
        double[] value = new double[]{lis.get(i).getNbrAchat()};

        
        // Set up the renderer
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN,ColorUtil.LTGRAY,ColorUtil.GRAY};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Project budget", value), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
       setTitle("Best seller");
        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, c);
     show();
    
   
    }}
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
            ServiceProduits serviceTask=new ServiceProduits();
        ArrayList<Produit> lis=serviceTask.getAllProduits();

        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(30);
        renderer.setLegendTextSize(30);
        renderer.setApplyBackgroundColor(true);
              for(int i=0;i<lis.size();i++){
     Produit produit = new Produit(lis.get(i).getId() ,lis.get(i).getName()
             ,lis.get(i).getDescription()
             ,lis.get(i).getPrix()
       ,lis.get(i).getQuantite(),lis.get(i).getImage()
         ,lis.get(i).getType(),lis.get(i).getTaille()
         , lis.get(i).getViews(),lis.get(i).getNbrAchat(),lis.get(i).getCategorie_id()
          ,lis.get(i).getMarque(),lis.get(i).getDate(),lis.get(i).getSexe());
        
        renderer.setMargins(new int[]{});
      }
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
      }
    

     protected CategorySeries buildCategoryDataset(String title, double[] value) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        ServiceProduits serviceTask=new ServiceProduits();
         ArrayList<Produit> lis=serviceTask.getAllProduits();
        for(int i=0;i<lis.size();i++){
        for (double valu : value) {
            series.add(lis.get(i).getName() ,valu);
        }
        }
        return series;
    }
}
