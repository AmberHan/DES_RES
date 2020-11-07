import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5�㷨����һ���ַ�������ת��Ϊ32λ����+��д��ĸ���ַ�����
 * 
 * @author elon
 * @version 2018��4��5��
 */
public class hash {

    /**
     * ����16���ư������ַ������ڼ��ܴ�ת��
     */
    private static final char[] hexDigints = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * ���ܺ󷵻�ʮ�����Ƶ����ġ�
     * 
     * @param input �����ַ���
     * @return ����
     * @throws NoSuchAlgorithmException 
     */
    public static String encrypt(String input) throws NoSuchAlgorithmException {

        /**
         * ��ȡMD5ժҪ�����㷨ʵ��
         */
        MessageDigest md5Intance = MessageDigest.getInstance("MD5");
//ͨ��ʹ�� update ������������,ʹָ���� byte�������ժҪ
        md5Intance.update(input.getBytes());
        //���������ɹ�ϣ����,����128 λ�ĳ����� 
        byte[] md5Encrypt = md5Intance.digest();

        /**
         * MD5�㷨��ȡ�̶�����(16λ)ժҪ��Ϣ��������Ϊ����λ�͵���λ��������ժҪ��Ϣ������(32λ)
         */
        char[] results = new char[md5Encrypt.length * 2];
//ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ�  
        int j = 0;//��ʾת������ж�Ӧ���ַ�λ��  
        for (int i = 0; i < md5Encrypt.length; ++i) {
//�ӵ�һ���ֽڿ�ʼ����ÿһ���ֽ�,ת���� 16 �����ַ���ת��  
            /**
             * ÿ��byte��8λ��ȡ����λ���ߵ���λ�������15��1111���������±��hexDigints��ȡֵ������Խ�硣
             */
            results[j++] = hexDigints[(md5Encrypt[i] >>> 4) & 0xF]; //ȡÿ���ַ�����λ�����±��Ӧ��ʮ�������ַ�
            //>>> Ϊ�߼����ƣ�������λһ������  
            results[j++] = hexDigints[md5Encrypt[i] & 0xF]; //ȡ����λ��Ӧ���ַ�
        }

        return String.valueOf(results);//����Ľ��ת��Ϊ�ַ���  

    }

//public class StartupMD5 {
public static void main(String[] args) {
	try {
	System.out.println(hash.encrypt("ʹ��keygenerator����������keygenerator.getInstance()������Ҫ�����Ӧ�ļ����㷨�������Ӷ����ɶ�Ӧ���ȵ�secret key����ˣ�����Ự��Կ��������ʹ�õ�DES�㷨��������С�����ɵ���Կ��56bit+8bit��żУ��λ��"));
	} catch (NoSuchAlgorithmException  e) {
			e.printStackTrace();
	}	
}
}
