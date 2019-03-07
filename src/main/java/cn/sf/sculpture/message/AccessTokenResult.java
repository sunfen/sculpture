 package cn.sf.sculpture.message;

 /**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/03/07
 */
public class AccessTokenResult {


      private String access_token;
      
      private Integer expires_in;
      
      private Integer errcode;
      
      private String errmsg;

    public String getAccess_token() {
        return access_token;
    }
    
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
    
    public Integer getExpires_in() {
        return expires_in;
    }
    
    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
    
    public Integer getErrcode() {
        return errcode;
    }
    
    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }
    
    public String getErrmsg() {
        return errmsg;
    }
    
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
      
  
  
  
}
