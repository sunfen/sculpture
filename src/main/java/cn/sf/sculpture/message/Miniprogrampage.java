 package cn.sf.sculpture.message;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public class Miniprogrampage {
    
    //  是   消息标题
    private String title;
    
    //  是   小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar
    private String pagepath;
    
    //  是   小程序消息卡片的封面， image 类型的 media_id，通过 新增素材接口 上传图片文件获得，建议大小为 520*416
    private String thumb_media_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }
    
}
