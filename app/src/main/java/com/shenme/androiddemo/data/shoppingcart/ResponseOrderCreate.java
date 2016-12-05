package com.shenme.androiddemo.data.shoppingcart;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.base.BaseResult;

/**
 * Created by CANC on 2016/11/28.
 */

public class ResponseOrderCreate extends BaseResult {
    @SerializedName("resultData")
    public ResultData resultData;


}
