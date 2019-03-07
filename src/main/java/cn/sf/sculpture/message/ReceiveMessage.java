 package cn.sf.sculpture.message;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public class ReceiveMessage {

    //  小程序的原始ID
    private String ToUserName;
    
    //发送者的openid
    private String FromUserName;
    
    //消息创建时间(整型）
    private Long CreateTime;
    
    //text      event
    private String MsgType;
    
    //文本消息内容
    private String Content;
    
    // 消息id，64位整型
    private Long MsgId;
 
    // 图片链接（由系统生成）
    private String PicUrl;
    
    // 图片消息媒体id，可以调用[获取临时素材]((getTempMedia)接口拉取数据。
    private String MediaId;
    
    //标题
    private String Title;
    
    //小程序appid
    private String AppId;
    
    // 小程序页面路径
    private String PagePath;
    
    // 封面图片的临时cdn链接
    private String ThumbUrl; 
    
    //封面图片的临时素材id
    private String ThumbMediaId;
    
    //事件类型，user_enter_tempsession
    private String Event;
    
    //开发者在客服会话按钮设置的 session-from 属性
    private String SessionFrom;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }


    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getPagePath() {
        return PagePath;
    }

    public void setPagePath(String pagePath) {
        PagePath = pagePath;
    }

    public String getThumbUrl() {
        return ThumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        ThumbUrl = thumbUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getSessionFrom() {
        return SessionFrom;
    }

    public void setSessionFrom(String sessionFrom) {
        SessionFrom = sessionFrom;
    }
    
}
