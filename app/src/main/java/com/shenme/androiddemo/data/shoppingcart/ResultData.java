package com.shenme.androiddemo.data.shoppingcart;

import com.google.gson.annotations.SerializedName;
import com.shenme.androiddemo.data.user.Customer;

import java.util.List;

/**
 * Created by CANC on 2016/11/30.
 */

public class ResultData {
    @SerializedName("user")
    public Customer user;
    @SerializedName("orderEntries")
    public List<Entry> orderEntries;

    @SerializedName("orderCode")
    private String orderCode;
    @SerializedName("totalPrice")
    private String totalPrice;
    @SerializedName("subTotalPrice")
    private String subTotalPrice;
    @SerializedName("deliverycost")
    private String deliverycost;
    @SerializedName("paymentMode")
    private String paymentMode;
    @SerializedName("deliveryMode")
    private String deliveryMode;
    @SerializedName("calculatedFlag")
    private String calculatedFlag;
    @SerializedName("isInvoice")
    private String isInvoice;
    @SerializedName("invoice")
    private String invoice;
    @SerializedName("notes")
    private String notes;
    @SerializedName("totalWeight")
    private String totalWeight;
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("modifyDate")
    private String modifyDate;
    @SerializedName("deliveryAddress")
    private String deliveryAddress;
    @SerializedName("orderStatus")
    private String orderStatus;
    @SerializedName("paymentStatus")
    private String paymentStatus;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(String subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public String getDeliverycost() {
        return deliverycost;
    }

    public void setDeliverycost(String deliverycost) {
        this.deliverycost = deliverycost;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getCalculatedFlag() {
        return calculatedFlag;
    }

    public void setCalculatedFlag(String calculatedFlag) {
        this.calculatedFlag = calculatedFlag;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
