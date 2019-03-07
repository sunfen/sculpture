 package cn.sf.sculpture.message;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public class Image {
    //   是   发送的图片的媒体ID，通过 新增素材接口 上传图片文件获得。
    private String media_id;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public Image(String media_id) {
        super();
        this.media_id = media_id;
    }
    
}
