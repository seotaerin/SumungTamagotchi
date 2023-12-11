package tama;

import javax.swing.*;

public class Tama_age
{
    //수뭉이 상태 별 이미지 불러오기
    static ImageIcon eggImg = new ImageIcon((Tama_age.class.getClassLoader().getResource("egg.gif")));
    static ImageIcon babyImg = new ImageIcon((Tama_age.class.getClassLoader().getResource("baby.gif")));
    static ImageIcon kidImg = new ImageIcon((Tama_age.class.getClassLoader().getResource("kid.gif")));
    static ImageIcon illKidImg = new ImageIcon((Tama_age.class.getClassLoader().getResource("illKid.gif")));
    static ImageIcon adultImg = new ImageIcon((Tama_age.class.getClassLoader().getResource("adult.gif")));
    static ImageIcon illAdultImg = new ImageIcon((Tama_age.class.getClassLoader().getResource("illAdult.gif")));
    static ImageIcon deadImg = new ImageIcon((Tama_age.class.getClassLoader().getResource("dead.gif")));
    static JLabel ageLabel=new JLabel(eggImg);//처음에는 알 상태로 설정

    //생성자에서는 수뭉이 이미지 초기 위치 설정
    Tama_age()
    {
        ageLabel.setBounds(175, 289, 130, 130);
    }

    //상태가 바뀌면 이미지를 변경하기 위한 함수
    public static void setAgeImg()
    {
        switch (Tama_state.currentAge) {
            case "egg" -> ageLabel.setIcon(eggImg);
            case "baby" -> ageLabel.setIcon(babyImg);
            case "kid" -> ageLabel.setIcon(kidImg);
            case "illKid" -> ageLabel.setIcon(illKidImg);
            case "adult" -> ageLabel.setIcon(adultImg);
            case "illAdult" -> ageLabel.setIcon(illAdultImg);
            case "dead" -> ageLabel.setIcon(deadImg);
        }
    }

}
