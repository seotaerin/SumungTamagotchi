package tama;

import javax.swing.*;
import java.awt.*;

public class Tama_eat {

    //라벨 설정
    JLabel rice;
    JLabel snack;

    static int select=0; //밥먹기, 간식먹기 상태 설정 변수

    //폰트 설정
    Font customFont = LoadFontDemo.loadFontFromResource(LoadFontDemo.class, "Galmuri9.ttf", 17);
    Color textColor = Color.decode("#737171");

    //생성자에서 라벨 텍스트, 색, 위치, 폰트 설정
    Tama_eat()
    {
        rice=new JLabel("▶ 밥 먹기");
        rice.setForeground(textColor);
        rice.setBounds(120, 313, 310, 45);
        rice.setFont(customFont);

        snack=new JLabel("    간식 먹기");
        snack.setForeground(textColor);
        snack.setBounds(120, 367, 310, 45);
        snack.setFont(customFont);
    }

    //밥먹기, 간식 먹기 메뉴 선택 함수
    void menuSelect()
    {
        if(select==0)
        {
            rice.setText("    밥 먹기");
            snack.setText("▶ 간식 먹기");
            select=1;
        }
        else
        {
            rice.setText("▶ 밥 먹기");
            snack.setText("    간식 먹기");
            select=0;
        }
    }

    //밥, 간식 먹기 하면 호출 될 함수
    void eating()
    {
        if(select==0) //밥먹기 메뉴 선택된 상태면
        {
            if(Tama_state.currentAge.equals("egg")) //알상태일때는 먹을 수 없고
            {
                Tama_frame.systemLabel.setText("아직 먹을 수 없어요!");
            }
            else
            {
                if(Tama_state.stateHungry>=100) //포만감이 100이상이면 먹을 수 없고
                {
                    Tama_frame.systemLabel.setText("더 이상 먹을 수 없어요!");
                }
                else
                {
                    Tama_state.stateHungry = Math.min(Tama_state.stateHungry + 20, 100); //100이하일 때는 최대 100까지 포만감이 올라간다
                    Tama_frame.systemLabel.setText("포만감이 "+Tama_state.stateHungry + "이 되었습니다.");
                    Tama_time.hungryTime=Tama_time.currentTime.getTime();
                }
                if(Tama_state.stateHungry>=70) Tama_frame.pooState=true; //포만감이 70이상이면 poostate 활성화
            }
        }
        else if (select==1) //간식먹기 메뉴 활성화한 상태면
        {
            if(Tama_state.currentAge.equals("egg")) //알상태일때는 먹을 수 없고
            {
                Tama_frame.systemLabel.setText("아직 먹을 수 없어요!");
            }
            else if(Tama_state.currentAge.equals("baby")||Tama_state.currentAge.equals("kid"))
            {
                if(Tama_state.stateLove>=50) //나이가 어릴때는 친밀도가 50이상 오르지 않게 설정
                {
                    Tama_frame.systemLabel.setText("더 이상 먹을 수 없어요!");
                }
                else
                {
                    Tama_state.stateLove = Math.min(Tama_state.stateLove + 10, 50); //간식을 먹으면 최대 친밀도가 50까지 오르도록 설정
                    Tama_frame.systemLabel.setText("친밀도가 "+Tama_state.stateLove + "이 되었습니다.");
                }
            }
            else//어른이 된 뒤
            {
                if(Tama_state.stateLove>=100)//친밀도가 100이상이면 간식을 먹을 수 없다
                {
                    Tama_frame.systemLabel.setText("더 이상 먹을 수 없어요!");
                }
                else
                {
                    Tama_state.stateLove = Math.min(Tama_state.stateLove + 20, 100); //친밀도는 최대 100까지
                    Tama_frame.systemLabel.setText("애정이 "+Tama_state.stateLove + "이 되었습니다.");
                }
                if(Tama_state.stateLove>=50) Tama_state.stateIll=51; //간식 너무 많이 먹으면 아픔
            }
        }
    }
}

