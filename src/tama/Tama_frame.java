package tama;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Tama_frame extends JFrame implements ActionListener {

    //필요한 이미지들 불러오기
    ImageIcon bg = new ImageIcon((this.getClass().getClassLoader().getResource("tamagotchi-ui.png"))); //배경이미지 불러옴
    ImageIcon select = new ImageIcon((this.getClass().getClassLoader().getResource("select.png"))); //메뉴 선택 이미지 불러옴
    ImageIcon poo = new ImageIcon((this.getClass().getClassLoader().getResource("poo.png"))); //수뭉이 똥 이미지 불러옴
    ImageIcon needHill = new ImageIcon((this.getClass().getClassLoader().getResource("needHill.png"))); //수뭉이 아픔 표시 이미지 불러옴

    //필요한 라벨, 버튼들 정의
    static JLabel systemLabel = new JLabel("수뭉이 알을 받았습니다!");//다른 클래스들에서도 써야해서 스태틱으로 설정
    JLabel needHillLabel=new JLabel(needHill); //수뭉이가 아플때 뜨는 라벨
    JLabel selectLabel = new JLabel(select); //메뉴 선택 표시하는 라벨
    JLabel bgLabel = new JLabel(bg); //배경이미지 라벨
    JLabel pooLabel=new JLabel(poo); //수뭉이 똥...
    static boolean pooState=false; //수뭉이 똥이 활성화 됐는지?
    JButton A; //메뉴이동 버튼
    JButton B; //선택 버튼
    JButton C; //취소버튼

    //클래스의 다른 변수, 함수들 불러오기 위해 객체들 생성
    Tama_state state = new Tama_state();
    Tama_eat eat=new Tama_eat();
    Tama_age age = new Tama_age();
    Tama_time time = new Tama_time();

    //프레임이랑 패널 정의
    static JFrame frame = new JFrame("다마고치"); //프레임 이름
    static JLayeredPane home = new JLayeredPane(); //라벨들이 추가될 패널

    //폰트 설정
    Font customFont = LoadFontDemo.loadFontFromResource(LoadFontDemo.class, "Galmuri9.ttf", 17); //폰트 설정
    Color textColor = Color.decode("#737171"); //폰트 색

    //프레임의 생성자: 프레임, 패널, 버튼, 라벨들을 생성하고 추가한다.
    public Tama_frame() {

        //프레임 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 닫기설정
        frame.setSize(500, 820); //프레임 크기 설정

        //라벨들 위치, 폰트, 색 설정, 패널에 추가
        bgLabel.setBounds(-10, -20, bg.getIconWidth(), bg.getIconHeight());
        home.add(bgLabel, JLayeredPane.DEFAULT_LAYER);
        selectLabel.setBounds(currentSelectX[0], 401, select.getIconWidth(), select.getIconHeight());
        frameAddPop(selectLabel);
        pooLabel.setBounds(315,360,40,40);
        needHillLabel.setBounds(330,250,50,50);
        frameAdd(needHillLabel);
        needHillLabel.setVisible(false);
        systemLabel.setBounds(120, 255, 300, 45);
        systemLabel.setFont(customFont);
        systemLabel.setForeground(textColor);
        frameAddPop(systemLabel);
        frameAddPop(Tama_age.ageLabel);

        //버튼생성하고 위치설정
        A = new JButton("A");
        A.setBounds(126, 510, 55, 55);
        B = new JButton("B");
        B.setBounds(212, 550, 55, 55);
        C = new JButton("C");
        C.setBounds(299, 510, 55, 55);

        //버튼 폰트, 색 설정
        A.setFont(customFont);
        B.setFont(customFont);
        C.setFont(customFont);
        A.setForeground(textColor);
        B.setForeground(textColor);
        C.setForeground(textColor);

        //버튼 투명하게 만들기
        A.setContentAreaFilled(false);
        B.setContentAreaFilled(false);
        C.setContentAreaFilled(false);

        //버튼을 패널에 추가
        frameAdd(A);
        frameAdd(B);
        frameAdd(C);
        frameAdd(time.afterHour);
        frameAdd(time.afterDay);
        frameAdd(Tama_time.gameTime);

        //버튼이 작동하도록 액션리스너
        A.addActionListener(this);
        B.addActionListener(this);
        C.addActionListener(this);

        //프레임에 패널 추가하고 설정 마무리
        frame.add(home);
        frame.setVisible(true);
    }

    //패널에 라벨 추가, 삭제할때 쓸 함수들
    void frameAdd(JLabel J)
    {
        home.add(J, JLayeredPane.PALETTE_LAYER);
    }
    void frameAddPop(JLabel J)
    {
        home.add(J, JLayeredPane.POPUP_LAYER);
    }
    void frameAdd(JButton J)
    {
        home.add(J, JLayeredPane.PALETTE_LAYER);
    }
    void frameRemove(JLabel J){
        home.remove(J);
        home.repaint();
    }

    //메인에서 게임오버 or 엔딩때 쓸 함수
    public void gameOver()
    {
        time.afterHour.setEnabled(false);
        time.afterDay.setEnabled(false);
        A.setEnabled(false);
        B.setEnabled(false);
        C.setEnabled(false);
        frameRemove(selectLabel);

        if(Tama_state.stateIll>=100)
        {
            systemLabel.setBounds(150, 335, 300, 45);
            systemLabel.setText("수뭉이가 죽었습니다...");
            Tama_state.currentAge="dead";
            age.setAgeImg();
        }
        else
        {
            systemLabel.setBounds(125, 305, 300, 45);
            if((Tama_state.currentAge.equals("illAdult") || Tama_state.stateLove<=50)) //병에 걸리지 않고 친밀도가 50 이상일 경우의 엔딩
            {
                systemLabel.setText("그동안 너무 외로웠어요.");
                JLabel label2=new JLabel("다음에는 더 사랑해주세요. ");
                label2.setBounds(125, 340, 300, 45);
                label2.setFont(customFont);
                label2.setForeground(textColor);
                frameAddPop(label2);
                JLabel label3=new JLabel("안녕!");
                label3.setBounds(125, 375, 300, 45);
                label3.setFont(customFont);
                label3.setForeground(textColor);
                frameAddPop(label3);
            }
            else if((Tama_state.currentAge.equals("ault") || Tama_state.stateLove>=50)) //병에 걸리거나 친밀도가 50 미만일 경우의 엔딩
            {
                systemLabel.setText("지금까지 키워주셔서 감사해요.");
                JLabel label2=new JLabel("덕분에 행복했어요.");
                label2.setBounds(125, 340, 300, 45);
                label2.setFont(customFont);
                label2.setForeground(textColor);
                frameAddPop(label2);
                JLabel label3=new JLabel("안녕!");
                label3.setBounds(125, 375, 300, 45);
                label3.setFont(customFont);
                label3.setForeground(textColor);
                frameAddPop(label3);
            }
        }
    }

    //메뉴 선택할때 필요한 배열과 변수들
    String[] menuList = {"state", "eat", "bath", "heal"};
    int[] currentSelectX={102, 166, 231, 297};
    String currentMenu = "state";
    int currentMenuIndex = 0;
    boolean eatMenuActive=false;

    //버튼 입력되면 실행
    public void actionPerformed(ActionEvent e) {

        //A버튼 눌렀을때, A버튼은 메뉴 이동을 위한 버튼
        if (e.getSource() == A) {
            if(currentMenuIndex == 1 && eatMenuActive) eat.menuSelect(); //밥먹기 메뉴에서 메뉴를 이동하기 위한 함수 호출
            else if (currentMenuIndex == 3) {
                currentMenuIndex = 0;
                currentMenu = menuList[currentMenuIndex];
                selectLabel.setBounds(currentSelectX[currentMenuIndex], 401, select.getIconWidth(), select.getIconHeight());
            } else {
                currentMenuIndex++;
                currentMenu = menuList[currentMenuIndex];
                selectLabel.setBounds(currentSelectX[currentMenuIndex], 401, select.getIconWidth(), select.getIconHeight());
            }
        }

        //B버튼 눌렀을때
        else if (e.getSource() == B)
        {
            if(currentMenu.equals("state"))
            {
                systemLabel.setText("수뭉이 현재 상태");
                state.stateLabel(); //스탯창 띄우기 전에 수뭉이 스텟들 업데이트함
                frameAdd(Tama_state.stateAgeLabel); //스텟창의 라벨들 추가
                frameAdd(state.stateHungryLabel);
                frameAdd(state.stateLoveLabel);
                A.setEnabled(false); //안 쓸 버튼 잠시 비활성화
                B.setEnabled(false);
                frameRemove(Tama_age.ageLabel); //수뭉이 라벨 잠시 치워둠
                frameRemove(pooLabel);
            }
            else if(currentMenu.equals("eat")&&eatMenuActive) //eat메뉴 활성화된상태서 버튼 클릭하면 eating함수 호출하기
            {
                eat.eating(); //밥, 간식 먹고 포만감, 애정 변경할 함수 호출
            }
            else if(currentMenu.equals("eat")) //eat 메뉴를 선택하면 다른 라벨들을 지우고 eat메뉴 라벨 추가
            {
                frameRemove(Tama_age.ageLabel);
                frameRemove(pooLabel);
                frameAdd(eat.rice);
                frameAdd(eat.snack);
                eatMenuActive=true;
            }
            else if(currentMenu.equals("bath"))//poo라벨 활성화된 상태면 치우기 가능
            {
                frameRemove(Tama_age.ageLabel);//다른 라벨들은 치우고
                frameRemove(pooLabel);
                systemLabel.setText("청소가 완료되었습니다.");//청소가 완료되었다는 메세지 출력
                systemLabel.setBounds(150, 335, 300, 45);
                pooState=false; //청소했으니까 poostate는 false로
                A.setEnabled(false); //A, B버튼이 눌리면 안 되니까 비활성화
                B.setEnabled(false);
            }
            else if(currentMenu.equals("heal"))//아픔 상태에서 실행하면 stateIll을 0으로 초기화
            {
                frameRemove(Tama_age.ageLabel); //다른 라벨들은 치우고
                frameRemove(pooLabel);
                systemLabel.setText("치료가 완료되었습니다."); //치료가되었다는 텍스트 출력
                systemLabel.setBounds(150, 335, 300, 45);
                Tama_time.hungryTime=Tama_time.currentTime.getTime();
                Tama_state.stateIll=0;
                A.setEnabled(false); //A, B버튼이 눌리면 안 되니까 비활성화
                B.setEnabled(false);
            }
        }
        //C버튼 눌렸을때
        else if (e.getSource() == C)
        {
            systemLabel.setText("");
            switch (currentMenu) {
                case "state" -> {
//state메뉴에서 나가면서

                    frameRemove(state.stateHungryLabel);//state메뉴의 라벨들 치우고
                    frameRemove(state.stateLoveLabel);
                    frameRemove(Tama_state.stateAgeLabel);
                    home.repaint(); //패널 다시 리페인트
                    A.setEnabled(true); //버튼 다시 활성화
                    B.setEnabled(true);
                    frameAddPop(Tama_age.ageLabel); //수뭉이 라벨 다시 추가
                    if (pooState) frameAdd(pooLabel); //혹시 poo true면 보이게
                }
                case "eat" -> {
//eat메뉴 나가면서

                    home.remove(eat.rice); //eat메뉴 라벨들 치우고
                    home.remove(eat.snack);
                    home.repaint(); //리페인트
                    eatMenuActive = false; //메뉴나가면서 eat메뉴 비활성화
                    frameAddPop(Tama_age.ageLabel); // 수뭉이 다시 추가
                    if (pooState) frameAdd(pooLabel);
                }
                case "bath" -> {
//청소 완료후 메뉴 나가면서

                    systemLabel.setText(""); //시스템 라벨 초기화하고
                    systemLabel.setBounds(120, 255, 300, 45);//원래 위치로 이동
                    frameAddPop(Tama_age.ageLabel); //수뭉이 다시 추가
                    A.setEnabled(true); //버튼도 다시 활성화
                    B.setEnabled(true);
                }
                case "heal" -> {
//치료 완료후 메뉴 나가면서

                    systemLabel.setText("");
                    systemLabel.setBounds(120, 255, 300, 45); //시스템 라벨 원래 위치로
                    frameAddPop(Tama_age.ageLabel); //수뭉이 다시 추가
                    if (pooState) frameAdd(pooLabel);
                    A.setEnabled(true); //버튼 다시 활성화
                    B.setEnabled(true);
                }
            }
        }

    }
}

