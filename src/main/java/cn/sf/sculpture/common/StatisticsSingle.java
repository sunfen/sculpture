package cn.sf.sculpture.common;

import java.math.BigInteger;

/**
 * 统计
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/08
 */
public class StatisticsSingle {
    
    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private BigInteger data;

    
    public StatisticsSingle(String name, BigInteger data) {
        super();
        this.name = name;
        this.data = data;
    }

    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getData() {
        return data;
    }


    public void setData(BigInteger data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Statistics [name=" + name + ", data=" + data + "]";
    }


}
