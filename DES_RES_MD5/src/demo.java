import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
public class demo extends JFrame
{
    static int v = 0;
    static int w = 0;
    static String siyao, gongyao;
    public static byte[] toByteArray(String hexString)
    {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++)  //��Ϊ��16���ƣ����ֻ��ռ��4λ��ת�����ֽ���Ҫ����16���Ƶ��ַ�����λ����
        {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * �ֽ�����ת��16���Ʊ�ʾ��ʽ���ַ���
     *
     * @param byteArray
     *            ��Ҫת�����ֽ�����
     * @return 16���Ʊ�ʾ��ʽ���ַ���
     **/
    public static String toHexString(byte[] byteArray)
    {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++)
        {
            if ((byteArray[i] & 0xff) < 0x10)//0~Fǰ�治��
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    public static void main(String args[])
    {
        JLabel c0, c1, c2, c3, c4, c5; //��ǩ
        JButton c10, c11, c12, c13, c14, c15, c16, c17; //��ť
        JScrollPane jspane4, jspane5, jspane6, jspane7; //��������
        JPanel d0 = new JPanel(new BorderLayout());//0
        JPanel d1 = new JPanel(new BorderLayout());//1
        JPanel d2 = new JPanel(new BorderLayout());//2
        JPanel d3 = new JPanel(new BorderLayout());//3
        JPanel d4 = new JPanel(new BorderLayout());//4
        JPanel d5 = new JPanel(new GridLayout(1, 4, 13, 13)); //5

        JPanel d6 = new JPanel(new BorderLayout());//����2,3
        JPanel d7 = new JPanel(new BorderLayout());//����0��1
        JPanel d8 = new JPanel(new GridLayout(3, 1, 13, 13)); //��

        //    	JPanel d9 = new JPanel(new GridLayout(2,1,13,13));//��������
        //    	JPanel d10 = new JPanel(new BorderLayout());
        //    	JPanel d11 = new JPanel(new BorderLayout());

        JFrame f1 = new JFrame("����");
        //�������Ϊ��ʽ���־�����ʾ������ᡢ�ݼ��Ϊ5������
        f1.setLayout(new BorderLayout(5, 5));

        //0,1,p7
        c10 = new JButton("���ܻỰ��Կ");//0
        final JTextField n0 = new JTextField();
        d0.add("Center", n0);
        d0.add("East", c10);

        c1 = new JLabel("���յ��ĻỰ��Կ:");//1
        final JTextArea n1 = new JTextArea();
        n1.setLineWrap(true);	//���ö����ı����Զ�����
        jspane4 = new JScrollPane(n1);	//������������
        d1.add("Center", jspane4);
        d1.add("North", c1);
        d7.add("South", d0);
        d7.add("Center", d1);

        //2,3;d6
        c11 = new JButton("  ���������  ");//2
        final JTextField n2 = new JTextField();
        d2.add("Center", n2);
        d2.add("East", c11);

        c2 = new JLabel("���ܺ���Ծ�����:");//3
        d3.add("North", c2);
        final JTextArea s0 = new JTextArea();
        s0.setLineWrap(true);	//���ö����ı����Զ�����
        jspane5 = new JScrollPane(s0);	//������������
        d3.add("Center", jspane5);

        d6.add("South", d2); //2,3
        d6.add("Center", d3);

        //4,d4
        c3 = new JLabel("�����Ծ�:");//4
        d4.add("North", c3);
        final JTextArea s1 = new JTextArea();
        s1.setLineWrap(true);	//���ö����ı����Զ�����
        jspane6 = new JScrollPane(s1);	//������������
        d4.add("Center", jspane6);
        final JTextField n3 = new JTextField();
        d4.add("South", n3);
        
        
        //5,d5
        c12 = new JButton("�����Ծ�");//5
        c13 = new JButton("��֤");
        c14 = new JButton("����");
        c15 = new JButton("���");
        d5.add(c12);
        d5.add(c13);
        d5.add(c14);
        d5.add(c15);

        //hash����d9
        //       c16=new JButton("  MD5����  ");//d10
        //       d10.add("East",c16);
        //       final JTextField s11 = new JTextField();
        //       d10.add("Center",s11);
        //
        //       c17=new JButton("��������");//p11
        //       d11.add("East",c17);
        //       final JTextArea s2=new JTextArea();
        //       s2.setLineWrap(true);	//���ö����ı����Զ�����
        //       jspane7 = new JScrollPane(s2);
        //       d11.add("Center",jspane7);
        //       d9.add(d10);//d9
        //       d9.add(d11);


        //��
        d8.add(d7);
        d8.add(d6);
        d8.add(d4);
        //     d8.add(d9);

        c0 = new JLabel("�� �� ģ �� ϵ ͳ ��  �� ��  ��  ��", JLabel.CENTER); //����
        f1.getContentPane().add("North", c0);
        f1.getContentPane().add(d8, BorderLayout.CENTER);//�������ӵ����λ��
        f1.getContentPane().add("South", d5);

        f1.setTitle("����");
        f1.setSize(700, 600);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);
        //�ô��������ʾ
        //���
        c15.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    s0.setText("");
                    s1.setText("");
                    n0.setText("");
                    n1.setText("");
                    n2.setText("");
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });



