
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * XStream������, ����xml�����֮���ת��
 * <br>Object fromXML(String xml) ��XMLת���ɶ���
 * <br>String toXML(Object obj) ������ת����XML�ֶδ�
 * 
 * @author XerCis
 * @version  1.0
 * 
 */
public class StreamUtil {

	private static XStream xstream = new XStream(new DomDriver());//X��
	private static PrintStream ps;
	private static BufferedReader in;
	
	/**
	 * �ַ���תΪbase64
	 * @param s ԭʼ�ַ���
	 * @return String base64
	 */
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		try {
			return (new BASE64Encoder()).encode(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * base64��ԭ���ַ���
	 * @param s base64��
	 * @return String ԭʼ��
	 */
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b, "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * �ֽ�����תbase64
	 * @param bs �ֽ�����
	 * @return String base64��
	 */
	public static String byteToBase64(byte[] bs) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bs);
	}

	/**
	 * base64ת�ֽ�����
	 * @param base64 base64��
	 * @return byte ԭʼ�ֽ�����
	 */
	public static byte[] base64ToByte(String base64) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(base64);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ��XMLת���ɶ���
	 * @param String xml
	 * @return Object
	 */
	public static Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}
	
	/**
	 * ������ת����XML�ֶδ�
	 * @param Object obj
	 * @return
	 */
	public static String toXML(Object obj) {
		String xml = xstream.toXML(obj);
		String a = xml.replaceAll("\n", "");//ȥ������
		String s = a.replaceAll("\r", "");
		return s;
	}
	
	/**
	 * ָ���ļ���д����
	 * @param String file
	 * @param String data
	 * @throws IOException 
	 */
	public static void printToFile(String file,String data) throws IOException {
		//�������򴴽�
		File temp = new File(file);
		File tempParent = temp.getParentFile();
		if(tempParent!=null) {
			if(!tempParent.exists()) {
			    tempParent.mkdirs();
				temp.createNewFile();
			}
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		ps = new PrintStream(fos);
		ps.print(data);
		ps.close();
	}
	
	/**
	 * ָ���ļ���������
	 * @param String file
	 * @throws IOException 
	 */
	public static String readFromFile(String file) throws IOException {
		in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String s = "";
		String temp = null;
		while((temp = in.readLine())!=null) {
			s += temp;
		}
		in.close();
		return s;
	}
}
