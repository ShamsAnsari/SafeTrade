import java.util.PriorityQueue;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * SafeTrade tests: TradeOrder PriceComparator Trader Brokerage StockExchange
 * Stock
 *
 * @author Elisa Yang
 * @author Leo Shaw
 * @author Shams Ansari
 * @version TODO date
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: TODO sources
 *
 */
public class JUSafeTradeTest
{

    /**
     * TradeOrder tests: TradeOrderConstructor - constructs TradeOrder and then
     * compare toString TradeOrderGetTrader - compares value returned to
     * constructed value TradeOrderGetSymbol - compares value returned to
     * constructed value TradeOrderIsBuy - compares value returned to
     * constructed value TradeOrderIsSell - compares value returned to
     * constructed value TradeOrderIsMarket - compares value returned to
     * constructed value TradeOrderIsLimit - compares value returned to
     * constructed value TradeOrderGetShares - compares value returned to
     * constructed value TradeOrderGetPrice - compares value returned to
     * constructed value TradeOrderSubtractShares - subtracts known value &
     * compares result returned by getShares to expected value
     */
    /**
     * GGGL
     */
    private String symbol = "GGGL";

    private boolean buyOrder = true;

    private boolean marketOrder = true;

    /**
     * 123
     */
    private int numShares = 123;

    /**
     * 24
     */
    private int numToSubtract = 24;

    /**
     * 123.45
     */
    private double price = 123.45;

    /**
     * Giggle.com
     */
    private String companyName = "Giggle.com";


    // =========================================================================
    // *********************************TRADEORDER******************************
    // =========================================================================

    @Test
    public void tradeOrderConstructor()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        String toStr = to.toString();

