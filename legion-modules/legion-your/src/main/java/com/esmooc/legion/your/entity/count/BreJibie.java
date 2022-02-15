package com.esmooc.legion.your.entity.count;

/*    */
/*    */ public class BreJibie {
    /*    */   private String lji;
    /*    */   private String rji;
    private Integer user_id;

    /*    */
    /*    */
    public String getLji() {
        /*  7 */
        if (this.lji == null) this.lji = "";
        /*  8 */
        return this.lji.trim();
        /*    */
    }

    /* 11 */
    public void setLji(String lji) {
        this.lji = lji;
    }

    /*    */
    /*    */
    public String getRji() {
        /* 14 */
        if (this.rji == null) this.rji = "";
        /* 15 */
        return this.rji.trim();
        /*    */
    }

    /*    */
    /* 18 */
    public void setRji(String rji) {
        this.rji = rji;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    /*    */
}


/* Location:              C:\Users\Administrator\Desktop\Screening岱岳区啊啊啊啊.zip!\WEB-INF\classes\screening\count\pojo\BreJibie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */
