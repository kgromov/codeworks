package task43;

import java.util.List;

/**
 * Created by konstantin on 05.04.2020.
 */
public class PaginationHelper<I> {
    private final List<I> items;
    private final int itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> items, int itemsPerPage) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        int totalItems = items.size();
        int remainingItems = totalItems % itemsPerPage == 0 ? 0 : 1;
        return totalItems / itemsPerPage + remainingItems;
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        int pageCount = pageCount();
        if (pageIndex < 0 || pageIndex >= pageCount)
        {
            return -1;
        }
        return  pageIndex < pageCount - 1 ? itemsPerPage : itemCount() - (itemsPerPage * pageIndex);
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if ( itemIndex < 0 || itemIndex >= itemCount())
        {
            return -1;
        }
        return itemIndex / itemsPerPage;
    }
}
