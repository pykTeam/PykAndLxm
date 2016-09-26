package com.mobileapplicationsclass.pykandlxm.model;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class WandNModel {
    /**
     * date : 2016年09月11日-2016年09月17日
     * name : 射手座
     * health : 健康：水逆期，外出注意安全。精神易恍惚。 作者：马子晴
     * job : 求职：朋友推荐可信赖。
     * love : 恋情：水逆、海逆能量叠加，感情易混沌。贪恋陪伴，友情爱情界限不明。
     * money : 财运：水逆期意外破财多。人际开销大，但财运也因此而来，可当是人脉投资。
     * weekth : 38
     * work : 工作：水逆继续，疏漏、延误多。工作压力偏大。团队、人脉帮你分担。
     * resultcode : 200
     * error_code : 0
     */

    private String date;
    private String name;
    private String health;
    private String job;
    private String love;
    private String money;
    private int weekth;
    private String work;
    private String resultcode;
    private int error_code;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getWeekth() {
        return weekth;
    }

    public void setWeekth(int weekth) {
        this.weekth = weekth;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
