package Display;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;


public class DisplayScreen {

    private JFrame frame;
    private Canvas canvas;

    private URL iconURL;

    private String title;
    private int width, height;

    public DisplayScreen(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;



        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        
        frame.setUndecorated(true);
        frame.setSize(width, height);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false); // Keep this.
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
        frame.setBackground(Color.black);       
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      

        
        
        try {
            frame.setIconImage(ImageIO.read(new File("res/Sheets/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        canvas.setBackground(Color.black);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}
