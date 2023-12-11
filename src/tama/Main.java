package tama;

public class Main
{
    public static void main(String[] args)
    {
        Tama_frame frame = new Tama_frame(); //프레임 클래스 객체를 만들고 생성자를 호출해 프레임, 패널, 버튼, 라벨 등 초기설정

        long hungryTimeDiffer; //밥 먹은지 얼마나 지났는지, 포만감 감소를 계산하기 위한 변수
        long loveTimeDiffer; //간식 먹은지 얼마나 지났는지, 친밀도 감소를 계산하기 위한 변수
        long ageTimeDiffer; //나이 먹은지 얼마나 지났는지 계산하기 위한 변수

        boolean ending=false;//엔딩 후 게임 종료하기 위한 변수

        while (true){
            hungryTimeDiffer=(Tama_time.currentTime.getTime()- Tama_time.hungryTime); //현재시간에서 마지막으로 밥먹은 시간 또는 마지막으로 포만감이 감소한 시간을 뺀다
            loveTimeDiffer=(Tama_time.currentTime.getTime()- Tama_time.loveTime); //현재 시간에서 마지막으로 간식 먹은 시간을 뺀다
            ageTimeDiffer=(Tama_time.currentTime.getTime()- Tama_time.startTime); //현재시간에서 프로그램이 시작한 시간을 뺀다

            //배고픔수치깎임 & 오래굶으면 질명수치 올라감
            if(Tama_state.stateHungry>0&&hungryTimeDiffer>=600000){ //포만감이 0초과&&마지막으로 박먹은지 한시간이상지남
                int n=(int)hungryTimeDiffer/600000; //포만감이 한시간에 10씩 감소하도록 계산
                Tama_state.stateHungry=Math.max(0, Tama_state.stateHungry-n); //포만감을 감소시키지만 0보다 감소되지 않도록 함
                Tama_time.hungryTime=Tama_time.currentTime.getTime(); //포만감을 감소시켰으니까 마지막으로 밥먹은 시간을 초기화함
            }
            else if(hungryTimeDiffer>=600000)//포만감이=0&&마지막으로 밥먹은지 한시간이상 지남
            {
                Tama_state.stateIll= (int) (hungryTimeDiffer/900000); //질병수치를 계산함
            }

            //친밀도 깎임
            if(loveTimeDiffer>=600000){ //마지막으로 간식먹은지 한시간이상 되면
                int n=(int)loveTimeDiffer/600000; //친밀도 깎이도록
                Tama_state.stateLove=Math.max(0, Tama_state.stateLove-n); //친밀도가 깎이지만 0미만이 되지 않도록
                Tama_time.loveTime=Tama_time.currentTime.getTime();//친밀도 감소시켰으니 마지막으로 간식 먹은 시간 초기화
            }

            //나이먹음&아픔
            if(ageTimeDiffer>=91860000) //ageTimeDiffer이 91860000이상이면 나이가 adult이다
            {
                if(Tama_state.stateIll>=50) //질병수치 50이상이면
                {
                    Tama_state.currentAge="illAdult"; //아픔상태로 바꾸고
                    frame.needHillLabel.setVisible(true); //아픔 상태를 표시하는 라벨을 보이게함
                }
                else{
                    Tama_state.currentAge="adult"; //질병수치가 50미만이면 상태를 adult로 바꾸고
                    frame.needHillLabel.setVisible(false); //아픔상태를 표시하는 라벨을 안 보이게 함
                }

            }
            else if(ageTimeDiffer>=5460000) //ageTimeDiffer이 5460000이상이면 Kid이다
            {
                if(Tama_state.stateIll>=50) //질병수치 50이상이면
                {
                    Tama_state.currentAge="illKid"; //아픔상태로 바꾸고
                    frame.needHillLabel.setVisible(true); //아픔 상태를 표시하는 라벨을 보이게함
                }
                else{
                    Tama_state.currentAge="kid"; //질병수치가 50미만일때는 kid상태로
                    frame.needHillLabel.setVisible(false); //아픔상태를 표시하는 라벨을 안 보이게 함
                }
            }
            else if(ageTimeDiffer>=60000) //ageTimeDiffer이 60000이상이면 baby
            {
                Tama_state.currentAge="baby"; //baby상태일때는 질병수치가 50이상될일이 없어서 질병수치 50이상일때를 만들이 않았음
            }

            Tama_age.setAgeImg(); //상태에 맞는 이미지로 변경한다
            Tama_state.setAge(); //나이를 설정한다
            frame.repaint();

            if(ageTimeDiffer>=200000000) ending=true; //성인이 된 후 엔딩이 나옴

            //게임오버 조건
            if(Tama_state.stateIll>=100||ending) //질병수치가100이거나 수뭉이가 성인이 되면 엔딩이 나옴
            {
                frame.gameOver(); //게임오버 함수에서 조건에 따라 엔딩이 나오도록 함
                frame.repaint();
                break;
            }
        }
    }
}
