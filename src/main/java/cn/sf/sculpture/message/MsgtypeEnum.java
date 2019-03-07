 package cn.sf.sculpture.message;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public enum MsgtypeEnum {
    
    event("event"),//打开事件
    text("text"),//文本消息
    image("image"),//图片消息
    link("link"), //图文链接
    miniprogrampage("miniprogrampage");//  图片消息
      
    
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private MsgtypeEnum(String value) {
        this.value = value;
    }
    
}
