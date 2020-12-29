package com.wxc.kafka.java.producer;

import java.util.Date;

/**
 * @author cAn
 */
public class RealtimeMessage extends AbstractMessage{

    //todo 属性封装
    //排放车辆牌照前两位（例如：苏E）
    private String licensePrefix;
    //排放车辆归属运输行业编码
    private String tradeCode;
    //排放车辆排放更新时间
    private Date updateTime;
    //排放车辆ID（无规则顺序自增加编码或者加密转码）
    private String id;
    //排放车辆车牌颜色
    private String licenseColor;
    //排放车辆状态
    private Integer status;
    //车辆排放氮氧化物含量
    private Double oxynitride;
    //车辆排放PM含量
    private Double pm;
    //车辆排放VOCS含量
    private Double vocs;
    //车辆排放CO2含量
    private Double co2;
    //车辆排放CO含量
    private Double co;
    //车辆排放甲苯含量
    private Double toluene;
    //车辆排放二甲苯含量
    private Double xylol;
    //车辆排放环戊烷含量
    private Double cyclopentane;
    //车辆排放黑炭含量
    private Double blackCarbon;
    //车辆排放有机碳含量
    private Double organicCarbon;
    //车辆排放位置定位时间
    private Date locateTime;
    //车辆排放位置经度
    private Double lon;
    //车辆排放位置纬度
    private Double lat;
    //排放标准
    private String emissionStandards;


    public String getLicensePrefix() {
        return licensePrefix;
    }

    public void setLicensePrefix(String licensePrefix) {
        this.licensePrefix = licensePrefix;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicenseColor() {
        return licenseColor;
    }

    public void setLicenseColor(String licenseColor) {
        this.licenseColor = licenseColor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getOxynitride() {
        return oxynitride;
    }

    public void setOxynitride(Double oxynitride) {
        this.oxynitride = oxynitride;
    }

    public Double getPm() {
        return pm;
    }

    public void setPm(Double pm) {
        this.pm = pm;
    }

    public Double getVocs() {
        return vocs;
    }

    public void setVocs(Double vocs) {
        this.vocs = vocs;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getToluene() {
        return toluene;
    }

    public void setToluene(Double toluene) {
        this.toluene = toluene;
    }

    public Double getXylol() {
        return xylol;
    }

    public void setXylol(Double xylol) {
        this.xylol = xylol;
    }

    public Double getCyclopentane() {
        return cyclopentane;
    }

    public void setCyclopentane(Double cyclopentane) {
        this.cyclopentane = cyclopentane;
    }

    public Double getBlackCarbon() {
        return blackCarbon;
    }

    public void setBlackCarbon(Double blackCarbon) {
        this.blackCarbon = blackCarbon;
    }

    public Double getOrganicCarbon() {
        return organicCarbon;
    }

    public void setOrganicCarbon(Double organicCarbon) {
        this.organicCarbon = organicCarbon;
    }

    public Date getLocateTime() {
        return locateTime;
    }

    public void setLocateTime(Date locateTime) {
        this.locateTime = locateTime;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getEmissionStandards() {
        return emissionStandards;
    }

    public void setEmissionStandards(String emissionStandards) {
        this.emissionStandards = emissionStandards;
    }
}
