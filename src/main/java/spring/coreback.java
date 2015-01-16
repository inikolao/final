/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spring;

import java.net.UnknownHostException;
import java.util.*;
import us.jubat.classifier.ClassifierClient;
import us.jubat.classifier.EstimateResult;
import us.jubat.common.Datum;

/**
 *
 * @author root
 */
public class coreback {
     
    private final long id;
    //private final String content;
    private final String content1;
    private final String content2;
    private final String content3;
    public coreback(long id, String content1,String content2,String content3) {
        this.id = id;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
    }
    public long getId() {
        return id;
    }
 
    public List<String> getContent() {
        List<String> content= new ArrayList();
        content.add(content1);
        content.add(content2);
        content.add(content3);
        return content;
    }
    public static List<List<EstimateResult>> clasy(double value)  throws UnknownHostException
    {
     
        ClassifierClient cl;
        List<List<EstimateResult>> results;
        String[] hlp = new String[2];
        cl=openClient();
        Datum[] testData = {
        makeDatum("value", value)};
        results = cl.classify(Arrays.asList(testData));
        return results;
    }
    private static ClassifierClient openClient() throws UnknownHostException {
        String host = "150.140.5.90";
        int port = 9199;
        String name = "test";
        int k=1;
        ClassifierClient client = new ClassifierClient(host, port, name, 10);
        return client;
    }
    private static Datum makeDatum(String key,double value) {
        return new Datum().addNumber(key, value);
    }
}
