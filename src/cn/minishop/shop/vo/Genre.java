package cn.minishop.shop.vo;

import java.util.List;

public class Genre {
    private Integer gid;
    private String title;
    private List<Commodity> allCommodity;

    public List<Commodity> getAllCommodity() {
        return allCommodity;
    }

    public void setAllCommodity(List<Commodity> allCommodity) {
        this.allCommodity = allCommodity;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
