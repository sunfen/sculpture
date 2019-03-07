package cn.sf.sculpture.message;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public class TextContent {
   
    //   是   发送的图片的媒体ID，通过 新增素材接口 上传图片文件获得。
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextContent(String content) {
        super();
        this.content = content;
    }
}
