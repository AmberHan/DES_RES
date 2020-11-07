import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		String data1="你好嗷嗷啊";
		
		//生成对称密钥：A/SymmetricKey，

		String symmetricKey = DESProvider.generateKey();
		StreamUtil.printToFile("A/SymmetricKey",symmetricKey);
		
 //进行原文DES加密
    	byte[] ctext3 = DESProvider.encrypt(data1, symmetricKey);
		
		//生成公钥：A/BPublicKey
    	Map<String, Object> keyPair = RSAProvider.generateKeyPair();//生成密钥对
    	String pubkey = RSAProvider.getPublicKeyBytes(keyPair);
		StreamUtil.printToFile("A/BPublicKey", pubkey);
    	
		//生成私钥：B/BPrivateKey
    	String prikey = RSAProvider.getPrivateKeyBytes(keyPair);
		StreamUtil.printToFile("B/BPrivateKey", prikey);
		
		//公钥加密，用公钥A/BPublicKey对对称密钥加密A/SymmetricKey：SecretKey
		String secretKey = StreamUtil.readFromFile("A/SymmetricKey");
    	byte[] ctext = RSAProvider.encryptPublicKey(secretKey,pubkey);
    	String text = StreamUtil.byteToBase64(ctext);
    	StreamUtil.printToFile("SecretKey", text);
		
		//私钥解密，用私钥B/BPrivateKey对SecretKey解密：B/SymmetricKey
    	String text2 = StreamUtil.readFromFile("SecretKey");
    	byte[] ctext2 = StreamUtil.base64ToByte(text2);
    	String ptext = RSAProvider.decryptPrivateKey(ctext2, prikey);

 //用会话解密 得到原来数据
    	byte[] ctext4=DESProvider.decrypt(ctext3,ptext);
    	
    	//验证
    	System.out.println("A原文="+data1);
    	System.out.println("公钥="+pubkey);
    	System.out.println("A原文会话密钥加密后="+new String(ctext3));    	
    	System.out.println("会话密钥="+symmetricKey);
        System.out.println("A会话密钥RSA加密="+text);
    	System.out.println("B会话密钥RSA解密="+ptext);
    	System.out.println("B解密的会话密钥解密得到的原文="+new String(ctext4));
        
	}
}
