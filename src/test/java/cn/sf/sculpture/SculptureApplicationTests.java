package cn.sf.sculpture;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SculptureApplicationTests {


    public static void main(String[] args) {  
        try {  
            FileInputStream stream = new FileInputStream("C:\\Users\\DELL\\Desktop\\重庆数据20190221/specification.csv");
            
            //读取文件 转换成map‪
            BufferedReader readerMap = new BufferedReader(new InputStreamReader(stream,"gbk"));//换成你的文件名 
          
            String lineMap = null;  
            //KEY:speId  VALUE : speName
            Map<String, String> map = new HashMap<>();
            while((lineMap = readerMap.readLine()) != null){  
                lineMap = lineMap.replace("(", "");
                lineMap = lineMap.replace(")", "");
                lineMap = lineMap.replace(";", "");
                String item[] = lineMap.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
                map.put(item[0], item[1]);
            }  
            
            Map<String, List<String>> results = new HashMap<>();
          
            
            
            //deviceId  speId
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\DELL\\Desktop\\重庆数据20190221/devices.csv"));//换成你的文件名 
            String line = null;
            Integer num = 0;
            Integer numNull = 0;
            while((line = reader.readLine()) != null){
                line = line.replace("(", "");
                line = line.replace(")", "");
                line = line.replace(";", "");
                String item[] = line.split(",");
                
                String speName = "";
                
             
                
                if(item[1].trim().equals("NULL")) {
                    speName = item[1];
                    numNull += 1;
                }
                else if(map.keySet().contains(item[1])) {
                    speName = map.get(item[1]);

                    Set<String> keySet = results.keySet();
                    if(!keySet.contains(speName)) {
                        results.put(speName, new LinkedList<>());
                    }
                    List<String> values = results.get(speName);
                    values.add(item[0]);
                    num += 1;
                }else {

                    System.out.println(item[0]);
                }
                
            }
            System.out.println("num : "+num);
            System.out.println("numNull : "+numNull);
            System.out.println("total : "+ (num + numNull ));
            
            
            List<String> writer = new ArrayList<>();
            
            for(String keyIndex : results.keySet()) {
                
                String result = "UPDATE devices SET specification = \"" + keyIndex + "\"   where id in ( ";
                
                List<String> values = results.get(keyIndex);
                
                if(values.isEmpty()) {
                    continue;
                }
                for(String value : values) {
                    
                    result += value + ",";
                }
                
                result = result.substring(0, result.length() -1);
                result += "); \n";
                
                writer.add(result);
            }
            
            
            //UPDATE devices SET specification = NULL where id = 801244;

            try {  
                File csv = new File("C:\\Users\\DELL\\Desktop\\重庆数据20190221/writers1.sql"); // CSV数据文件 
           
                BufferedWriter bw = new BufferedWriter(new FileWriter(csv)); // 附加 
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
