import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

import java.io.File;
import java.util.*;

/**
 * Pinboard is the main class of the pinboard application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * To start the application, create an object of this class.
 * 
 * @author David J Barnes and Michael Kölling
 * @version 2012.10.30
 */
public class Pinboard extends JFrame
{
    // static fields:
    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

    // The desktop for displaying the pictures and messages.
    private JDesktopPane desktop;
    // Random number generator for random placement.
    private Random rand;
    // A map of the displayed items.
    // Used to manage removal.
    private HashMap<String, JInternalFrame> displayedItems;
    
    /**
     * Create a Pinboard show it on screen.
     */
    public Pinboard()
    {
        super("Pinboard");
        desktop = new JDesktopPane();
        rand = new Random();
        displayedItems = new HashMap<String, JInternalFrame>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        makeContents();
        
    }
    
    /**
     * Clear the pinboard.
     */
    public void clear()
    {
        desktop.removeAll();
        desktop.repaint();
        displayedItems.clear();
    }
    
    /**
     * Remove a displayed item.
     * @param item A filename or message.
     * @return true if the item was displayed, false otherwise.
     */
    public boolean removeItem(String item)
    {
        JInternalFrame frame = displayedItems.remove(item);
        if(frame != null) {
            desktop.remove(frame);
            desktop.repaint();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Pin the picture in the given file to the pinboard.
     * An error dialog is displayed if the image cannot be read.
     * @param filename The picture file.
     */
    public void pinPicture(String filename)
     {
        File selectedFile = new File(filename);
        OFImage image = ImageFileManager.loadImage(selectedFile);

        if(image != null){
            ImagePanel picture = new ImagePanel(image);
            JInternalFrame pictureFrame = new JInternalFrame("", false, true);
            displayedItems.put(filename, pictureFrame);
            pictureFrame.add(picture);
            pin(pictureFrame);
        }
        else {   // image file was not a valid image
            JOptionPane.showMessageDialog(this,
                    "The file was not found or in a recognized image file format.",
                    "Image Load Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
        
    /**
     * Display a note on the pinboard.
     * @param note The note to be displayed.
     */
    public void pinNote(String note)
    {
        JInternalFrame messageFrame = new JInternalFrame("", false, true);
        displayedItems.put(note, messageFrame);
        messageFrame.add(new JLabel(note));
        pin(messageFrame);
    }
    
    /**
     * Add something to the pinboard.
     * @param frame The frame containing the item to be pinned.
     */
    private void pin(JInternalFrame frame)
    {
        frame.setVisible(true);
        frame.pack();
        // Set a random location within the size of the window.
        Dimension size = desktop.getSize();
        desktop.add(frame);
        if(size.width > 0 && size.height > 0) {
            int x = rand.nextInt(size.width / 2);
            int y = rand.nextInt(size.height/ 2);
            frame.setLocation(x, y);
        }
        
        frame.toFront();
    }
    
    
    /**
     * Create the frame's contents.
     */
    private void makeContents()
    {
        // Initial position.
        int initX = 10;
        int initY = 10;
        // Initial width and height.
        int initWidth = 500;
        int initHeight = 450;
                
        setContentPane(desktop);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(initX, initY);
        setSize(initWidth, initHeight);
        setVisible(true);
    }
    
}
