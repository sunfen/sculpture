 package cn.sf.sculpture.common;

 /**
  * 发送消息
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/18
 */
public class RemindMessageData {
    
    /**
     * 名称 
     */
    private String keyword1;
    
    /**
     * 结束时间   2017.04.23
     */
    private String keyword2;
    
    /**
     * 提醒内容   叮！该签到啦~锲而不舍,金石可镂
     */
    private String keyword3;
    
    /**
     * 签到次数  第2次啦，加油嘞！
     */
    private String keyword4;
    
    /**
     * 备注   8点之前要签到
     */
    private String keyword5;
    
    public RemindMessageData(String now, Integer year, Long days) {
        super();
        this.keyword1 = "每日记工";
        this.keyword2 = now;
        this.keyword3 = "叮！该记工啦~锲而不舍,金石可镂";
        this.keyword4 = year + "年， 已经上了 " + days + " 天啦，加油嘞！记得按时休息哦！";
        this.keyword5 = "睡觉前记得要签到";
    }
    
    
    
    public String getKeyword1() {
        return keyword1;
    }
    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }
    public String getKeyword2() {
        return keyword2;
    }
    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }
    public String getKeyword3() {
        return keyword3;
    }
    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }
    public String getKeyword4() {
        return keyword4;
    }
    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }
    public String getKeyword5() {
        return keyword5;
    }
    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    
    
    
}
