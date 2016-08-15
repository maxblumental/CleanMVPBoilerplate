package com.maxblumental.cleanboilerplate.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Rates {

    @SerializedName("AUD")
    @Expose
    private Double aUD;
    @SerializedName("BGN")
    @Expose
    private Double bGN;
    @SerializedName("BRL")
    @Expose
    private Double bRL;
    @SerializedName("CAD")
    @Expose
    private Double cAD;
    @SerializedName("CHF")
    @Expose
    private Double cHF;
    @SerializedName("CNY")
    @Expose
    private Double cNY;
    @SerializedName("CZK")
    @Expose
    private Double cZK;
    @SerializedName("DKK")
    @Expose
    private Double dKK;
    @SerializedName("GBP")
    @Expose
    private Double gBP;
    @SerializedName("HKD")
    @Expose
    private Double hKD;
    @SerializedName("HRK")
    @Expose
    private Double hRK;
    @SerializedName("HUF")
    @Expose
    private Double hUF;
    @SerializedName("IDR")
    @Expose
    private Double iDR;
    @SerializedName("ILS")
    @Expose
    private Double iLS;
    @SerializedName("INR")
    @Expose
    private Double iNR;
    @SerializedName("JPY")
    @Expose
    private Double jPY;
    @SerializedName("KRW")
    @Expose
    private Double kRW;
    @SerializedName("MXN")
    @Expose
    private Double mXN;
    @SerializedName("MYR")
    @Expose
    private Double mYR;
    @SerializedName("NOK")
    @Expose
    private Double nOK;
    @SerializedName("NZD")
    @Expose
    private Double nZD;
    @SerializedName("PHP")
    @Expose
    private Double pHP;
    @SerializedName("PLN")
    @Expose
    private Double pLN;
    @SerializedName("RON")
    @Expose
    private Double rON;
    @SerializedName("RUB")
    @Expose
    private Double rUB;
    @SerializedName("SEK")
    @Expose
    private Double sEK;
    @SerializedName("SGD")
    @Expose
    private Double sGD;
    @SerializedName("THB")
    @Expose
    private Double tHB;
    @SerializedName("TRY")
    @Expose
    private Double tRY;
    @SerializedName("USD")
    @Expose
    private Double uSD;
    @SerializedName("ZAR")
    @Expose
    private Double zAR;
    @SerializedName("EUR")
    @Expose
    private Double eUR;

    public Rates() {
        setAUD(0.0);
        setBGN(0.0);
        setBRL(0.0);
        setCAD(0.0);
        setCHF(0.0);
        setCNY(0.0);
        setCZK(0.0);
        setDKK(0.0);
        setGBP(0.0);
        setHKD(0.0);
        setHRK(0.0);
        setHUF(0.0);
        setIDR(0.0);
        setILS(0.0);
        setINR(0.0);
        setJPY(0.0);
        setKRW(0.0);
        setMXN(0.0);
        setMYR(0.0);
        setNOK(0.0);
        setNZD(0.0);
        setPHP(0.0);
        setPLN(0.0);
        setRON(0.0);
        setRUB(0.0);
        setSEK(0.0);
        setSGD(0.0);
        setTHB(0.0);
        setTRY(0.0);
        setUSD(0.0);
        setZAR(0.0);
    }

    /**
     * @return The aUD
     */
    public Double getAUD() {
        return aUD;
    }

    /**
     * @param aUD The AUD
     */
    public void setAUD(Double aUD) {
        this.aUD = aUD;
    }

    /**
     * @return The bGN
     */
    public Double getBGN() {
        return bGN;
    }

    /**
     * @param bGN The BGN
     */
    public void setBGN(Double bGN) {
        this.bGN = bGN;
    }

    /**
     * @return The bRL
     */
    public Double getBRL() {
        return bRL;
    }

    /**
     * @param bRL The BRL
     */
    public void setBRL(Double bRL) {
        this.bRL = bRL;
    }

    /**
     * @return The cAD
     */
    public Double getCAD() {
        return cAD;
    }

    /**
     * @param cAD The CAD
     */
    public void setCAD(Double cAD) {
        this.cAD = cAD;
    }

    /**
     * @return The cHF
     */
    public Double getCHF() {
        return cHF;
    }

    /**
     * @param cHF The CHF
     */
    public void setCHF(Double cHF) {
        this.cHF = cHF;
    }

    /**
     * @return The cNY
     */
    public Double getCNY() {
        return cNY;
    }

    /**
     * @param cNY The CNY
     */
    public void setCNY(Double cNY) {
        this.cNY = cNY;
    }

    /**
     * @return The cZK
     */
    public Double getCZK() {
        return cZK;
    }

    /**
     * @param cZK The CZK
     */
    public void setCZK(Double cZK) {
        this.cZK = cZK;
    }

    /**
     * @return The dKK
     */
    public Double getDKK() {
        return dKK;
    }

    /**
     * @param dKK The DKK
     */
    public void setDKK(Double dKK) {
        this.dKK = dKK;
    }

    /**
     * @return The gBP
     */
    public Double getGBP() {
        return gBP;
    }

    /**
     * @param gBP The GBP
     */
    public void setGBP(Double gBP) {
        this.gBP = gBP;
    }

    /**
     * @return The hKD
     */
    public Double getHKD() {
        return hKD;
    }

    /**
     * @param hKD The HKD
     */
    public void setHKD(Double hKD) {
        this.hKD = hKD;
    }

    /**
     * @return The hRK
     */
    public Double getHRK() {
        return hRK;
    }

    /**
     * @param hRK The HRK
     */
    public void setHRK(Double hRK) {
        this.hRK = hRK;
    }

    /**
     * @return The hUF
     */
    public Double getHUF() {
        return hUF;
    }

    /**
     * @param hUF The HUF
     */
    public void setHUF(Double hUF) {
        this.hUF = hUF;
    }

    /**
     * @return The iDR
     */
    public Double getIDR() {
        return iDR;
    }

    /**
     * @param iDR The IDR
     */
    public void setIDR(Double iDR) {
        this.iDR = iDR;
    }

    /**
     * @return The iLS
     */
    public Double getILS() {
        return iLS;
    }

    /**
     * @param iLS The ILS
     */
    public void setILS(Double iLS) {
        this.iLS = iLS;
    }

    /**
     * @return The iNR
     */
    public Double getINR() {
        return iNR;
    }

    /**
     * @param iNR The INR
     */
    public void setINR(Double iNR) {
        this.iNR = iNR;
    }

    /**
     * @return The jPY
     */
    public Double getJPY() {
        return jPY;
    }

    /**
     * @param jPY The JPY
     */
    public void setJPY(Double jPY) {
        this.jPY = jPY;
    }

    /**
     * @return The kRW
     */
    public Double getKRW() {
        return kRW;
    }

    /**
     * @param kRW The KRW
     */
    public void setKRW(Double kRW) {
        this.kRW = kRW;
    }

    /**
     * @return The mXN
     */
    public Double getMXN() {
        return mXN;
    }

    /**
     * @param mXN The MXN
     */
    public void setMXN(Double mXN) {
        this.mXN = mXN;
    }

    /**
     * @return The mYR
     */
    public Double getMYR() {
        return mYR;
    }

    /**
     * @param mYR The MYR
     */
    public void setMYR(Double mYR) {
        this.mYR = mYR;
    }

    /**
     * @return The nOK
     */
    public Double getNOK() {
        return nOK;
    }

    /**
     * @param nOK The NOK
     */
    public void setNOK(Double nOK) {
        this.nOK = nOK;
    }

    /**
     * @return The nZD
     */
    public Double getNZD() {
        return nZD;
    }

    /**
     * @param nZD The NZD
     */
    public void setNZD(Double nZD) {
        this.nZD = nZD;
    }

    /**
     * @return The pHP
     */
    public Double getPHP() {
        return pHP;
    }

    /**
     * @param pHP The PHP
     */
    public void setPHP(Double pHP) {
        this.pHP = pHP;
    }

    /**
     * @return The pLN
     */
    public Double getPLN() {
        return pLN;
    }

    /**
     * @param pLN The PLN
     */
    public void setPLN(Double pLN) {
        this.pLN = pLN;
    }

    /**
     * @return The rON
     */
    public Double getRON() {
        return rON;
    }

    /**
     * @param rON The RON
     */
    public void setRON(Double rON) {
        this.rON = rON;
    }

    /**
     * @return The rUB
     */
    public Double getRUB() {
        return rUB;
    }

    /**
     * @param rUB The RUB
     */
    public void setRUB(Double rUB) {
        this.rUB = rUB;
    }

    /**
     * @return The sEK
     */
    public Double getSEK() {
        return sEK;
    }

    /**
     * @param sEK The SEK
     */
    public void setSEK(Double sEK) {
        this.sEK = sEK;
    }

    /**
     * @return The sGD
     */
    public Double getSGD() {
        return sGD;
    }

    /**
     * @param sGD The SGD
     */
    public void setSGD(Double sGD) {
        this.sGD = sGD;
    }

    /**
     * @return The tHB
     */
    public Double getTHB() {
        return tHB;
    }

    /**
     * @param tHB The THB
     */
    public void setTHB(Double tHB) {
        this.tHB = tHB;
    }

    /**
     * @return The tRY
     */
    public Double getTRY() {
        return tRY;
    }

    /**
     * @param tRY The TRY
     */
    public void setTRY(Double tRY) {
        this.tRY = tRY;
    }

    /**
     * @return The uSD
     */
    public Double getUSD() {
        return uSD;
    }

    /**
     * @param uSD The USD
     */
    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

    /**
     * @return The zAR
     */
    public Double getZAR() {
        return zAR;
    }

    /**
     * @param zAR The ZAR
     */
    public void setZAR(Double zAR) {
        this.zAR = zAR;
    }

    public List<Money> toList() {
        return new ArrayList<>(asList(
                new Money(getAUD(), "AUD"),
                new Money(getBGN(), "BGN"),
                new Money(getBRL(), "BRL"),
                new Money(getCAD(), "CAD"),
                new Money(getCHF(), "CHF"),
                new Money(getCNY(), "CNY"),
                new Money(getCZK(), "CZK"),
                new Money(getDKK(), "DKK"),
                new Money(getGBP(), "GBP"),
                new Money(getHKD(), "HKD"),
                new Money(getHRK(), "HRK"),
                new Money(getHUF(), "HUF"),
                new Money(getIDR(), "IDR"),
                new Money(getILS(), "ILS"),
                new Money(getINR(), "INR"),
                new Money(getJPY(), "JPY"),
                new Money(getKRW(), "KRW"),
                new Money(getMXN(), "MXN"),
                new Money(getMYR(), "MYR"),
                new Money(getNOK(), "NOK"),
                new Money(getNZD(), "NZD"),
                new Money(getPHP(), "PHP"),
                new Money(getPLN(), "PLN"),
                new Money(getRON(), "RON"),
                new Money(getRUB(), "RUB"),
                new Money(getSEK(), "SEK"),
                new Money(getSGD(), "SGD"),
                new Money(getTHB(), "THB"),
                new Money(getTRY(), "TRY"),
                new Money(getUSD(), "USD"),
                new Money(getZAR(), "ZAR")
        ));
    }
}