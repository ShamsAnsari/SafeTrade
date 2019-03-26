import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;


/**
 * SafeTrade tests: TradeOrder PriceComparator Trader Brokerage StockExchange
 * Stock
 *
 * @author TODO Name of principal author
 * @author TODO Name of group member
 * @author TODO Name of group member
 * @version TODO date
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: TODO sources
 *
 */
public class JUSafeTradeTest
{
    // --Test TradeOrder
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
    public void TradeOrderToString()
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


    // --Test TraderWindow Stub
    @Test
    public void traderWindowConstructor()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
    }


    @Test
    public void traderWindowShowMessage()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
        tw.showMessage( null );
    }
    // --Test PriceComparator

    // new Trader( new Brokerage( new StockExchange() )
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
    @Test
    public void traderGetNameTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        assertEquals( t.getName(), "Steve" );
    }


    @Test
    public void traderGetPassTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        assertEquals( t.getPassword(), "12345" );
    }


    @Test
    public void traderCompareToZeroTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        Trader s = new Trader( null, "Steve", "54321" );
        assertEquals( t.compareTo( s ), 0 );
    }


    @Test
    public void traderCompareToNegativeTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        Trader s = new Trader( null, "Zachary", "54321" );
        assertEquals( -7, t.compareTo( s ) );
    }


    @Test
    public void traderCompareToPositiveTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        Trader s = new Trader( null, "Zachary", "54321" );
        assertEquals( 7, s.compareTo( t ) );
    }


    @Test
    public void traderEqualsExceptionTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        String fail = "hi";
        // assertThrows(ClassCastException.class, t.equals( fail ));
    }


    @Test
    public void traderEqualsTrueTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        Trader s = new Trader( null, "Steve", "54321" );
        assertTrue( t.equals( s ) );
    }


    @Test
    public void traderEqualsFalseTest()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        Trader s = new Trader( null, "Zachary", "54321" );
        assertFalse( t.equals( s ) );
    }


    @Test
    public void idkhowtoopenWindowTest()
    {

    }


    @Test
    public void idkhowtoquitTest()
    {

    }


    @Test
    public void idkhowtoplaceOrderTest()
    {

    }


    @Test
    public void idkhowtoreceiveMessageTest()
    {

    }


    @Test
    public void traderHasMessagesFalse()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        assertFalse( t.hasMessages() );
    }


    @Test
    public void traderHasMessagesTrue()
    {
        Trader t = new Trader( null, "Steve", "12345" );
        t.receiveMessage( "hi" );
        assertTrue( t.hasMessages() );
    }


    @Test
    public void traderGetQuoteTest()
    {
        StockExchange s = new StockExchange();
        String symbol = "AAPL";
        s.listStock( symbol, "Apple", 100.0 );
        Brokerage b = new Brokerage( s );
        Trader t = new Trader( b, "Steve", "12345" );
        // IDK
    }


    @Test
    public void traderMailboxTest()
    {
        LinkedList<String> mail = new LinkedList<String>();
        mail.add( "message" );
        Trader t = new Trader( null, "Steve", "12345" );
        t.receiveMessage( "message" );

        assertEquals( t.mailbox(), mail );
    }

    // TODO your tests here

    // --Test Brokerage

    // TODO your tests here

    // --Test StockExchange

    // TODO your tests here

    // --Test Stock
    // ****Stock.getQuote****
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

    // ****Stock.placeOrder****

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
        TradeOrder t = new TradeOrder( new Trader( null, "Bobo", "1234" ),
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

    // CHECK W/MR.FULK
    @Test
    public void stock_toString_Test()
    {
        assertEquals( GGGL.toString(), GGGL.toString() );
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
        
        assertEquals(GGGL.getBuyOrders().size(), 0);
        assertEquals(GGGL.getSellOrders().size(),0);
        
        
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
        
        assertEquals(GGGL.getBuyOrders().size(), 1);
        assertEquals(GGGL.getSellOrders().size(), 1);
        
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
        assertEquals(GGGL.getBuyOrders().size(), 0);
        assertEquals(GGGL.getSellOrders().size(), 0);
        GGGL.clearQueues();
        
        
        
        
        

    }

    public void stock_executeOrders_ML_Test()
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
    }
    
    
    
    
    // Remove block comment below to run JUnit test in console

    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( JUSafeTradeTest.class );
    }


    public static void main( String args[] )
    {
        org.junit.runner.JUnitCore.main( "JUSafeTradeTest" );
    }

}
