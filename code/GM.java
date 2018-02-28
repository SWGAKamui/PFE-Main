

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;

public class GM extends javax.swing.JFrame {
    public GM() {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        
        JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        browser.loadURL("http://maps.google.com/");
        //https://www.google.fr/maps/place/R%C3%A9sidence+Crous+-+Village+1+r%C3%A9nov%C3%A9/@44.80695,-0.6083624,17z/data=!3m1!4b1!4m5!3m4!1s0xd54d8822aa73187:0xc691aeba1fd592f3!8m2!3d44.80695!4d-0.603985
        //browser.loadURL("https://maps.google.fr/");
    }
} 