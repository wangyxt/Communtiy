package com.lixiangshequ.entity;

import java.util.Date;

public class BuildingInfo {
    private Integer buildingId;

    private String buildingName;

    private Integer buildingType;

    private Date buildingTime;

    private Integer buildingStrut;

    private String buildingAdd;

    private Integer roomNum;

    private Integer roomLived;

    private Integer floor;

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public Integer getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(Integer buildingType) {
        this.buildingType = buildingType;
    }

    public Date getBuildingTime() {
        return buildingTime;
    }

    public void setBuildingTime(Date buildingTime) {
        this.buildingTime = buildingTime;
    }

    public Integer getBuildingStrut() {
        return buildingStrut;
    }

    public void setBuildingStrut(Integer buildingStrut) {
        this.buildingStrut = buildingStrut;
    }

    public String getBuildingAdd() {
        return buildingAdd;
    }

    public void setBuildingAdd(String buildingAdd) {
        this.buildingAdd = buildingAdd == null ? null : buildingAdd.trim();
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getRoomLived() {
        return roomLived;
    }

    public void setRoomLived(Integer roomLived) {
        this.roomLived = roomLived;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
}