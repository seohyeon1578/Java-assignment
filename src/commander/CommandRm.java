package commander;

import java.io.File;
import java.util.Scanner;

public class CommandRm extends AbstractCommand{

    public CommandRm(File currentDirectory, String commandLine) {
        super(currentDirectory, commandLine);
    }

    @Override
    public File executeCommand() {
        File file = null;
        Scanner sc = new Scanner(System.in);

        String[] path = commandLine.split(" ");
        file = new File(currentDirectory + "/" + path[1]);

        if (file.exists()) { //파일존재여부확인
            System.out.print("정말로 삭제하시겠습니까? (Y/N): ");
            String msg = sc.nextLine();

            if(msg.equals("Y") || msg.equals("y")) {
                if (file.isDirectory()) { //파일이 디렉토리인지 확인

                    File[] files = file.listFiles();

                    for (int i = 0; i < files.length; i++) {
                        if (files[i].delete()) {
                            System.out.println(files[i].getName() + " 삭제성공");
                        } else {
                            System.out.println(files[i].getName() + " 삭제실패");
                        }
                    }

                }
                if (file.delete()) {
                    System.out.println(path[1] + " 삭제성공");
                } else {
                    System.out.println(path[1] + " 삭제실패");
                }
            }else {
                return currentDirectory;
            }

        } else {
            System.out.println("해당 파일을 찾을 수 없습니다.");
        }

        return currentDirectory;
    }
}
