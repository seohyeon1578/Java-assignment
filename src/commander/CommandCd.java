package commander;

import java.io.File;

public class CommandCd extends AbstractCommand {

    public CommandCd(File currentDirectory, String commandLine) {
        super(currentDirectory, commandLine);
    }

    @Override
    public File executeCommand() {
        String[] path = commandLine.split(" ");
        File file = null;
        if(path[1].equals("..")) {
            //상위 폴더 여부
            if(currentDirectory.getParentFile() == null){
                file = currentDirectory;
            }else {
                file = currentDirectory.getParentFile();
            }

        } else{
            String filename = "";
            for(int i = 1; i < path.length; i++) {
                if(i > 1){
                    filename += " ";
                }
                filename += String.join("",path[i]);
            }
            file = new File(currentDirectory + "/" + filename);

            //파일인지 디렉터리인지 구분 *파일은 ls가 안된다.*
            if(file.isDirectory()) {
                //경로 여부
                if (file.exists() == false) {
                    System.out.println("지정된 경로를 찾을 수 없습니다.");
                    file = currentDirectory;
                }
            }else {
                System.out.println("디렉터리 이름이 올바르지 않습니다.");
                file = currentDirectory;
            }
        }
        return file;
    }
}