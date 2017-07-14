package com.github.alvarosct.happycows.data.source.remote;

import android.text.TextUtils;

import com.github.alvarosct.ascthelper.utils.UtilMethods;
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
        if (!TextUtils.isEmpty(message)){
            return message;
        } else {
            if (reasons!= null & reasons.size() != 0){
                return reasons.get(0);
            }
        }
        return "Ha ocurrido un error";
    }

    public List<String> getReasons() {
        return reasons;
    }

}