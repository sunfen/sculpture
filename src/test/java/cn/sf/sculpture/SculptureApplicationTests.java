package cn.sf.sculpture;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SculptureApplicationTests {


    public static void main(String[] args) {  
        try {  
            //读取文件 转换成map‪
            BufferedReader readerMap = 
            new BufferedReader(new FileReader("C:/Users/DELL/Desktop/jobcontent-personnels.csv"));//换成你的文件名 
            String lineMap = null;  
            Map<String, String> map = new HashMap<>();
            while((lineMap = readerMap.readLine()) != null){  
                lineMap = lineMap.replace("(", "");
                lineMap = lineMap.replace(")", "");
                lineMap = lineMap.replace(";", "");
                String item[] = lineMap.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                map.put(item[0], item[1]);
            }  
            
            List<String> writer = new ArrayList<>();
            
            
            
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/DELL/Desktop/jobcontentoperators.csv"));//换成你的文件名 
            String line = null;  
            while((line=reader.readLine())!=null){ 
                line = line.replace("(", "");
                line = line.replace(")", "");
                line = line.replace(";", "");
                String item[] = line.split(",");
                if(map.keySet().contains(item[0])) {
                    item[0] = map.get(item[0]);
                }else {
                   System.out.println(item[0]);
                }
                writer.add(item[0] + ","+ item[1]+ "\n");
            }
            
            
            
            try {  
                File csv = new File("C:/Users/DELL/Desktop/writers.csv"); // CSV数据文件 
           
                BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // 附加 
                for(String key : writer ) {
                    
                    // 添加新的数据行 
                    bw.write( key);  
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
