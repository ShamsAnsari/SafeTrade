
// TODO BY SHAMS ANSARI
/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    private boolean ascending;


    /**
     * Constructs a price comparator that compares two orders in ascending
     * order. Sets the private boolean ascending flag to true.
     */
    public PriceComparator()
    {
        ascending = true;
    }


    /**
     * Constructs a price comparator that compares two orders in ascending or
     * descending order. The order of comparison depends on the value of a given
     * parameter. Sets the private boolean ascending flag to asc.
     * 
     * @param asc
     *            - if true, make an ascending comparator; otherwise make a
     *            descending comparator/.
     */
    public PriceComparator( boolean asc )
    {
        ascending = asc;
    }


    /**
     * Compares two trade orders.
     * 
     * @param o1
     *            - the first order
     * @param o2
     *            - the second order
     * @return 0 if both orders are market orders; -1 if order1 is market and
     *         order2 is limit; 1 if order1 is limit and order2 is market; the
     *         difference in prices, rounded to the nearest cent, if both order1
     *         and order2 are limit orders. In the latter case, the difference
     *         returned is cents1 - cents2 or cents2 - cents1, depending on
     *         whether this is an ascending or descending comparator (ascending
     *         is true or false).
     */
    @Override
    public int compare( TradeOrder o1, TradeOrder o2 )
    {
        if ( o1.isMarket() && o2.isMarket() )// both are equal
        {
            return 0;
        }
        if ( o1.isMarket() && o2.isLimit() )// o1 one is less than o2
        {
            return -1;
        }
        if ( o1.isLimit() && o2.isMarket() )// o1 is greater than o2
        {
            return 1;
        }
        else
        {
            if ( ascending )
            {
                return Math.round( o1.getPrice() - o2.getPrice() );// TODO CHECK
                                                                   // is the
                                                                   // price in
                                                                   // cents,
                                                                   // what is
                                                                   // ascending
                                                                   // order vs
                                                                   // descending
                                                                   // order?

            }
            else
            {
                return Math.round( o2.getPrice() - o1.getPrice() );// TODO CHECK
            }

        }

    }

}
