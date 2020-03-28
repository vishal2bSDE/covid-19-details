package com.tut;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class    App
{
    public static String getData(String c) throws IOException {
        //System.out.println("SDE");
        StringBuffer br=new StringBuffer();
        br.append("<html>" +
                "<body style='text-align:center;color:red;'>");
        br.append(c.toUpperCase()).append("<br>");
        String url="https://www.worldometers.info/coronavirus/country/"+c.toLowerCase()+"/";
        Document doc= Jsoup.connect(url).get();
        if(doc.title().contains("404"))return "please type correct country code.";
        System.out.println(doc.title());
//        fetching #maincounter-wrap from the html
        Elements elements=doc.select("#maincounter-wrap");
        // alt+enter if getting error using lamda expression
//        here foreach will give each #maincounter-wrap sequentially in this case case then death then recovered
        elements.forEach((e)->{
            String text=e.select("h1").text();
            String count=e.select(".maincounter-number>span").text();
            br.append(text).append(" : ").append(count).append("<br>");
        });
        br.append("</body>" +
                "</html>");
        return br.toString();
    }
    public static void main( String[] args ) throws IOException {
//        Scanner in=new Scanner(System.in);
//        String co=in.next();
//        System.out.println(getData(co));

//        creating GUI

//        frame details
        JFrame root=new JFrame("Details of COVID-19 country");
        root.setSize(500,500);

//        parameter-(font_name,font_style,fornt_size)
        Font f=new Font("Poppins",Font.BOLD,30);

//        textfield
        JTextField field=new JTextField();

//        label
        JLabel datal=new JLabel();

//        Button
        JButton button=new JButton("Get");

//        adding action listener /click on the button
        button.addActionListener((e)->{
            try{
                String maindata=field.getText();
                String result=getData(maindata);
                datal.setText(result);
//                if(datal.getText().length()==0)datal.setText("Something went wrong...");
            }catch (Exception e1)
            {
                e1.printStackTrace();
            }

        });

//        setting font
        field.setFont(f);
        datal.setFont(f);
        button.setFont(f);

//        setting layout of our visual window
        datal.setHorizontalAlignment(SwingConstants.CENTER);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        root.setLayout(new BorderLayout());
        root.add(field,BorderLayout.NORTH);
        root.add(datal,BorderLayout.CENTER);
        root.add(button,BorderLayout.SOUTH);


//        visibility of the frame
        root.setVisible(true);
    }
}
