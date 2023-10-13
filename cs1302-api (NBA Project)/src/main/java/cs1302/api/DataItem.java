package cs1302.api;

import com.google.gson.annotations.SerializedName;

/**
 * Class to access the data inside the JSon Response.
 */
public class DataItem {
    int id;
    @SerializedName("first_name") String firstName;
    @SerializedName("last_name") String lastName;
    String position;
    @SerializedName("height_feet") int heightFeet;
    @SerializedName("height_inches") int heightInches;
    @SerializedName("weight_pounds") int weightPounds;
    TeamData team;

    /**
     * Returns the player's id.
     * @return int value which is the player id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the player's weight.
     * @return int value which is the player weight.
     */
    public int getWeightPounds() {
        return weightPounds;
    }

} //DataItem
