/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.expertoweb.Batch_IJ;

import ij.plugin.frame.PlugInFrame;;  
import ij.gui.GenericDialog;  
import ij.ImagePlus;  
import ij.io.Opener;
import ij.io.OpenDialog;
import ij.process.*;  
import ij.IJ;  
import ij.Macro;  
import java.awt.CheckboxGroup;
import java.awt.Color; 
import java.io.File;



/**
 *
 * @author expertoweb
 */
public class Batch_IJ extends PlugInFrame {

    /**
     * Creates new form Batch_IJ_Frame
     */
    public Batch_IJ() {
        super("FrameDemo");
        initComponents();
    }
    
    public void run(String arg) {  
  
        // A - Without automation: the dialog shows  
        ImagePlus imp = createImage();  
        if (null != imp) imp.show();  
  
        // B - With automation: the dialog never shows:  
  
        Thread thread = Thread.currentThread();  
        thread.setName("Run$_create_image");  
  
        // ... so we can create many images in a loop, for example.  
        for (int i=1; i<=3; i++) {  
            // Create a 1024x1024 image, 160bit, with noise added  
            // and with the title storing the loop index:  
            Macro.setOptions(thread, "title='My new image " + i + "'"  
                  + " width=1024 height=1024 type='16-bit' add_noise");  
            // Above, notice how we do not set the fill_value key,  
            //     so that its default value (zero in this case) is taken.  
            ImagePlus imp2 = createImage();  
            imp2.show();  
        }  
  
        // Cleanup: remove reference to the Thread and its associated options  
        Macro.setOptions(thread, null);  
        
        setVisible(true);
    }  
  
    public ImagePlus createImage() {  
        final GenericDialog gd = new GenericDialog("Create image");  
        gd.addStringField("title:", "new");  
        gd.addNumericField("width:", 512, 0);  
        gd.addNumericField("height:", 512, 0);  
        final String[] types = new String[]{"8-bit", "16-bit", "32-bit", "RGB"};  
        gd.addChoice("type:", types, types[0]);  
        gd.addSlider("fill_value:", 0, 255, 0); // min, max, default  
        gd.addCheckbox("add_noise", false);  
        gd.showDialog();  
        if (gd.wasCanceled()) return null;  
  
        final String title = gd.getNextString();  
        final int width = (int)gd.getNextNumber();  
        final int height = (int)gd.getNextNumber();  
        final int itype = gd.getNextChoiceIndex();  
        final double fill_value = gd.getNextNumber();  
        final boolean add_noise = gd.getNextBoolean();  
  
        ImageProcessor ip = null;  
  
        switch (itype) {  
            case 0: ip = new ByteProcessor(width, height);  break;  
            case 1: ip = new ShortProcessor(width, height); break;  
            case 2: ip = new FloatProcessor(width, height); break;  
            case 3: ip = new ColorProcessor(width, height); break;  
        }  
  
        // Color images are created filled with white by default  
        if (3 == itype && 255 != fill_value) {  
            // color image  
            ip.setColor(new Color((int)fill_value, (int)fill_value, (int)fill_value));  
            ip.fill();  
        }  
        // non-color images  
        else if (0 != fill_value) {  
            ip.setValue(fill_value);  
            ip.fill();  
        }  
  
        final ImagePlus imp = new ImagePlus(title, ip);  
  
        if (add_noise) IJ.run(imp, "Add Noise", "");  
  
        return imp;  
    }  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        panel2 = new java.awt.Panel();
        checkbox2 = new java.awt.Checkbox();
        checkbox3 = new java.awt.Checkbox();
        button2 = new java.awt.Button();
        panel3 = new java.awt.Panel();
        textArea1 = new java.awt.TextArea();
        textField1 = new java.awt.TextField();
        panel4 = new java.awt.Panel();
        list1 = new java.awt.List();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menuItem1 = new java.awt.MenuItem();
        menu2 = new java.awt.Menu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("jMenu3");
        jMenuBar1.add(jMenu3);

        setPreferredSize(new java.awt.Dimension(744, 492));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        panel2.setBackground(new java.awt.Color(204, 204, 204));
        panel2.setLayout(new java.awt.GridLayout(8, 1));

        checkbox2.setCheckboxGroup(cbg);
        checkbox2.setLabel("Open with Bioformats");
        checkbox2.setState(true);
        panel2.add(checkbox2);

        checkbox3.setCheckboxGroup(cbg);
        checkbox3.setLabel("Open");
        panel2.add(checkbox3);

        button2.setLabel("Select File(s)");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        panel2.add(button2);

        add(panel2, java.awt.BorderLayout.WEST);

        panel3.setBackground(new java.awt.Color(153, 153, 153));
        panel3.setLayout(new java.awt.BorderLayout());
        panel3.add(textArea1, java.awt.BorderLayout.CENTER);

        textField1.setPreferredSize(new java.awt.Dimension(500, 20));
        textField1.setText("textField1");
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });
        panel3.add(textField1, java.awt.BorderLayout.SOUTH);

        add(panel3, java.awt.BorderLayout.CENTER);

        list1.setMultipleMode(true);
        panel4.add(list1);

        add(panel4, java.awt.BorderLayout.EAST);

        menu1.setLabel("File");

        menuItem1.setLabel("menuItem1");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        menu1.add(menuItem1);

        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setMenuBar(menuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        if (checkbox2.getState()){
            IJ.run("Bio-Formats Importer");
        }
        else{
            OpenDialog opener = new OpenDialog(".");  
            System.out.println(opener.getPath());
            ImagePlus imp = (new Opener()).openImage(opener.getPath());
            imp.show();
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Batch_IJ().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button2;
    private java.awt.Checkbox checkbox2;
    private java.awt.Checkbox checkbox3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private java.awt.List list1;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuItem menuItem1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private java.awt.Panel panel4;
    private java.awt.TextArea textArea1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables


    CheckboxGroup cbg = new CheckboxGroup();
}
