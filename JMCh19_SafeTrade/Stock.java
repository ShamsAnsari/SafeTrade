import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;


/**
 * Represents a stock in the SafeTrade project
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

        buyOrders = new PriorityQueue<TradeOrder>(
            new PriceComparator( false ) );
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
     * Executes as many pending orders as possible. 1. Examines the top sell
     * order and the top buy order in the respective priority queues. i) If both
     * are limit orders and the asking price is less than or equal to the
     * selling price, executes the order (or a part of it) at the sell order
     * price. ii) If one order is limit and the other is market, executes the
     * order (or a part of it) at the limit order price iii) If both orders are
     * market, executes the order (or a part of it) at the last sale price. 2.
     * Figures out how many shares can be traded, which is the smallest of the
     * numbers of shares in the two orders. 3. Subtracts the traded number of
     * shares from each order; Removes each of the orders with 0 remaining
     * shares from the respective queue. 4. Updates the day's low price, high
     * price, and volume. 5. Sends a message to each of the two traders involved
     * in the transaction. For example: You bought: 150 GGGL at 38.00 amt
     * 5700.00 6. Repeats steps 1-5 for as long as possible, that is as long as
     * there is any movement in the buy / sell order queues. (The process gets
     * stuck when the top buy order and sell order are both limit orders and the
     * ask price is higher than the bid price.)
     */
    @SuppressWarnings("deprecation")
    protected void executeOrders()
    {
        while ( !buyOrders.isEmpty() && !sellOrders.isEmpty() )
        {
            TradeOrder sellOrder = sellOrders.peek();
            TradeOrder buyOrder = buyOrders.peek();

            Trader sellTrader = sellOrder.getTrader();
            Trader buyTrader = buyOrder.getTrader();

            double sellOrderPrice = new Double(
                money.format( sellOrder.getPrice() ) );
            double buyOrderPrice = new Double(
                money.format( buyOrder.getPrice() ) );

            int sharesSell = sellOrder.getShares();
            int sharesBuy = buyOrder.getShares();

            /*
             * If the seller wants to sell at a certain price and the buy wants
             * to buy at a certain price. Then checks if the buyers' price is
             * greater than than the sellers' price. Because a sale can only
             * happen if the buying price is greater than or equal to the
             * selling price. Executes the order at the seller's price.
             * 
             */
            if ( ( sellOrder.isLimit() && buyOrder.isLimit() )
                && ( buyOrderPrice >= sellOrderPrice ) )
            {
                execHelper( sharesSell,
                    sharesBuy,
                    sellOrderPrice,
                    sellTrader,
                    buyTrader );
            }
            /*
             * If the seller wants to sell at a certain price and the buyer
             * wants to sell at the market price("last sale price"). Then checks
             * that the selling price is lower than or equal the market price.
             * Because the sale can only be made if the buying price is greater
             * than or equal to the selling price. Executes the order at the
             * seller's price
             */
            else if ( ( sellOrder.isLimit() && buyOrder.isMarket() )
                && ( sellOrderPrice <= lastPrice ) )
            {

                execHelper( sharesSell,
                    sharesBuy,
                    sellOrderPrice,
                    sellTrader,
                    buyTrader );
            }
            /*
             * If the seller wants to sell at market price and the buyer wants
             * to buy at a certain price. Then checks if the buying price is
             * greater than or equal to the lastPrice, which is the market
             * price. Executes the order at the buyer's price.
             */
            else if ( ( sellOrder.isMarket() && buyOrder.isLimit() )
                && ( buyOrderPrice >= lastPrice ) )
            {

                execHelper( sharesSell,
                    sharesBuy,
                    buyOrderPrice,
                    sellTrader,
                    buyTrader );

            }
            /*
             * If the seller wants to sell at the market price and the buyer
             * wants to buy at the market. Executes the order at the last price.
             */
            else if ( sellOrder.isMarket() && buyOrder.isMarket() )
            {

                execHelper( sharesSell,
                    sharesBuy,
                    lastPrice,
                    sellTrader,
                    buyTrader );
            }

        }

    }


    /**
     * 
     * Adds the orders back to the respective queues if they are not empty
     * 
     * @param buyOrder
     *            - The buy order a TradeOrder object
     * @param sellOrder
     *            - The sell order a TradeOrder object
     */
    private void addToQueue( TradeOrder buyOrder, TradeOrder sellOrder )
    {
        if ( sellOrder.getShares() > 0 )
        {
            sellOrders.add( sellOrder );
        }
        if ( buyOrder.getShares() > 0 )
        {
            buyOrders.add( buyOrder );
        }
    }


    /**
     * 
     * See the step by step comments
     * 
     * @param sharesSell
     *            - (int) the shares the seller is selling
     * @param sharesBuy
     *            - (int) the shares the buyer is buying
     * @param sellingPrice
     *            - (double) the price at which the deal is being executed
     * @param sellTrader
     *            - (Trader) the trader who is selling
     * @param buyTrader
     *            - (Trader) the trader who is buying
     */
    private void execHelper(
        int sharesSell,
        int sharesBuy,
        double sellingPrice,
        Trader sellTrader,
        Trader buyTrader )
    {
        // Finds the stocks of the lower trade, b/c thats how many you
        // can trade

        int smallerShares = Math.min( sharesSell, sharesBuy );

        // Removes the orders from the queue
        TradeOrder sellOrder = sellOrders.remove();
        TradeOrder buyOrder = buyOrders.remove();
        lastPrice = sellingPrice;
        volume += smallerShares;

        // Subtracts the smaller # of shares from both, one will be left
        // 0
        sellOrder.subtractShares( smallerShares );
        buyOrder.subtractShares( smallerShares );

        // Adds the orders back to their respective queues iff the have
        // greater than 0 shares in the order
        addToQueue( buyOrder, sellOrder );

        // Updates the day's high's and lows
        // lastPrice = sellOrder.getPrice();
        // volume += smallerShares;
        loPrice = ( loPrice > sellingPrice ) ? sellingPrice : loPrice;
        hiPrice = ( loPrice < sellingPrice ) ? sellingPrice : loPrice;

        // Sends a messages both traders
        sellTrader.receiveMessage(
            "You sold: " + smallerShares + " " + stockSymbol + " at "
                + sellingPrice + " amt " + smallerShares * sellingPrice );
        buyTrader.receiveMessage(
            "You bought: " + smallerShares + " " + stockSymbol + " at "
                + sellingPrice + " amt " + smallerShares * sellingPrice );

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

        String message = "ERROR: Stock.java --> placeOrder";

        if ( order.isBuy() )
        {
            buyOrders.add( order );
            if ( order.isLimit() )
            {

                message = "New order: Buy " + order.getSymbol() + " ("
                    + companyName + ")\n" + order.getShares() + " shares at $"
                    + order.getPrice();

            }
            else if ( order.isMarket() )
            {
                message = "New order: Buy " + order.getSymbol() + " ("
                    + companyName + ")\n" + order.getShares()
                    + " shares at market";
            }
            order.getTrader().receiveMessage( message );// CORRECTLY WORKING
        }
        else if ( order.isSell() )
        {
            sellOrders.add( order );

            if ( order.isLimit() )
            {

                message = "New order: Sell " + order.getSymbol() + " ("
                    + companyName + ")\n" + order.getShares() + " shares at $"
                    + order.getPrice();

            }
            else if ( order.isMarket() )
            {
                message = "New order: Sell " + order.getSymbol() + " ("
                    + companyName + ")\n" + order.getShares()
                    + " shares at market";
            }
            order.getTrader().receiveMessage( message );// WORKING CORRECTLY

        }
        this.executeOrders();
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
