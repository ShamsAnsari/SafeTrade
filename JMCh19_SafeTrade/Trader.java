import java.awt.GraphicsConfiguration;
import java.lang.reflect.*;
import java.util.*;
//TODO BY MR.FULK
/**
 * Represents a stock trader.
 */
public class Trader implements Comparable<Trader>
{
    private Brokerage brokerage;
    private String screenName, password;
    private TraderWindow myWindow;
    private Queue<String> mailbox;

    // TODO complete class


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

    @Override
    public int compareTo( Trader o )
    {
        // TODO Auto-generated method stub by Shams A.
        return 0;
    }

    public GraphicsConfiguration getName()
    {
        // TODO Auto-generated method stub by Shams A.
        return null;
    }

    public void quit()
    {
        // TODO Auto-generated method stub by Shams A.
        
    }

    public void getQuote( String symbol )
    {
        // TODO Auto-generated method stub by Shams A.
        
    }

    public void placeOrder( TradeOrder tradeOrder )
    {
        // TODO Auto-generated method stub by Shams A.
        
    }
}
