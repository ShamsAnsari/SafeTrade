import java.lang.reflect.*;
import java.util.*;


/**
 * Represents a stock exchange. A StockExchange keeps a HashMap of stocks, keyed
 * by a stock symbol. It has methods to list a new stock, request a quote for a
 * given stock symbol, and to place a specified trade order.
 * 
 * @author Shams Ansari
 * @author Elisa Yang
 * @author Leo Shaw
 * @version Mar 20, 2019
 * @author Period: TODO
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources: TODO
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders; // Key = trader name

    private Set<Trader> loggedTraders;

    private StockExchange exchange;


    /**
     * Constructs a new stock exchange object.
     * 
     * @param exchange2
     *            The stock exchange
     */
    public Brokerage( StockExchange exchange2 )
    {
        exchange = exchange2;
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();

    }


    /**
     * Tries to register a new trader with a given screen name and password. If
     * successful, creates a Trader object for this trader and adds this trader
     * to the map of all traders (using the screen name as the key).
     * 
     * @param name
     *            - the screen name of the trader.
     * @param password
     *            - the password for the trader.
     * @return 0 if successful, or an error code (a negative integer) if failed:
     *         -1 -- invalid screen name (must be 4-10 chars) -2 -- invalid
     *         password (must be 2-10 chars) -3 -- the screen name is already
     *         taken.
     */
    public int addUser( String name, String password )
    {
        if ( !( ( name.length() >= 4 ) && ( name.length() <= 10 ) ) )
        {
            return -1;
        }
        if ( !( ( password.length() >= 2 ) && ( password.length() <= 10 ) ) )
        {
            return -2;
        }
        if ( traders.containsKey( name ) )
        {
            return -1;
        }

        traders.put( name, new Trader( this, name, password ) );
        return 0;
    }


    /**
     * Tries to login a trader with a given screen name and password. If no
     * messages are waiting for the trader, sends a "Welcome to SafeTrade!"
     * message to the trader. Opens a dialog window for the trader by calling
     * trader's openWindow() method. Adds the trader to the set of all logged-in
     * traders.
     * 
     * @param name
     *            - the screen name of the trader.
     * @param password
     *            - the password for the trader.
     * @return 0 if successful, or an error code (a negative integer) if failed:
     *         -1 -- screen name not found -2 -- invalid password -3 -- user is
     *         already logged in.
     */
    public int login( String name, String password )
    {

        Trader trader = traders.get( name );
        if ( trader == null )
        {
            return -1;
        }
        if ( !trader.getPassword().equals( password ) )
        {
            return -2;
        }
        if ( loggedTraders.contains( trader ) )
        {
            return -3;
        }

        if ( !trader.hasMessages() )
        {
            trader.receiveMessage( "Welcome to SafeTrade!" );
        }

        trader.openWindow();
        loggedTraders.add( trader );
        return 0;
    }


    /**
     * 
     * Removes a specified trader from the set of logged-in traders.
     * 
     * @param trader
     *            - the trader that logs out.
     */
    public void logout( Trader trader )
    {
        loggedTraders.remove( trader );

    }


    /**
     * 
     * Requests a quote for a given stock from the stock exachange and passes it
     * along to the trader by calling trader's receiveMessage method.
     * 
     * @param symbol-
     *            the stock symbol.
     * @param trader-
     *            the stock symbol.
     */
    public void getQuote( String symbol, Trader trader )
    {
        trader.receiveMessage( exchange.getQuote( symbol ) );

    }


    /**
     * Places an order at the stock exchange.
     * 
     * @param order
     *            - an order to be placed at the stock exchange.
     */
    public void placeOrder( TradeOrder order )
    {
        exchange.placeOrder( order );

    }


    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }


    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }


    protected StockExchange getExchange()
    {
        return exchange;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
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
