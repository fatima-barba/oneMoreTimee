package com.example.onemoretime.data.model;

public class MatchModel {

    private int matchNum;
    private int teamNum;
    private int deviceNum;

    public MatchModel(int matchNum, int teamNum, int deviceNum) {
        this.matchNum = matchNum;
        this.teamNum = teamNum;
        this.deviceNum = deviceNum;
    }

    @Override
    public String toString() {
        return "MatchModel{" +
                "matchNum=" + matchNum +
                ", teamNum=" + teamNum +
                ", deviceNum=" + deviceNum +
                '}';
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public int getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(int deviceNum) {
        this.deviceNum = deviceNum;
    }
}
