package tama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tama_time
{
    Font customFont = LoadFontDemo.loadFontFromResource(LoadFontDemo.class, "Galmuri9.ttf", 17);
    static Date currentTime=new Date(); //date형식의 현재시간 변수 만듬
    static long startTime=new Date().getTime(); //시작시간 저장
    static long hungryTime=new Date().getTime(); //포만감 떨어지게 하기 위해 마지막으로 밥먹은 시간 저장
    static long loveTime=new Date().getTime(); //마지막으로 간식 먹은 시간 저장
    SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일 HH시 mm분"); //시간 형식 어떻게 표시할지
    static JLabel gameTime = new JLabel(""); //시간 표시할 라벨
    JButton afterHour = new JButton("1시간 후"); //1시간 더할때 쓸 버튼
    JButton afterDay = new JButton("24시간 후"); //24시간 더할 때 쓸 버튼

    //생성자에서 타이머 시작하도록
    Tama_time()
    {
        Timer timer = new Timer(1000, new ActionListener() { //1초에 한번씩 현재시간에 1초를 더한다
            public void actionPerformed(ActionEvent e) {
                currentTime.setTime(currentTime.getTime() + 1000);
                gameTimeUpdate();
            }
        });

        timer.start();

        //한시간 후 버튼
        afterHour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime.setTime(currentTime.getTime() + 3600000); //현재시간에 한시간을 더함
                gameTimeUpdate(); //라벨의 시간 업데이트
            }
        });

        //24시간 후 버튼
        afterDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTime.setTime(currentTime.getTime() + 3600000*24); //현재 시간에 24시간을 더함
                gameTimeUpdate(); //라벨의 시간 업데이트
            }
        });

        //라벨과 버튼 위치랑 폰트 설정
        gameTime.setFont(customFont);
        afterHour.setFont(customFont);
        afterDay.setFont(customFont);
        gameTime.setBounds(50, 630, 500, 130);
        afterHour.setBounds(330, 650, 130, 50);
        afterDay.setBounds(330, 700, 130, 50);
    }

    //라벨에 시간 업데이트하는 함수
    void gameTimeUpdate()
    {
        String time = sdf.format(currentTime);
        gameTime.setText("현재시간 : " + time);
    }

}
