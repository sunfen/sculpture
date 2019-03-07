 package cn.sf.sculpture.message;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public class Link {
    
    //  是   消息标题
    private String title;
    
    //    是   图文链接消息
    private String description;
    
    //  是   图文链接消息被点击后跳转的链接
    private String url;
    
    //  是   图文链接消息的图片链接，支持 JPG、PNG 格式，较好的效果为大图 640 X 320，小图 80 X 80
    private String thumb_url;
    
    
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getThumb_url() {
        return thumb_url;
    }
    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    
}
