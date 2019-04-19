 package cn.sf.sculpture.common;

import java.math.BigDecimal;

/**
  * 发送消息
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/18
 */
public class RemindMessageData {
    
    /**
     * 名称 
     */
    private Keyword keyword1;
    
    /**
     * 结束时间   2017.04.23
     */
    private Keyword keyword2;
    
    /**
     * 提醒内容   叮！该签到啦~锲而不舍,金石可镂
     */
    private Keyword keyword3;
    
    /**
     * 签到次数  第2次啦，加油嘞！
     */
    private Keyword keyword4;
    
    /**
     * 备注   8点之前要签到
     */
    private Keyword keyword5;
    
    public RemindMessageData(String now, Integer year, BigDecimal days) {
        super();
        this.keyword1 = new Keyword("每日记工");
        this.keyword2 = new Keyword(now);
        this.keyword3 = new Keyword("叮！该记工啦~锲而不舍,金石可镂");
        this.keyword4 = new Keyword(year + "年， 已经上了 " + days + " 天啦，加油嘞！记得按时休息哦！");
        this.keyword5 = new Keyword("睡觉前记得要签到");
    }

    public Keyword getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(Keyword keyword1) {
        this.keyword1 = keyword1;
    }

    public Keyword getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(Keyword keyword2) {
        this.keyword2 = keyword2;
    }

    public Keyword getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(Keyword keyword3) {
        this.keyword3 = keyword3;
    }

    public Keyword getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(Keyword keyword4) {
        this.keyword4 = keyword4;
    }

    public Keyword getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(Keyword keyword5) {
        this.keyword5 = keyword5;
    }
    
}
