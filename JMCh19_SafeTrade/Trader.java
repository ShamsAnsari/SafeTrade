import java.awt.GraphicsConfiguration;
import java.lang.reflect.*;
import java.util.*;


/**
 * Represents a stock trader.
 * 
 * @author Elisa Yang
 * @author Shams Ansari
 * @author Leo Shaw
 * @version Mar 20, 2019
 * @author Period: TODO
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


    public Trader( Brokerage brokerage, String name, String pswd )
    {
        this.brokerage = brokerage;
        screenName = name;
        password = pswd;
        mailbox = new LinkedList<String>();
    }


    public String getName()
    {
        return screenName;
    }


    public String getPassword()
    {
        return password;
    }


    public int compareTo( Trader other )
    {
        return screenName.compareToIgnoreCase( other.getName() );
    }


    public boolean equals( Object other )
    {
        if ( !( other instanceof Trader ) )
        {
            throw new ClassCastException();
        }

        return this.getName().equals( other );
    }


    public void openWindow()
    {
        myWindow = new TraderWindow( this );
        while ( this.hasMessages() )
        {
            myWindow.showMessage( mailbox.remove() );
        }
    }


    public boolean hasMessages()
    {
        return !mailbox.isEmpty();
    }


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
                    str += separator + field.getType().getName() + " "
                        + field.getName();
                else
                    str += separator + field.getType().getName() + " "
                        + field.getName() + ":" + field.get( this );
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
