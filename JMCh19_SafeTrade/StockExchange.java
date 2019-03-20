import java.lang.reflect.*;
import java.util.*;


// TODO BY SHAMS ANSARI
/**
 * Represents a stock exchange. A <code>StockExchange</code> keeps a
 * <code>HashMap</code> of stocks, keyed by a stock symbol. It has methods to
 * list a new stock, request a quote for a given stock symbol, and to place a
 * specified trade order.
 */
public class StockExchange
{
    private Map<String, Stock> listedStocks;


    public StockExchange()
    {
        listedStocks = new HashMap<String, Stock>( 2000 );
    }


    /**
     * Returns a quote for a given stock.
     * 
     * @param symbol
     *            Stock symbol
     * @return a text message that contains the quote
     */
    public String getQuote( String symbol )
    {
        // TODO method
        return null;// FIX THIS!!
    }


    /**
     * Adds a new stock with given parameters to the listed stocks.
     * 
     * @param symbol
     *            - stock symbol.
     * @param name
     *            - full company name.
     * @param price
     *            - opening stock price.
     */
    public void listStock( String symbol, String name, double price )
    {
        // TODO method
    }


    /**
     * Places a trade order by calling stock.placeOrder for the stock specified
     * by the stock symbol in the trade order.
     * 
     * @param order
     *            - a trading order to be placed with the stock exchange.
     */
    public void placeOrder( TradeOrder order )
    {
        // TODO method
    }


    // The following are for test purposes only
    //
    protected Map<String, Stock> getListedStocks()
    {
        return listedStocks;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this StockExchange.
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
