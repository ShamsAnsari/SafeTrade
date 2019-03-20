import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;


// TODO BY SHAMS ANSARI
/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

    private String stockSymbol;

    private String companyName;

    private double loPrice, hiPrice, lastPrice;

    private int volume;

    private PriorityQueue<TradeOrder> buyOrders, sellOrders;


    /**
     * Constructs a new stock with a given symbol, company name, and starting
     * price. Sets low price, high price, and last price to the same opening
     * price. Sets "day" volume to zero. Initializes a priority queue for sell
     * orders to an empty PriorityQueue with a PriceComparator configured for
     * comparing orders in ascending order; initializes a priority queue for buy
     * orders to an empty PriorityQueue with a PriceComparator configured for
     * comparing orders in descending order.
     * 
     * @param stockSymbol
     *            - the symbol of the stock, like "MSFT." A String
     * @param companyName
     *            - the name of the company, a String.
     * @param startingPrice
     *            - the starting price of the stock, a double.
     */
    public Stock( String stockSymbol, String companyName, double startingPrice )
    {

        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        lastPrice = loPrice = hiPrice = startingPrice;
        volume = 0;
        sellOrders = new PriorityQueue<TradeOrder>( new PriceComparator() );
        // Check
        buyOrders = new PriorityQueue<TradeOrder>(
            new PriceComparator( false ) ); // Check
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
    public String getQuote()
    {
        // Should 'money' be #.00?
        // How can a stock not exist?

        // Line2
        String lastPrice = money.format( this.lastPrice );
        String hiPrice = money.format( this.hiPrice );
        String loPrice = money.format( this.loPrice );

        // Line3
        String ask;
        if ( sellOrders.isEmpty() )
        {
            ask = "Ask: none";
        }
        else
        {
            ask = "Ask: " + money.format( sellOrders.peek().getPrice() )
                + " size: " + sellOrders.peek().getShares();
        }
        String bid;
        if ( buyOrders.isEmpty() )
        {
            bid = "Bid: none";
        }
        else
        {
            bid = "Bid: " + money.format( buyOrders.peek().getPrice() )
                + " size: " + buyOrders.peek().getShares();

        }

        String line1 = companyName + " (" + stockSymbol + ")";
        String line2 = "Price: " + lastPrice + "  hi: " + hiPrice + "  lo: "
            + loPrice + "  vol: " + volume;
        String line3 = ask + "  " + bid;

        String quote = line1 + "\n" + line2 + "\n" + line3;
        return quote;
    }


    /**
     * TODO Write your method description here.
     */
    protected void executeOrders()
    {
        TradeOrder sellOrder = sellOrders.peek();
        TradeOrder buyOrder = buyOrders.peek();
        double sellOrderPrice = new Double(
            money.format( sellOrder.getPrice() ) );
        double buyOrderPrice = new Double(
            money.format( buyOrder.getPrice() ) );

        if ( ( sellOrder.isLimit() && buyOrder.isLimit() )
            && ( buyOrderPrice >= sellOrderPrice ) )
        {
            
        }
       // else if((sellOrder))
        //TODO
    }


    /**
     * 
     * Places a trading order for this stock. Adds the order to the appropriate
     * priority queue depending on whether this is a buy or sell order. Notifies
     * the trader who placed the order that the order has been placed, by
     * sending a message to that trader.
     * 
     * @param order
     *            - a trading order to be placed.
     */
    public void placeOrder( TradeOrder order )
    {
        // TODO How to get the company?(+ order.getCompany();) this message
        // is missing the company name

        String message = "ERROR: Stock.java --> placeOrder";
        if ( order.isBuy() )
        {
            buyOrders.add( order );
            if ( order.isLimit() )
            {

                message = "New order: Buy " + order.getSymbol() + "\n"
                    + order.getShares() + " shares at $" + order.getPrice();

            }
            else if ( order.isMarket() )
            {
                message = "New order: Buy " + order.getSymbol() + "\n"
                    + order.getShares() + " shares at market";
            }
            order.getTrader().receiveMessage( message );
        }
        else if ( order.isSell() )
        {
            sellOrders.add( order );
            if ( order.isLimit() )
            {

                message = "New order: Sell " + order.getSymbol() + "\n"
                    + order.getShares() + " shares at $" + order.getPrice();

            }
            else if ( order.isMarket() )
            {
                message = "New order: Sell " + order.getSymbol() + "\n"
                    + order.getShares() + " shares at market";
            }
            order.getTrader().receiveMessage( message );

        }
    }


    //
    // The following are for test purposes only
    //

    protected String getStockSymbol()
    {
        return stockSymbol;
    }


    protected String getCompanyName()
    {
        return companyName;
    }


    protected double getLoPrice()
    {
        return loPrice;
    }


    protected double getHiPrice()
    {
        return hiPrice;
    }


    protected double getLastPrice()
    {
        return lastPrice;
    }


    protected int getVolume()
    {
        return volume;
    }


    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }


    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
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
