
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class m1 {
	
	public static void main(String args[])throws IOException {
		
		FileNameExtensionFilter filter=new FileNameExtensionFilter("*.txt","txt");
		JFileChooser fc=new JFileChooser();
		fc.setFileFilter(filter);
		fc.setMultiSelectionEnabled(false);
		int result=fc.showSaveDialog(null);
		if (result==JFileChooser.APPROVE_OPTION) {
			File file=fc.getSelectedFile();
			if (!file.getPath().endsWith(".txt")) {
				file=new File(file.getPath()+".txt");
			}
			System.out.println("file path="+file.getPath());
			FileOutputStream fos=null;
			try {
				if (!file.exists()) {//文件不存在 则创建一个
					file.createNewFile();
				}
				fos=new FileOutputStream(file);
				fos.write("文件内容".getBytes());
				fos.flush();
			} catch (IOException e) {
				System.err.println("文件创建失败：");
				e.printStackTrace();
			}finally{
				if (fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
		}}
	}