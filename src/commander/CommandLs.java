package commander;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CommandLs extends AbstractCommand {

    public CommandLs(File currentDirectory, String commandLine) {
        super(currentDirectory, commandLine);
    }

    @Override
    public File executeCommand() throws IOException {
        File dir = currentDirectory;

        File[] filenames = dir.listFiles();
        String formatted = "";
        BasicFileAttributes attrs = null;

        for(File filename : filenames) {
            attrs = Files.readAttributes(filename.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();
            String bytes = " ";

            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            formatted = simpleDateFormat.format(new Date(time.toMillis()));

            long size = filename.length();

            if(filename.isDirectory()) {
                System.out.println(formatted + "   <DIR>        "+ filename.getName());

            }else {
                while(size / 1024 > 0) {
                    size /= 1024;

                    if(size / 1024 > 0) {
                        size /= 1024;

                        if(size / 1024 > 0) {
                            size /= 1024;
                            bytes = "G";
                        }

                        bytes = "M";
                    }

                    bytes = "K";
                }

                System.out.printf("%s          %4d%s %s\n",formatted,size,bytes,filename.getName());
            }


        }
        return currentDirectory;
    }

}