        assertTrue( "<< Invalid TradeOrder Constructor >>",
            toStr.contains( "TradeOrder[Trader trader:null" )
                && toStr.contains( "java.lang.String symbol:" + symbol )
                && toStr.contains( "boolean buyOrder:" + buyOrder )
                && toStr.contains( "boolean marketOrder:" + marketOrder )
                && toStr.contains( "int numShares:" + numShares )
                && toStr.contains( "double price:" + price ) );
    }


    @Test
    public void tradeOrderToString()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNotNull( to.toString() );
    }


    @Test
    public void tradeOrderGetTrader()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNull( "<< TradeOrder: " + to.getTrader() + " should be null >>",
            to.getTrader() );
    }


    @Test
    public void tradeOrderGetSymbol()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertEquals( "<< TradeOrder: " + to.getTrader() + " should be "
            + symbol + " >>", symbol, to.getSymbol() );
    }


    @Test
    public void tradeOrderIsBuy()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        assertTrue(
            "<< TradeOrder: " + to.isBuy() + " should be " + buyOrder + " >>",
            to.isBuy() );
    }


    @Test
    public void tradeOrderIsSell()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertFalse(
            "<< TradeOrder: " + to.isSell() + " should be " + !buyOrder + " >>",
            to.isSell() );
    }


    @Test
    public void tradeOrderIsMarket()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue(
            "<< TradeOrder: " + to.isMarket() + " should be " + marketOrder
                + " >>",
            to.isMarket() );
    }


    @Test
    public void tradeOrderIsLimit()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        assertFalse(
            "<< TradeOrder: " + to.isLimit() + " should be " + !marketOrder
                + ">>",
            to.isLimit() );
    }


    @Test
    public void tradeOrderGetShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue(
            "<< TradeOrder: " + to.getShares() + " should be " + numShares
                + ">>",
            numShares == to.getShares()
                || ( numShares - numToSubtract ) == to.getShares() );
    }


    @Test
    public void tradeOrderGetPrice()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertEquals( "<< TradeOrder: " + to.getPrice() + " should be " + price
            + ">>", price, to.getPrice(), 0.0 );
    }


    @Test
    public void tradeOrderSubtractShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        to.subtractShares( numToSubtract );
        assertEquals(
            "<< TradeOrder: subtractShares(" + numToSubtract + ") should be "
                + ( numShares - numToSubtract ) + ">>",
            numShares - numToSubtract,
            to.getShares() );
    }

    // --Test PriceComparator
    // =========================================================================
    // *****************************PRICECOMPARATOR*****************************
    // =========================================================================

    private TradeOrder tOrderMarket1 = new TradeOrder( null,
        null,
        true,
        true,
        0,
        0.0 );

    private TradeOrder tOrderMarket2 = new TradeOrder( null,
        null,
        true,
        true,
        0,
        0.0 );

    private TradeOrder tOrderLimit1 = new TradeOrder( null,
        null,
        true,
        false,
        0,
        100.0 );

    private TradeOrder tOrderLimit2 = new TradeOrder( null,
        null,
        true,
        false,
        0,
        75.0 );


    @Test
    public void priceComparatorMMTest()
    {
        PriceComparator pc = new PriceComparator();
        int mm = pc.compare( tOrderMarket1, tOrderMarket2 );
        assertEquals( mm, 0 );

    }


    @Test
    public void priceComparatorMLTest()
    {
        PriceComparator pc = new PriceComparator();
        int ml = pc.compare( tOrderMarket1, tOrderLimit1 );
        assertEquals( ml, -1 );
    }


    @Test
    public void priceComparatorLMTest()
    {
        PriceComparator pc = new PriceComparator();
        int lm = pc.compare( tOrderLimit1, tOrderMarket1 );
        assertEquals( lm, 1 );
    }


    @Test
    public void priceComparatorLLTrueTest()
    {
        PriceComparator pc = new PriceComparator();
        int ll = pc.compare( tOrderLimit1, tOrderLimit2 );
        assertEquals( ll, 2500 );
    }


    @Test
    public void priceComparatorLLFalseTest()
    {
        PriceComparator pc = new PriceComparator( false );
        int ll = pc.compare( tOrderLimit1, tOrderLimit2 );
        assertEquals( ll, -2500 );
    }

    // --Test Trader
    // Take out jacocoData synthetic Java field


    // =========================================================================
    // *******************************TRADER************************************
    // =========================================================================

    @Test
    public void traderConstructorTest()
    {
        StockExchange s = new StockExchange();
        Brokerage b = new Brokerage( s );
        Trader t = new Trader( b, "trader", "password" );

        String str = "Trader[Brokerage brokerage, java.lang.String screenName:trader, "
            + "java.lang.String password:password, TraderWindow myWindow:null, "
            + "java.util.Queue mailbox:[]]";
        t.clearMail();
        assertEquals( str,
            t.toString().substring( 0, str.length() - 1 ) + "]" );
    }


    @Test
    public void traderGetNameTest()
    {
        Trader t = new Trader( null, "Steve", "password" );
        assertEquals( "Steve", t.getName() );
    }


    @Test
    public void traderGetPassTest()
    {
        Trader t = new Trader( null, "Steve", "password" );
        assertEquals( "password", t.getPassword() );
    }


    @Test(expected = ClassCastException.class)
    public void traderEqualsTest()
    {

        Trader t1 = new Trader( new Brokerage( new StockExchange() ),
            "Zach",
            "pass" );
        Trader t2 = new Trader( new Brokerage( new StockExchange() ),
            "Zach",
            "word" );
        String t3 = "Zach";
        Trader Bobo = new Trader( new Brokerage( new StockExchange() ),
            "Bobo",
            "word" );
        assertTrue( t1.equals( t2 ) );
        assertFalse( Bobo.equals( t1 ) );
        t1.equals( t3 );

    }


    @Test
    public void traderCompareToTest()
    {
        Trader t1 = new Trader( null, "Zach", "pass" );
        Trader t2 = new Trader( null, "Zach", "word" );
        Trader t3 = new Trader( null, "Zachary", "password" );

        assertEquals( -3, t1.compareTo( t3 ) );
        assertEquals( 0, t1.compareTo( t2 ) );
    }


    @Test
    public void traderOpenWindowTest()
    {
        Trader t1 = new Trader( null, "Zach", "pass" );
        t1.openWindow();

        assertEquals( false, t1.hasMessages() );
    }


    @Test
    public void traderHasMessagesTest()
    {
        Trader t1 = new Trader( null, "Zach", "pass" );

        t1.mailbox().add( "message" );

        assertTrue( t1.hasMessages() );
    }


    @Test
    public void traderGetQuoteTest()
    {
        StockExchange s = new StockExchange();
        Brokerage b = new Brokerage( s );
        s.listStock( "AAPL", "Apple", 1000.0 );
        Trader t1 = new Trader( b, "Zach", "pass" );

        int initSize = t1.mailbox().size();
        t1.getQuote( "AAPL" );

        assertEquals( initSize + 1, t1.mailbox().size() );
    }


    @Test
    public void traderReceiveMessageTest()
    {

        Trader t1 = new Trader( null, "Zach", "pass" );
        t1.receiveMessage( "msg" );
        assertTrue( t1.hasMessages() );
        t1.openWindow();
        t1.receiveMessage( "msg2" );
        assertFalse( t1.hasMessages() );
    }


    @Test
    public void traderPlaceOrderTest_OVERALL_PLACEORDER_TEST()
    {

        StockExchange NYSE = new StockExchange();
        Brokerage MorganStanley = new Brokerage( NYSE );
        Stock stock = new Stock( "AAPL", "Apple", 189.29 );
        NYSE.listStock( "AAPL", "Apple", 189.29 );
        Trader Clare = new Trader( MorganStanley, "Clare", "pass" );
        TradeOrder order = new TradeOrder( Clare,
            "AAPL",
            buyOrder,
            marketOrder,
            numShares,
            189.29 );

        // Trader.placeOrder
        Clare.placeOrder( order );
        assertTrue( Clare.hasMessages() );
        System.out.println( Clare.mailbox().remove() );
        assertFalse( Clare.hasMessages() );

        // Stock.placeOrder
        stock.placeOrder( order );
        assertTrue( Clare.hasMessages() );
        System.out.println( Clare.mailbox().remove() );
        assertFalse( Clare.hasMessages() );

        // Brokerage.placeOrder
        MorganStanley.placeOrder( order );
        assertTrue( Clare.hasMessages() );
        System.out.println( Clare.mailbox().remove() );
        assertFalse( Clare.hasMessages() );

        // StockExchange.placeOrder
        NYSE.placeOrder( order );
        assertTrue( Clare.hasMessages() );
        System.out.println( Clare.mailbox().remove() );
        assertFalse( Clare.hasMessages() );

        // Null Order
        TradeOrder nullOrder = new TradeOrder( null,
            "AAPL",
            buyOrder,
            marketOrder,
            numShares,
            price );
        Clare.placeOrder( nullOrder );
        assertFalse( Clare.hasMessages() );
       // System.out.println( Clare.mailbox().remove() );
        assertFalse( Clare.hasMessages() );

    }


    @Test
    public void traderQuitTest()
    {
        StockExchange s = new StockExchange();
        Brokerage b = new Brokerage( s );
        Trader t1 = new Trader( b, "Bobo", "pass" );
        b.addUser( "Zach", "pass" );
        b.login( "Zach", "pass" );
        t1.quit();
        assertFalse( b.getLoggedTraders().contains( t1 ) );

    }


    @Test
    public void traderToStringTest()
    {
        Trader t1 = new Trader( null, "Zach", "pass" );
        assertNotNull( t1.toString() );
    }

    // =========================================================================
    // ******************************STOCK**************************************
    // =========================================================================

    private Stock GGGL = new Stock( symbol, companyName, price );


    @Test
    public void stock_getQuote_SE_BE_Test()
    {
        String Actual = GGGL.getQuote();
        String Expected = "Giggle.com (GGGL)\n"
            + "Price: 123.45  hi: 123.45  lo: 123.45  vol: 0\n"
            + "Ask: none  Bid: none";
        assertEquals( Actual, Expected );
        GGGL.clearQueues();

    }


    @Test
    public void stock_getQuote_S_BE_Test()
    {
        TradeOrder t = new TradeOrder( new Trader( null, "Bobo", "1234" ),
            companyName,
            false, // sell Order
            true,
            5,
            25.0 );

        GGGL.placeOrder( t );
        String Actual = GGGL.getQuote();
        String Expected = "Giggle.com (GGGL)\n"
            + "Price: 123.45  hi: 123.45  lo: 123.45  vol: 0\n"
            + "Ask: 25.00 size: 5  Bid: none";

        assertEquals( Actual, Expected );
        GGGL.clearQueues();

    }


    @Test
    public void stock_getQuote_SE_B_Test()
    {
        TradeOrder t = new TradeOrder( new Trader( null, "Bobo", "1234" ),
            companyName,
            true, // buy Order
            true,
            5,
            25.0 );

        GGGL.placeOrder( t );
        String Actual = GGGL.getQuote();
        String Expected = "Giggle.com (GGGL)\n"
            + "Price: 123.45  hi: 123.45  lo: 123.45  vol: 0\n"
            + "Ask: none  Bid: 25.00 size: 5";
        // System.out.println(Actual);
        // System.out.println(Expected);

        assertEquals( Actual, Expected );
        GGGL.clearQueues();

    }


    @Test
    public void stock_placeOrder_BL_Test()
    {
        GGGL.clearQueues();
        TradeOrder t = new TradeOrder( new Trader( null, "Bobo", "1234" ),
            symbol,
            true, // buyOrder
            false, // limit order
            numShares,
            price );

        GGGL.placeOrder( t );

        PriorityQueue<TradeOrder> buyOrders = GGGL.getBuyOrders();

        String actual = buyOrders.remove().toString();
        String expected = t.toString();
        assertEquals( actual, expected );
        GGGL.clearQueues();

    }


    @Test
    public void stock_placeOrder_BM_Test()
    {
        GGGL.clearQueues();
        StockExchange s = new StockExchange();
        Brokerage b = new Brokerage( s );
        Trader Bobo = new Trader( b, "Bobo", "1234" );
        TradeOrder t = new TradeOrder( Bobo,
            symbol,
            true, // buyOrder
            true, // market order
            numShares,
            price );

        GGGL.placeOrder( t );

        PriorityQueue<TradeOrder> buyOrders = GGGL.getBuyOrders();

        String actual = buyOrders.remove().toString();
        String expected = t.toString();
        assertEquals( actual, expected );
        assertTrue( Bobo.hasMessages() );
        // System.out.println(Bobo.mailbox().peek());

        GGGL.clearQueues();

    }


    @Test
    public void stock_placeOrder_SL_Test()
    {
        GGGL.clearQueues();
        TradeOrder t = new TradeOrder( new Trader( null, "Bobo", "1234" ),
            symbol,
            false, // buyOrder
            false, // limit order
            numShares,
            price );

        GGGL.placeOrder( t );

        PriorityQueue<TradeOrder> sellOrders = GGGL.getSellOrders();

        String actual = sellOrders.remove().toString();
        String expected = t.toString();
        assertEquals( actual, expected );
        GGGL.clearQueues();

    }


    @Test
    public void stock_protectedMethods_Test()
    {
        assertEquals( GGGL.getStockSymbol(), "GGGL" );
        assertEquals( GGGL.getCompanyName(), "Giggle.com" );
        assertEquals( GGGL.getLoPrice(), 123.45, 0.001 );
        assertEquals( GGGL.getHiPrice(), 123.45, 0.001 );
        assertEquals( GGGL.getLastPrice(), 123.45, 0.001 );
        assertEquals( GGGL.getVolume(), 0 );

    }


    @Test
    public void stock_toString_Test()
    {
        assertNotNull( GGGL.toString() );
    }


    @Test
    public void stock_executeOrders_LL_Test()
    {
        GGGL.clearQueues();
        TradeOrder tbl = new TradeOrder( new Trader( null, "Tommy", "1234" ),
            symbol,
            buyOrder,
            false,
            numShares,
            price );
        TradeOrder tsl = new TradeOrder( new Trader( null, "Philbert", "1234" ),
            symbol,
            false, // why does covered instructions go down if I change this to
                   // false, instead of !BuyOrder.
            false,
            numShares,
            price + 1.0 );
        GGGL.placeOrder( tbl );
        GGGL.placeOrder( tsl );

        assertEquals( GGGL.getSellOrders().size(), 1 );
        assertEquals( GGGL.getBuyOrders().size(), 1 );

        GGGL.clearQueues();
        tsl = new TradeOrder( new Trader( null, "Philbert", "1234" ),
            symbol,
            false, // why does covered instructions go down if I change this to
                   // false, instead of !BuyOrder.
            false,
            numShares,
            price );

        GGGL.placeOrder( tbl );
        GGGL.placeOrder( tsl );
        assertEquals( GGGL.getSellOrders().size(), 0 );
        assertEquals( GGGL.getBuyOrders().size(), 0 );

        GGGL.placeOrder( tsl );

        GGGL.clearQueues();

    }


    @Test
    public void stock_executeOrders_MM_Test()
    {
        GGGL.clearQueues();
        TradeOrder tbm = new TradeOrder( new Trader( null, "Tommy", "1234" ),
            symbol,
            buyOrder,
            true,
            numShares,
            price );
        TradeOrder tsm = new TradeOrder( new Trader( null, "Philbert", "1234" ),
            symbol,
            false, // why does covered instructions go down if I change this to
                   // false, instead of !BuyOrder.
            true,
            numShares,
            price + 1.0 );

        GGGL.placeOrder( tbm );
        GGGL.placeOrder( tsm );

        assertEquals( GGGL.getBuyOrders().size(), 0 );
        assertEquals( GGGL.getSellOrders().size(), 0 );

    }


    @Test
    public void stock_executeOrders_LM_Test()
    {
        GGGL.clearQueues();
        TradeOrder tbm = new TradeOrder( new Trader( null, "Tommy", "1234" ),
            symbol,
            buyOrder,
            true,
            numShares,
            price );
        TradeOrder tsl = new TradeOrder( new Trader( null, "Philbert", "1234" ),
            symbol,
            !buyOrder, // why does covered instructions go down if I change this
                       // to false, instead of !BuyOrder.
            false,
            numShares,
            price + 1.0 );
        GGGL.placeOrder( tbm );
        GGGL.placeOrder( tsl );

        assertEquals( GGGL.getBuyOrders().size(), 1 );
        assertEquals( GGGL.getSellOrders().size(), 1 );

        GGGL.clearQueues();
        tsl = new TradeOrder( new Trader( null, "Philbert", "1234" ),
            symbol,
            !buyOrder, // why does covered instructions go down if I change this
                       // to false, instead of !BuyOrder.
            false,
            numShares,
            price - 1.0 );
        GGGL.placeOrder( tbm );
        GGGL.placeOrder( tsl );
        assertEquals( GGGL.getBuyOrders().size(), 0 );
        assertEquals( GGGL.getSellOrders().size(), 0 );
        GGGL.clearQueues();

    }


    @Test
    public void stock_executeOrders_ML_Test()
    {
        GGGL.clearQueues();
        TradeOrder tbl = new TradeOrder( new Trader( null, "Tommy", "1234" ),
            symbol,
            true,
            false,
            numShares,
            price - 1.0 );
        TradeOrder tsm = new TradeOrder( new Trader( null, "Philbert", "1234" ),
            symbol,
            false, // why does covered instructions go down if I change this to
                   // false, instead of !BuyOrder.
            true,
            numShares,
            price );
        GGGL.placeOrder( tbl );
        GGGL.placeOrder( tsm );

        assertEquals( GGGL.getBuyOrders().size(), 1 );
        assertEquals( GGGL.getSellOrders().size(), 1 );

        GGGL.clearQueues();

        tbl = new TradeOrder( new Trader( null, "Tommy", "1234" ),
            symbol,
            true,
            false,
            numShares,
            price + 1.0 );
        GGGL.placeOrder( tbl );
        GGGL.placeOrder( tsm );

        assertEquals( GGGL.getBuyOrders().size(), 0 );
        assertEquals( GGGL.getSellOrders().size(), 0 );

    }


    @Test
    public void stock_addToQueue()
    {
        TradeOrder tbm = new TradeOrder( new Trader( null, "Tommy", "1234" ),
            symbol,
            buyOrder,
            true,
            numShares,
            price );
        GGGL.clearQueues();
        TradeOrder tsl = new TradeOrder( new Trader( null, "Philbert", "1234" ),
            symbol,
            !buyOrder, // why does covered instructions go down if I change this
                       // to false, instead of !BuyOrder.
            false,
            numShares + 1,
            price - 1.0 );

        GGGL.placeOrder( tbm );
        GGGL.placeOrder( tsl );

        assertEquals( GGGL.getBuyOrders().size(), 0 );
        assertEquals( GGGL.getSellOrders().size(), 1 );

        GGGL.clearQueues();

        tbm = new TradeOrder( new Trader( null, "Tommy", "1234" ),
            symbol,
            buyOrder,
            true,
            numShares + 2,
            price );
        GGGL.placeOrder( tbm );
        GGGL.placeOrder( tsl );

        assertEquals( GGGL.getBuyOrders().size(), 1 );
        assertEquals( GGGL.getSellOrders().size(), 0 );

    }


    // =========================================================================
    // *****************************BROKERAGE************************************
    // =========================================================================

    @Test
    public void brokeragetoString_Test()
    {
        Brokerage b = new Brokerage( new StockExchange() );
        assertNotNull( b.toString() );
    }

    // =========================================================================
    // =========================================================================
    // =========================================================================

    // // Remove block comment below to run JUnit test in console
    //
    // public static junit.framework.Test suite()
    // {
    // return new JUnit4TestAdapter( JUSafeTradeTest.class );
    // }
    //
    //
    // public static void main( String args[] )
    // {
    // org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
    // }

}
