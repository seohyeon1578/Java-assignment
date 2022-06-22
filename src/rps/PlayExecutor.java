package rps;

import java.util.Random;
import java.util.Scanner;

public class PlayExecutor {

    public static int comData = 0;

    public static int makeComData() {
        Random random = new Random();
        comData = random.nextInt(3) + 1;  //1 ~ 3
        return comData;
    }

    public void doPlay() {
        int com, score, user;
        int win = 0, lose = 0, draw = 0;
        String userStr;

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("가위바위보 가운데 하나를 입력하세요.");
            System.out.print("(사용자) ");
            userStr = sc.nextLine();

            if(userStr.equals("가위") || userStr.equals("바위") || userStr.equals("보")) {
                user = userStr.equals("가위") ? 1 : userStr.equals("바위") ? 2 : 3;

                com = makeComData();

                System.out.print(userStr + "vs" + (com == 1 ? "가위" : com == 2 ? "바위" : "보") + " : ");
                score = user * com;

                switch (score) {
                    case 2:
                    case 6:
                        System.out.println(((user > com) ? "당신이" : "컴퓨터가") + " 이겼습니다.");
                        if (user > com) {
                            win++;
                        } else {
                            lose++;
                        }
                        break;
                    case 3:
                        System.out.println(((user > com) ? "컴퓨터가" : "당신이") + " 이겼습니다.");
                        if (user > com) {
                            lose++;
                        } else {
                            win++;
                        }
                        break;
                    default:
                        System.out.println("비겼습니다.");
                        draw++;
                }

                System.out.printf("전적: %d승 %d무 %d패\n\n", win, draw, lose);
            }else if(userStr.equals("quit")){
                System.out.printf("총 전적: %d승 %d무 %d패\n", win, draw, lose);
                System.out.println("프로그램을 종료합니다.");
                sc.close();
                break;
            }else {
                System.out.println("\'"+ userStr + "\' 이런거 없는뎀..");
                System.out.printf("가위바위보는 가위, 바위, 보 중에 해야되는뎀..\n\n");
            }
        }
    }

    public static void main(String[] args) {
        PlayExecutor playExecutor = new PlayExecutor();

        playExecutor.doPlay();
    }
}
