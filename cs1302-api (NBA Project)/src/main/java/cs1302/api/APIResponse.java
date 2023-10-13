package cs1302.api;

/**
 * Class that takes the response from the API so that GSON can be used.
 */

public class APIResponse {

    DataItem[] data;
    int dataCount;

    /**
     * Returns the dataItem.
     * @return the dataItem.
     */
    public DataItem[] getData() {
        return data;
    }

} //APIResult
