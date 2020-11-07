import java.util.Map;

public class Main {
	public static void main(String[] args) throws Exception {
		String data1="����໰�";
		
		//���ɶԳ���Կ��A/SymmetricKey��

		String symmetricKey = DESProvider.generateKey();
		StreamUtil.printToFile("A/SymmetricKey",symmetricKey);
		
 //����ԭ��DES����
    	byte[] ctext3 = DESProvider.encrypt(data1, symmetricKey);
		
		//���ɹ�Կ��A/BPublicKey
    	Map<String, Object> keyPair = RSAProvider.generateKeyPair();//������Կ��
    	String pubkey = RSAProvider.getPublicKeyBytes(keyPair);
		StreamUtil.printToFile("A/BPublicKey", pubkey);
    	
		//����˽Կ��B/BPrivateKey
    	String prikey = RSAProvider.getPrivateKeyBytes(keyPair);
		StreamUtil.printToFile("B/BPrivateKey", prikey);
		
		//��Կ���ܣ��ù�ԿA/BPublicKey�ԶԳ���Կ����A/SymmetricKey��SecretKey
		String secretKey = StreamUtil.readFromFile("A/SymmetricKey");
    	byte[] ctext = RSAProvider.encryptPublicKey(secretKey,pubkey);
    	String text = StreamUtil.byteToBase64(ctext);
    	StreamUtil.printToFile("SecretKey", text);
		
		//˽Կ���ܣ���˽ԿB/BPrivateKey��SecretKey���ܣ�B/SymmetricKey
    	String text2 = StreamUtil.readFromFile("SecretKey");
    	byte[] ctext2 = StreamUtil.base64ToByte(text2);
    	String ptext = RSAProvider.decryptPrivateKey(ctext2, prikey);

 //�ûỰ���� �õ�ԭ������
    	byte[] ctext4=DESProvider.decrypt(ctext3,ptext);
    	
    	//��֤
    	System.out.println("Aԭ��="+data1);
    	System.out.println("��Կ="+pubkey);
    	System.out.println("Aԭ�ĻỰ��Կ���ܺ�="+new String(ctext3));    	
    	System.out.println("�Ự��Կ="+symmetricKey);
        System.out.println("A�Ự��ԿRSA����="+text);
    	System.out.println("B�Ự��ԿRSA����="+ptext);
    	System.out.println("B���ܵĻỰ��Կ���ܵõ���ԭ��="+new String(ctext4));
        
	}
}
