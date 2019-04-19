 package cn.sf.sculpture.common;

 /**
  * 发送消息
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/18
 */
public class RemindMessage {
    
    /*
     *    是   接口调用凭证
     */
    private String access_token;
    /*
     * 用户openid，可以是小程序的openid，也可以是mp_template_msg.appid对应的公众号的openid
     */
    private  String touser;
    
    /*
     * weapp_template_msg  Object      否   小程序模板消息相关的信息，可以参考小程序模板消息接口; 有此节点则优先发送小程序模板消息
     */
    private  TemplateMessage weapp_template_msg;

    
    
    public RemindMessage(String access_token, String touser, TemplateMessage weapp_template_msg) {
        super();
        this.access_token = access_token;
        this.touser = touser;
        this.weapp_template_msg = weapp_template_msg;
    }

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

    public TemplateMessage getWeapp_template_msg() {
        return weapp_template_msg;
    }

    public void setWeapp_template_msg(TemplateMessage weapp_template_msg) {
        this.weapp_template_msg = weapp_template_msg;
    }
   
    
    
    
    
}
