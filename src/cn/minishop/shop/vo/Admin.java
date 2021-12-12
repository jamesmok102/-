package cn.minishop.shop.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Admin implements Serializable {
    private String aid;
    private String password;
    private Date lastDate;
    private List<Commodity> allCommodity;

    public List<Commodity> getAllCommodity() {
        return allCommodity;
    }

    public void setAllCommodity(List<Commodity> allCommodity) {
        this.allCommodity = allCommodity;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
