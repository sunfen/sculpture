package cn.sf.sculpture.common;

import java.math.BigDecimal;

/**
 * 统计
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/08
 */
public class Statistics {
    
    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private BigDecimal value;


    
    public Statistics(String name, BigDecimal value) {
        super();
        this.name = name;
        this.value = value;
    }

    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public BigDecimal getValue() {
        return value;
    }


    public void setValue(BigDecimal value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Statistics [name=" + name + ", value=" + value + "]";
    }


}
