import java.lang.reflect.*;


/**
 * Represents a buy or sell order for trading a given number of shares of a
 * specified stock.
 * 
 * @author Elissa Yang
 * @author Shams Ansari
 * @author Leo Shaw
 * @version Mar 20, 2019
 * @author Period: 4
 * @author Assignment: JMCh19_SafeTrade
 *
 * @author Sources: none
 */
public class TradeOrder
{
    private Trader trader;

    private String symbol;

    private boolean buyOrder;

    private boolean marketOrder;

    private int numShares;

    private double price;


    /**
     * @param trader
     *            - The Trader
     * @param symbol
     *            - The Symbol
     * @param buyOrder
     *            - BuyORder
     * @param marketOrder
     *            boolean is market order
     * 
     * @param numShares
     *            the number of shares (int)
     * @param price
     *            the price of the stock(double)
     */
    public TradeOrder(
        Trader trader,
        String symbol,
        boolean buyOrder,
        boolean marketOrder,
        int numShares,
        double price )
    {
        this.trader = trader;
        this.symbol = symbol;
        this.buyOrder = buyOrder;
        this.marketOrder = marketOrder;
        this.numShares = numShares;
        this.price = price;
    }


    /**
     * @return the trader
     */
    public Trader getTrader()
    {
        return trader;
    }


    /**
     * @return the symbol
     */
    public String getSymbol()
    {
        return symbol;
    }


    /**
     * @return if it is buy
     */
    public boolean isBuy()
    {
        return buyOrder;
    }


    /**
     * @return if it is a sell
     */
    public boolean isSell()
    {
        return !buyOrder;
    }


    /**
     * @return if it is a market
     */
    public boolean isMarket()
    {
        return marketOrder;
    }


    /**
     * @return returns if it is a limit
     */
    public boolean isLimit()
    {
        return !marketOrder;
    }


    /**
     * @return returns shares
     */
    public int getShares()
    {
        return numShares;
    }


    /**
     * @return returns price
     */
    public double getPrice()
    {
        return price;
    }


    /**
     * @param shares
     *            number of shares
     */
    public void subtractShares( int shares )
    {
        numShares -= shares;
    }


    //
    // The following are for test purposes only
    //
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this TradeOrder.
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
