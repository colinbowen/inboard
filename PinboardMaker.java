import java.util.ArrayList;

/**
 * A class to store a list of picture filenames that can
 * be displayed on a Pinboard object.
 * 
 * @colin.bowen
 * @v0.9
 */
public class PinboardMaker
{
    //An arraylist for storing pictures on the pinboard.
    private ArrayList<String> pictures;
    //Pinboard object
    private Pinboard pinboardObject;

    /**
     * Constructor for objects of class PinboardMaker
     */
    public PinboardMaker()
    {
        pictures = new ArrayList<String>();
        pinboardObject = new Pinboard();
    }
    
    /**
     * A method that pauses the program for the given length of
     * time, in milliseconds.
     * This can be used when you want to pin items with
     * a delay between them - as in a slide show.
     * 
     * @param howLong How long to pause for, in milliseconds.
     */
    private void pause(long howLong)
    {
        try 
        {
            Thread.sleep(howLong);
        }
        catch(InterruptedException e) 
        {

        }
    }

    /**
     * A method that takes a String parameter and stores it in the collection.
     * The picture should not be displayed on the pinboard when it is added.
     */

    public void addPicture(String picture)
    {
        pictures.add(picture);
    }

    /**
     * A method that returns (not prints) the number of
     * picture filenames in the collection.
     */

    public int getNumberOfPictures()
    {
        return pictures.size();
    }

    /**
     * listPictures: A method that prints to the BlueJ terminal window the names of the
     * picture files in the collection, one per line, with an index number against 
     * each file name.
     */
    public void listPictures()
    {
        int index = 0;
        while(index >=0 && index < pictures.size()) 
        {
            String filename = pictures.get(index);
            System.out.println(index + ": "+ filename);
            index++;
        }
    }

    /**
     * A method that takes an integer as a parameter and uses the Pinboard
     * object to display the image file at that index in the list. Use the pinPicture
     * method of Pinboard. However, if the index is not valid (too small or too large)
     * then use the pinNote method of the Pinboard object to pin an error message on the board.
     */
    public void pin(int index)
    {
        if(index >= 0 && index < pictures.size())
        {   
            pinboardObject.pinPicture(pictures.get(index));
        }
        else
        {
            pinboardObject.pinNote("This is not a valid index.");
        }
    }

    /**
     * A method that takes an integer index as parameter and removes
     * the filename at that index from the collection. If the index is not valid
     * then use the pinNote method of the Pinboard to show an error message and don’t
     * change the collection. If the picture is currently displayed on the pinboard it
     * should just be removed from the collection and not the board.
     */
    public void removePicture(int index)
    {
        if(index >= 0 && index < pictures.size())
        {
            pictures.remove(index);
        }
        else
        {
            pinboardObject.pinNote("Index is not valid");
        }
    }

    /**
     * clearPinboard: A method that calls the Pinboard object’s clear method to remove
     * everything on the pinboard.
     */
    public void clearPinboard()
    {
        {
            pinboardObject.clear();
        }
    }

    /**
     * A method that takes no parameters but causes all the pictures files in
     * the collection to be displayed, one after the other, on the pinboard. Before anything
     * is displayed, have displayAll call your clearPinboard method to clear the Pinboard.
     * The displayAll method takes no parameters.
     */
    public void displayAll()
    {
        clearPinboard();
        for(String filename : pictures)
        {
            pinboardObject.pinPicture(filename);
            pause(1000);
        }
    }

    /**
     * unpin: A method that takes an integer index as parameter. The integer
     * is an index into the picture collection. If the index is not valid then
     * use the pinNote method of the Pinboard to show an error message. The
     * unpin method removes the associated picture from the pinboard. It does
     * not remove the picture from the collection.
     */
    public void unpin(int index)
     {
        if(index >=0 && index < pictures.size())
        {
            if(pinboardObject.removeItem(pictures.get(index)))
            {
                pinboardObject.removeItem(pictures.get(index));
            }
            else
            {
               pinboardObject.pinNote("There is no image at index: " + index + " being displayed."); 
            }
        }
        else
        {
             pinboardObject.pinNote("This is not a valid index.");
        }
    }
}
