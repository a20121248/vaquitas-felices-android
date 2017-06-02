package com.github.alvarosct.happycows.data.source.remote;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvaro Santa Cruz on 30/03/2017.
 * Special thanks to Ruby Lopez and the OPEAR Team
 */

public class ApiError {

    public ApiError(String message){
        this.message = message;
        this.reasons = new ArrayList<>();
    }

    @SerializedName("message")
    private String message;

    @SerializedName("reasons")
    private List<String> reasons;

    public String getMessage() {
        return message;
    }

    public List<String> getReasons() {
        return reasons;
    }

}