        //���ܻỰ
        c10.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    byte[] str2 = toByteArray(n1.getText());   //�����ı���ȡ
                    String ctext1 = RSAProvider.decryptPrivateKey(str2, siyao); //˽Կ����
                    n0.setText(ctext1);   //����
                    System.out.println("���ܻỰ��Կ��" + ctext1);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //���������
        c11.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String strtext = s0.getText().toString();   //�����ı���ȡ
                    String[] strs=strtext.split("&");
                    byte[] str5 = toByteArray(strs[1].toString());   //�����ı���ȡ
                    String ctext1 = RSAProvider.decryptPrivateKey(str5, siyao); //˽Կ����
                    n2.setText(ctext1);   //����
                    System.out.println("��������룺" + ctext1);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //�����Ծ�
        c12.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String strtext = s0.getText().toString();   //�����ı���ȡ
                    String[] strs=strtext.split("&");                	
                    byte[] str2 = toByteArray(strs[0].toString());   //�����ı���ȡ
                    String str0 = n0.getText().toString();   //�Գ���Կ��ȡ
                    byte[] ctext1 = DESProvider.decrypt(str2, str0); //DES����
                    s1.setText(new String(ctext1));   //����
                    System.out.println("����ı���" + new String(ctext1));
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //MD5����
        c13.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = s1.getText().toString();   //ԭ�Ļ�ȡ
                    System.out.println("ԭ�����ݣ�" + str1);
                    String HashData1 = n2.getText().toString();   //Hash��ȡ
                    String HashData = hash.encrypt(str1);
                    n3.setText("");
                    n3.setText("�����Ծ�ԭ��MD5���ܣ�"+HashData);
                    System.out.println("�����Ծ�Hash�������ݣ�" + HashData);
                    if(HashData.equals("")||HashData.equals("")) {
                        JOptionPane.showMessageDialog(null, "��ִ��֮ǰ�Ĳ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
                        }
                    else {
                    	if(HashData.equals(HashData1)) {
                    		JOptionPane.showMessageDialog(null, "ԭ��δ�޸�", "��ʾ", JOptionPane.ERROR_MESSAGE);
                    	}
                    	else {
                    		JOptionPane.showMessageDialog(null, "ԭ�ı��۸�", "��ʾ", JOptionPane.ERROR_MESSAGE);
                    	}
                    }
                    
                    
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });


        //����
        c14.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {

                    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
                    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(filter);
                    fc.setMultiSelectionEnabled(false);
                    int result = fc.showSaveDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION)
                    {
                        File file = fc.getSelectedFile();
                        if (!file.getPath().endsWith(".txt"))
                        {
                            file = new File(file.getPath() + ".txt");
                        }
                        System.out.println("file path=" + file.getPath());
                        FileOutputStream fos = null;
                        try
                        {
                            if (!file.exists())  //�ļ������� �򴴽�һ��
                            {
                                file.createNewFile();
                            }
                            fos = new FileOutputStream(file);
                            String str0 = s1.getText().toString();
                            fos.write(str0.getBytes());
                            fos.flush();
                        }
                        catch (IOException e1)
                        {
                            System.err.println("�ļ�����ʧ�ܣ�");
                            e1.printStackTrace();
                        }
                        finally
                        {
                            if (fos != null)
                            {
                                try
                                {
                                    fos.close();
                                }
                                catch (IOException e1)
                                {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });






        
        
        
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        JLabel b0, b1, b2, b3, b4, b5; //��ǩ
        JButton b10, b11, b12, b13, b14, b15, b16, b17; //��ť
        JScrollPane jspane0, jspane1, jspanel2, jspanel3; //��������
        JPanel p0 = new JPanel(new BorderLayout());//0
        JPanel p1 = new JPanel(new BorderLayout());//1
        JPanel p2 = new JPanel(new BorderLayout());//2
        JPanel p3 = new JPanel(new BorderLayout());//3
        JPanel p4 = new JPanel(new BorderLayout());//4
        JPanel p5 = new JPanel(new GridLayout(1, 4, 13, 13)); //5


        JPanel p6 = new JPanel(new BorderLayout());//����2,3
        JPanel p7 = new JPanel(new BorderLayout());//����0��1
        JPanel p8 = new JPanel(new GridLayout(4, 1, 13, 13)); //��

        JPanel p9 = new JPanel(new GridLayout(2, 1, 13, 13)); //��������
        JPanel p10 = new JPanel(new BorderLayout());
        JPanel p11 = new JPanel(new BorderLayout());

        JFrame f = new JFrame("����");
        //�������Ϊ��ʽ���־�����ʾ������ᡢ�ݼ��Ϊ5������
        f.setLayout(new BorderLayout(5, 5));

        //0,1,p7
        b10 = new JButton("���ɻỰ��Կ");//0
        final JTextField m0 = new JTextField();
        p0.add("Center", m0);
        p0.add("East", b10);

        b1 = new JLabel("���ܻỰ��Կ:");//1
        final JTextArea m1 = new JTextArea();
        m1.setLineWrap(true);	//���ö����ı����Զ�����
        jspane0 = new JScrollPane(m1);	//������������
        p1.add("Center", jspane0);
        p1.add("North", b1);
        p7.add("North", p0);
        p7.add("Center", p1);

        //2,3;p6
        b11 = new JButton("�Ծ����");//2
        final JTextField m2 = new JTextField();
        p2.add("Center", m2);
        p2.add("East", b11);

        b2 = new JLabel("�Ծ�����:");//3
        p3.add("North", b2);
        final JTextArea t0 = new JTextArea();
        t0.setLineWrap(true);	//���ö����ı����Զ�����
        jspane1 = new JScrollPane(t0);	//������������
        p3.add("Center", jspane1);

        p6.add("North", p2); //2,3
        p6.add("Center", p3);

        //4,p4
        b3 = new JLabel("�����Ծ�:");//4
        p4.add("North", b3);
        final JTextArea t1 = new JTextArea();
        t1.setLineWrap(true);	//���ö����ı����Զ�����
        jspanel2 = new JScrollPane(t1);	//������������
        p4.add("Center", jspanel2);

        //5,p5
        b12 = new JButton("���ܻỰ��Կ");//5
        b13 = new JButton("�����Ծ�");
        b14 = new JButton("����");
        b15 = new JButton("���");
        p5.add(b12);
        p5.add(b13);
        p5.add(b14);
        p5.add(b15);

        //hash����p9
        b16 = new JButton("  MD5����  "); //p10
        p10.add("East", b16);
        final JTextField m10 = new JTextField();
        p10.add("Center", m10);

        b17 = new JButton("��������"); //p11
        p11.add("East", b17);
        final JTextArea t2 = new JTextArea();
        t2.setLineWrap(true);	//���ö����ı����Զ�����
        jspanel3 = new JScrollPane(t2);
        p11.add("Center", jspanel3);
        p9.add(p10);//p9
        p9.add(p11);

        //��
        p8.add(p6);
        p8.add(p7);
        p8.add(p4);
        p8.add(p9);

        b0 = new JLabel("�� �� ģ �� ϵ ͳ �� �� �� �� ��", JLabel.CENTER); //����
        f.getContentPane().add("North", b0);
        f.getContentPane().add(p8, BorderLayout.CENTER);//�������ӵ����λ��
        f.getContentPane().add("South", p5);

        f.setTitle("����");
        f.setSize(700, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        //�ô��������ʾ


        //���ɶԻ���Կ
        b10.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String symmetricKey = DESProvider.generateKey();    //���ɻỰ��Կ
                    StreamUtil.printToFile("A/SymmetricKey", symmetricKey);
                    System.out.println("�Ự��Կ��" + symmetricKey);
                    m0.setText(symmetricKey);
                    m1.setText("");
                    t1.setText("");
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //���ܻỰ��Կ
        b12.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = m0.getText().toString();   //��ȡ�Ի���Կ
                    //���ɹ�Կ��A/BPublicKey
                    Map<String, Object> keyPair = RSAProvider.generateKeyPair();//������Կ��
                    String pubkey = RSAProvider.getPublicKeyBytes(keyPair);
                    StreamUtil.printToFile("A/BPublicKey", pubkey);

                    //����˽Կ��B/BPrivateKey
                    String prikey = RSAProvider.getPrivateKeyBytes(keyPair);
                    StreamUtil.printToFile("B/BPrivateKey", prikey);
                    System.out.println("RSA��Կ��" + pubkey);
                    System.out.println("RSA˽Կ��" + prikey);
                    siyao = prikey;
                    gongyao = pubkey;
                    byte[] ctext = RSAProvider.encryptPublicKey(str1, pubkey);
                    String Huihuajia = toHexString(ctext);
                    t1.setText("");
                    t2.setText("");
                    m1.setText(Huihuajia);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //�Ծ����
        b11.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    JFileChooser jfc = new JFileChooser();
                    if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    {
                        File file1 = jfc.getSelectedFile();
                        Scanner input = new Scanner(file1);
                        if(input.hasNext()){
                       	 String jiamiData = StreamUtil.readFromFile(file1.getPath());
                       	 t0.setText(jiamiData);
                       	 m2.setText(file1.getPath()); 
                        }
                        input.close();
                    }
                    else
                        System.out.println("No file is selected!");
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //�����Ծ�
        b13.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = t0.getText().toString();   //ԭ�Ļ�ȡ
                    System.out.println("��ϼ����������ݣ�" + str1);
                    //����ԭ��DES����
                    String SymmetricKey = StreamUtil.readFromFile("A/SymmetricKey");
                    byte[] ctext3 = DESProvider.encrypt(str1, SymmetricKey);//DES���ܵ�ԭ��
                    String Hunjiami = toHexString(ctext3);
                    t1.setText(Hunjiami);   //����
                    System.out.println("DES���ܺ����ݣ�" + Hunjiami);
                    StreamUtil.printToFile("�����ļ�/HUNHEjiami.txt", Hunjiami); //���ܺ��ԭ���ļ��洢
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });


        //MD5����
        b16.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = t0.getText().toString();   //ԭ�Ļ�ȡ
                    System.out.println("�������ݣ�" + str1);
                    String HashData = hash.encrypt(str1);
                    m10.setText("");
                    m10.setText(HashData);
                    System.out.println("Hash�������ݣ�" + HashData);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //��������
        b17.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = m10.getText().toString();   //ԭ�Ļ�ȡ
                    System.out.println("ɢ��ֵ���ݣ�" + str1);
                    byte[] ctext11 = RSAProvider.encryptPublicKey(str1, gongyao);
                    String zhenbiema = toHexString(ctext11);
                    t2.setText("");
                    t2.setText(zhenbiema);
                    System.out.println("��Կ��" + gongyao);
                    System.out.println("����룺" + zhenbiema);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });


        //�������
        b15.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    t0.setText("");
                    t1.setText("");
                    t2.setText("");
                    m0.setText("");
                    m1.setText("");
                    m2.setText("");
                    m10.setText("");
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        //����
        b14.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str2 = m1.getText().toString();   //�Ự��ȡ
                    String str3 = t1.getText().toString();   //ԭ�Ļ�ȡ
                    String str4 = t2.getText().toString();   //������ȡ
                    n1.setText(str2);
                    s0.setText(str3 + '&' + str4); //ԭ��+�����

                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }
}
