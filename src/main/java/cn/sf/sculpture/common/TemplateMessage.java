 package cn.sf.sculpture.common;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/18
 */
public class TemplateMessage {
    
    /*
     * 是   小程序模板ID
     */
    private String  template_id;      
    
    /*
     *  是   小程序页面路径
     */
    private String page;     
    
    /*
     * 小程序模板消息formid
     */
    private String form_id;         
    /*
     *  是   小程序模板数据
     */
    private RemindMessageData data;
    /*
     *  是   小程序模板放大关键词
     */
    private String emphasis_keyword;
    
    
    
    
    
    public TemplateMessage( String form_id, RemindMessageData data) {
        super();
        this.form_id = form_id;
        this.data = data;
        this.template_id = "Z9ABOEJsCIygZaZ5co5QxGuhjCUNPET4wXafgK5IHqQ";
        this.page = "pages/index/index";
        this.emphasis_keyword = "签到提醒";
    }
    
    
    
    
    public String getTemplate_id() {
        return template_id;
    }
    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public String getForm_id() {
        return form_id;
    }
    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }
    public RemindMessageData getData() {
        return data;
    }
    public void setData(RemindMessageData data) {
        this.data = data;
    }
    public String getEmphasis_keyword() {
        return emphasis_keyword;
    }
    public void setEmphasis_keyword(String emphasis_keyword) {
        this.emphasis_keyword = emphasis_keyword;
    }  
    
    
}
