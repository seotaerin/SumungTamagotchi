package tama;

import javax.swing.*;
import java.awt.*;

public class Tama_state
{
    //폰트추가
    Font customFont = LoadFontDemo.loadFontFromResource(LoadFontDemo.class, "Galmuri9.ttf", 17);
    //라벨추가
    JLabel stateLoveLabel;
    JLabel stateHungryLabel;
    static JLabel stateAgeLabel;

    //수뭉이 스텟
    static int stateHungry = 0;
    static int stateLove=0;
    static int stateIll=0;

    //수뭉이 현재 나이
    static String currentAge="egg";
    static int age=0;

    Tama_state() //생성자에서 수뭉이 스텟 표시할 라벨들 설정
    {
        //수뭉이 나이 라벨 만들고 위치, 색, 폰트 등 설정
        stateAgeLabel = new JLabel("나이 : "+age+"살");
        Color textColor = Color.decode("#737171");
        stateAgeLabel.setForeground(textColor);
        stateAgeLabel.setBounds(200, 300, 300, 45);
        stateAgeLabel.setFont(customFont);

        //친밀도 라벨 설정
        stateLoveLabel = new JLabel("친밀도 : "+stateLove);
        stateLoveLabel.setForeground(textColor);
        stateLoveLabel.setBounds(122, 340, 300, 45);
        stateLoveLabel.setFont(customFont);

        //포만감 라벨 설정
        stateHungryLabel = new JLabel("포만감 : "+stateHungry);
        stateHungryLabel.setForeground(textColor);
        stateHungryLabel.setBounds(122, 380, 300, 45);
        stateHungryLabel.setFont(customFont);
    }

    //포만감, 애정 업데이트
    void stateLabel()
    {
        setAge();//수뭉이 나이 업데이트
        stateHungryLabel.setText("포만감 : "+icon(stateHungry));
        stateLoveLabel.setText("친밀도 : "+icon(stateLove));
    }

    //수뭉이 나이 설정하는 함수
    public static void setAge()
    {
        switch (currentAge) {
            case "egg" -> age = 0;
            case "baby" -> age = 1;
            case "kid" -> age = 2;
            case "adult" -> age = 3;
        }
        stateAgeLabel.setText("나이 : "+age+"살");
    }

    //스텟 설정할때 숫자가 아니라 네모 모양으로 설정하려고 만든 함수
    String icon(int n)
    {
        if(n>=100)
        {
            return "▮▮▮▮▮▮▮▮▮▮";
        }
        else if(n>=90)
        {
            return "▮▮▮▮▮▮▮▮▮▯";
        }
        else if(n>=80)
        {
            return "▮▮▮▮▮▮▮▮▯▯";
        }
        else if(n>=70)
        {
            return "▮▮▮▮▮▮▮▯▯▯";
        }
        else if(n>=60)
        {
            return "▮▮▮▮▮▮▯▯▯▯";
        }
        else if(n>=50)
        {
            return "▮▮▮▮▮▯▯▯▯▯";
        }
        else if(n>=40)
        {
            return "▮▮▮▮▯▯▯▯▯▯";
        }
        else if(n>=30)
        {
            return "▮▮▮▯▯▯▯▯▯▯";
        }
        else if(n>=20)
        {
            return "▮▮▯▯▯▯▯▯▯▯";
        }
        else if(n>=10)
        {
            return "▮▯▯▯▯▯▯▯▯▯";
        }
        else
        {
            return "▯▯▯▯▯▯▯▯▯▯";
        }

    }

 }
