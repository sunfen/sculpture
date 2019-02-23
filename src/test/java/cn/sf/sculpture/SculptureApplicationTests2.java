package cn.sf.sculpture;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SculptureApplicationTests2 {


    
    public static void main(String[] args) {  
        try {  
            //读取文件 转换成map‪
           String path = "C:/Users/DELL/Desktop/jobtickets .csv";
           InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
           
           BufferedReader readerMap = new BufferedReader(isr);
           
           String lineMap = null;  
            
           Map<String, String> map = new HashMap<>();
           
            while((lineMap = readerMap.readLine()) != null){
                String item[] = lineMap.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                map.put(item[0], item[1]);
            }  
            
            List<String> writer = new ArrayList<>();
            
            String path2 = "C:/Users/DELL/Desktop/jobticket-contents.csv";
            InputStreamReader isr2 = new InputStreamReader(new FileInputStream(path2), "gbk");
            
            BufferedReader reader = new BufferedReader(isr2);
            
            String line = null;  
            while((line=reader.readLine())!=null){ 
                String item[] = line.split(",");
                if(map.keySet().contains(item[0])) {
                    writer.add(line + "," + map.get(item[0]) + "\n");
                }else {
                    writer.add(line + "\n");
                   System.out.println(item[0]);
                }
            }
            
            
            
            try {  
                String path1 = "C:/Users/DELL/Desktop/writers_content.csv"; // CSV数据文件 
                OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(path1),"UTF-8");
                BufferedWriter bw = new BufferedWriter(writerStream);
                for(String key : writer ) {
                    
                    // 添加新的数据行 
                    bw.write(key);  
                }
                bw.newLine();  
                bw.close();  
              } catch (FileNotFoundException e) {  
                // File对象的创建过程中的异常捕获 
                e.printStackTrace();  
              } catch (IOException e) {  
                // BufferedWriter在关闭对象捕捉异常 
                e.printStackTrace();  
              }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 

}
