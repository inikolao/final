/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spring;

import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import static spring.coreback.clasy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import us.jubat.classifier.EstimateResult;


/**
 *
 * @author root
 */
@RestController
public class controler {
 
    private static final String template1 = "light:, %s";
    private static final String template2 = "tmp:, %s";
    private static final String template3 = "cur:, %s";
    private final AtomicLong counter = new AtomicLong();
    private List<List<EstimateResult>> results;
    public static double[] Sort( List<EstimateResult> res)
    {
        double[] max = new double[]{0,0,0};
        //for (List<EstimateResult> result : results) {
        

        for (EstimateResult r : res) {
            //System.out.printf("%s %f\n", r.label, r.score);
            if(r.label.contains("tmp"))
            {
                max[0]=r.score;}
            else if(r.label.contains("light"))
            {
                max[1]=r.score;
            }
            else if(r.label.contains("cur"))
            {
                max[2]=r.score;
            }
           
        }
        //}        
        return max;
    }
    @RequestMapping("/jubaform")
    public coreback jubaform(@RequestParam(value="value", defaultValue="0") double value) throws UnknownHostException {
        double [] value1=new double[3];
        String [] value2=new String [3];
        int k=0;
        double[] fn = new double[]{0,0,0};
        results=clasy(value);
        
        for (List<EstimateResult> result : results) {
            
            for (EstimateResult r : result) {
                System.out.printf("%s %f\n", r.label, r.score);
                value1[k]= r.score;
                value2[k]= r.label;
                k++;
            }
            fn=Sort(result);
        }
        
        
        return new coreback(counter.incrementAndGet(),
                            String.format(template1, fn[1]),String.format(template2, fn[0]),String.format(template3, fn[2]));
    }
 
    
}