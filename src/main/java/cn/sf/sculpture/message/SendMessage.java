package cn.sf.sculpture.message;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public class SendMessage {

    // 接口调用凭证
    private String access_token;
    
    //用户的 OpenID
    private String touser;
    
    //消息类型
    private String msgtype;
    
    //文本消息内容，msgtype="text" 时必填
    private TextContent text;
    
    //图片消息，msgtype="image" 时必填
    private Image image;
    
    // 图片消息，msgtype="link" 时必填
    private Link link;
 
    //小程序卡片，msgtype="miniprogrampage" 时必填
    private Miniprogrampage miniprogrampage;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }


    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }



    public TextContent getText() {
        return text;
    }

    public void setText(TextContent text) {
        this.text = text;
    }

    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Miniprogrampage getMiniprogrampage() {
        return miniprogrampage;
    }

    public void setMiniprogrampage(Miniprogrampage miniprogrampage) {
        this.miniprogrampage = miniprogrampage;
    }

    
  
}
