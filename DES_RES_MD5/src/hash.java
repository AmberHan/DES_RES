import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5算法，将一串字符串加密转换为32位数据+大写字母的字符串。
 * 
 * @author elon
 * @version 2018年4月5日
 */
public class hash {

    /**
     * 定义16进制包含的字符，用于加密串转换
     */
    private static final char[] hexDigints = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 加密后返回十六进制的密文。
     * 
     * @param input 输入字符串
     * @return 密文
     * @throws NoSuchAlgorithmException 
     */
    public static String encrypt(String input) throws NoSuchAlgorithmException {

        /**
         * 获取MD5摘要加密算法实例
         */
        MessageDigest md5Intance = MessageDigest.getInstance("MD5");
//通过使用 update 方法处理数据,使指定的 byte数组更新摘要
        md5Intance.update(input.getBytes());
        //获得密文完成哈希计算,产生128 位的长整数 
        byte[] md5Encrypt = md5Intance.digest();

        /**
         * MD5算法提取固定长度(16位)摘要信息。结果拆分为高四位和低四位，长度是摘要信息的两倍(32位)
         */
        char[] results = new char[md5Encrypt.length * 2];
//每个字节用 16 进制表示的话，使用两个字符  
        int j = 0;//表示转换结果中对应的字符位置  
        for (int i = 0; i < md5Encrypt.length; ++i) {
//从第一个字节开始，对每一个字节,转换成 16 进制字符的转换  
            /**
             * 每个byte是8位，取高四位或者低四位，最多是15（1111）。用作下标从hexDigints中取值，不会越界。
             */
            results[j++] = hexDigints[(md5Encrypt[i] >>> 4) & 0xF]; //取每个字符高四位数字下标对应的十六进制字符
            //>>> 为逻辑右移，将符号位一起右移  
            results[j++] = hexDigints[md5Encrypt[i] & 0xF]; //取低四位对应的字符
        }

        return String.valueOf(results);//换后的结果转换为字符串  

    }

//public class StartupMD5 {
public static void main(String[] args) {
	try {
	System.out.println(hash.encrypt("使用keygenerator函数，其中keygenerator.getInstance()函数需要输入对应的加密算法方法，从而生成对应长度的secret key。因此，这里会话密钥生成我们使用的DES算法，计算量小，生成的密钥是56bit+8bit奇偶校验位。"));
	} catch (NoSuchAlgorithmException  e) {
			e.printStackTrace();
	}	
}
}
