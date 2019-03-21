import java.lang.reflect.*;
import java.util.*;


/**
 * Represents a stock exchange. A <code>StockExchange</code> keeps a
 * <code>HashMap</code> of stocks, keyed by a stock symbol. It has methods to
 * list a new stock, request a quote for a given stock symbol, and to place a
 * specified trade order.
 *
 * 
 * @author Shams Ansari
 * @author 
 * @author 
 * @version Mar 20, 2019
 * @author Period: TODO
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources: TODO
 */
public class StockExchange
{
    private Map<String, Stock> listedStocks;


    public StockExchange()
    {
        listedStocks = new HashMap<String, Stock>();
    }


    /**
     * Returns a quote string for this stock. The quote includes: the company
     * name for this stock; the stock symbol; last sale price; the lowest and
     * highest day prices; the lowest price in a sell order (or "market") and
     * the number of shares in it (or "none" if there are no sell orders); the
     * highest price in a buy order (or "market") and the number of shares in it
     * (or "none" if there are no buy orders).
     * 
     * @return the quote for this stock.
     */
    public String getQuote( String symbol )
    {
        Stock stock = listedStocks.get( symbol );
        if ( stock == null )
        {
            return symbol + " not found";
        }
        return stock.getQuote();

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
        listedStocks.put(  symbol, new Stock( symbol, name, price ) );
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
        listedStocks.get( order.getSymbol() ).placeOrder( order );
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
