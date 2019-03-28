import java.awt.GraphicsConfiguration;
import java.lang.reflect.*;
import java.util.*;


/**
 * Represents a stock trader.
 * 
 * @author Elissa Yang
 * @author Shams Ansari
 * @author Leo Shaw
 * @version Mar 20, 2019
 * @author Period: 4
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources: TODO
 */
public class Trader implements Comparable<Trader>
{
    private Brokerage brokerage;

    private String screenName, password;

    private TraderWindow myWindow;

    private Queue<String> mailbox;


    /**
     * Constructs a new trader, affiliated with a given brockerage, with a given
     * screen name and password.
     * 
     * @param brokerage
     *            - given brokerage
     * @param name
     *            - given screen name
     * @param pswd
     *            - passwords
     */
    public Trader( Brokerage brokerage, String name, String pswd )
    {
        this.brokerage = brokerage;
        screenName = name;
        password = pswd;
        mailbox = new LinkedList<String>();
    }


    /**
     * Returns the screen name for this trader
     * 
     * @return screen name
     */
    public String getName()
    {
        return screenName;
    }


    /**
     * Returns the password for this trader.
     * 
     * @return password
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * Compares this trader to another by comparing their screen names case
     * blind
     * 
     * @return compare value
     */
    public int compareTo( Trader other )
    {
        return screenName.compareToIgnoreCase( other.getName() );
    }


    /**
     * Indicates whether some other trader is "equal to" this one, based on
     * comparing their screen names case blind
     * 
     * @return equals
     */
    public boolean equals( Object other )
    {
        if ( !( other instanceof Trader ) )
        {
            throw new ClassCastException();
        }

        return this.compareTo( (Trader)other ) == 0;
    }


    /**
     * Creates a new TraderWindow for this trader and saves a reference to it in
     * myWindow.
     */
    public void openWindow()
    {
        myWindow = new TraderWindow( this );
        while ( this.hasMessages() )
        {
            myWindow.showMessage( mailbox.remove() );
        }
    }


    /**
     * Returns true if this trader has any messages in its mailbox.
     * 
     * @return if trader has messagess
     */
    public boolean hasMessages()
    {
        return !mailbox.isEmpty();
    }


    /**
     * Adds msg to this trader's mailbox and displays all messages
     * 
     * @param msg
     *            - msg to add to mailbox
     */
    public void receiveMessage( String msg )
    {
        mailbox.add( msg );
        if ( myWindow != null )
        {
            while ( this.hasMessages() )
            {
                myWindow.showMessage( mailbox.remove() );
            }
        }
    }


    public void getQuote( String symbol )
    {
        brokerage.getQuote( symbol, this );
    }


    public void placeOrder( TradeOrder order )
    {
        brokerage.placeOrder( order );
    }


    public void quit()
    {
        brokerage.logout( this );
        myWindow = null;
    }


    //
    // The following are for test purposes only
    //
    protected Queue<String> mailbox()
    {
        return mailbox;
    }
    
    protected void clearMail()
    {
        mailbox.clear();
    }

    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Trader.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                if ( field.getType().getName().equals( "Brokerage" ) )
                    str += separator + field.getType().getName() + " " + field.getName();
                else
                    str += separator + field.getType().getName() + " " + field.getName() + ":"
                        + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }
}
