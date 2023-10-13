package cs1302.api;

import com.google.gson.annotations.SerializedName;

/**
 * Class to access the team data inside the JSon Response.
 */
public class TeamData {
    String abbreviation;
    @SerializedName("full_name") String fullName;

} //TeamData
