package com.creditsapp.entity;

import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: Windows
 * Date: 26.09.14
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
public class BankDescription {
 private   ImageView imageView;
 private   int durationCredit;
 private   int monthlyPayment;

    public BankDescription(ImageView imageView, int durationCredit, int monthlyPayment) {
        this.imageView = imageView;
        this.durationCredit = durationCredit;
        this.monthlyPayment = monthlyPayment;
    }


    public BankDescription() {
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setDurationCredit(int durationCredit) {
        this.durationCredit = durationCredit;
    }

    public void setMonthlyPayment(int monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }


    public ImageView getImageView() {
        return imageView;
    }

    public int getDurationCredit() {
        return durationCredit;
    }

    public int getMonthlyPayment() {
        return monthlyPayment;
    }
}
