import java.io.*;
public class FileClassDemo
{
    public static void main(String[] args)
    {
        File f = new File("c:\\hello");
        File allfiles[] = f.listFiles();
        for (int i = 0; i < allfiles.length; i++)
        {
            System.out.println("Name = "+allfiles[i].getName());
            System.out.println("Parent = "+allfiles[i].getParent());
            System.out.println("File Size = "+allfiles[i].length());
            System.out.println("-----------------------------------");
            
        }
        
        
//        File f = new File("c:\\hello");
//        String names[] = f.list();
//        for (int i = 0; i < names.length; i++)
//        {
//            System.out.println(names[i]);
//        }
        
        
//        File f = new File("c:\\hello\\test\\one\\two\\three");
//        if(f.exists()==false)
//        {
//            System.out.println("Folder Created = "+f.mkdirs());
//        }
        
        
//        File f  = new File("c:\\hello\\two.txt");
//        System.out.println("File Deleted " +f.delete()); //boolean
//        System.out.println("File Size = "+f.length()+" bytes");//long 
//        System.out.println("File Name = "+f.getName());//String 
//        System.out.println("File Path  = "+f.getPath()); //String
//        System.out.println("File Parent = "+f.getParent()); //String
//        System.out.println("Is File = "+f.isFile()); //boolean
//        System.out.println("Is Directory = "+f.isDirectory()); //boolean
//        System.out.println("Exists = "+f.exists()); //boolean
//        System.out.println("Can Read = "+f.canRead()); //boolean
//        System.out.println("Can Write = "+f.canWrite()); //boolean
    }
}
