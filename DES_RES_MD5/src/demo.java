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
        for (int i = 0; i < byteArray.length; i++)  //因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
        {
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray
     *            需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String toHexString(byte[] byteArray)
    {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++)
        {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    public static void main(String args[])
    {
        JLabel c0, c1, c2, c3, c4, c5; //标签
        JButton c10, c11, c12, c13, c14, c15, c16, c17; //按钮
        JScrollPane jspane4, jspane5, jspane6, jspane7; //滚动窗口
        JPanel d0 = new JPanel(new BorderLayout());//0
        JPanel d1 = new JPanel(new BorderLayout());//1
        JPanel d2 = new JPanel(new BorderLayout());//2
        JPanel d3 = new JPanel(new BorderLayout());//3
        JPanel d4 = new JPanel(new BorderLayout());//4
        JPanel d5 = new JPanel(new GridLayout(1, 4, 13, 13)); //5

        JPanel d6 = new JPanel(new BorderLayout());//中心2,3
        JPanel d7 = new JPanel(new BorderLayout());//中心0，1
        JPanel d8 = new JPanel(new GridLayout(3, 1, 13, 13)); //总

        //    	JPanel d9 = new JPanel(new GridLayout(2,1,13,13));//新增内容
        //    	JPanel d10 = new JPanel(new BorderLayout());
        //    	JPanel d11 = new JPanel(new BorderLayout());

        JFrame f1 = new JFrame("解密");
        //设置面板为流式布局居中显示，组件横、纵间距为5个像素
        f1.setLayout(new BorderLayout(5, 5));

        //0,1,p7
        c10 = new JButton("解密会话密钥");//0
        final JTextField n0 = new JTextField();
        d0.add("Center", n0);
        d0.add("East", c10);

        c1 = new JLabel("接收到的会话密钥:");//1
        final JTextArea n1 = new JTextArea();
        n1.setLineWrap(true);	//设置多行文本框自动换行
        jspane4 = new JScrollPane(n1);	//创建滚动窗格
        d1.add("Center", jspane4);
        d1.add("North", c1);
        d7.add("South", d0);
        d7.add("Center", d1);

        //2,3;d6
        c11 = new JButton("  解密甄别码  ");//2
        final JTextField n2 = new JTextField();
        d2.add("Center", n2);
        d2.add("East", c11);

        c2 = new JLabel("加密后的试卷内容:");//3
        d3.add("North", c2);
        final JTextArea s0 = new JTextArea();
        s0.setLineWrap(true);	//设置多行文本框自动换行
        jspane5 = new JScrollPane(s0);	//创建滚动窗格
        d3.add("Center", jspane5);

        d6.add("South", d2); //2,3
        d6.add("Center", d3);

        //4,d4
        c3 = new JLabel("解密试卷:");//4
        d4.add("North", c3);
        final JTextArea s1 = new JTextArea();
        s1.setLineWrap(true);	//设置多行文本框自动换行
        jspane6 = new JScrollPane(s1);	//创建滚动窗格
        d4.add("Center", jspane6);
        final JTextField n3 = new JTextField();
        d4.add("South", n3);
        
        
        //5,d5
        c12 = new JButton("解密试卷");//5
        c13 = new JButton("验证");
        c14 = new JButton("保存");
        c15 = new JButton("清除");
        d5.add(c12);
        d5.add(c13);
        d5.add(c14);
        d5.add(c15);

        //hash解密d9
        //       c16=new JButton("  MD5加密  ");//d10
        //       d10.add("East",c16);
        //       final JTextField s11 = new JTextField();
        //       d10.add("Center",s11);
        //
        //       c17=new JButton("甄别码加密");//p11
        //       d11.add("East",c17);
        //       final JTextArea s2=new JTextArea();
        //       s2.setLineWrap(true);	//设置多行文本框自动换行
        //       jspane7 = new JScrollPane(s2);
        //       d11.add("Center",jspane7);
        //       d9.add(d10);//d9
        //       d9.add(d11);


        //总
        d8.add(d7);
        d8.add(d6);
        d8.add(d4);
        //     d8.add(d9);

        c0 = new JLabel("考 试 模 拟 系 统 （  接 收  端  ）", JLabel.CENTER); //标题
        f1.getContentPane().add("North", c0);
        f1.getContentPane().add(d8, BorderLayout.CENTER);//将面板添加到左边位置
        f1.getContentPane().add("South", d5);

        f1.setTitle("解密");
        f1.setSize(700, 600);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);
        //让窗体居中显示
        //清空
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



        //解密会话
        c10.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    byte[] str2 = toByteArray(n1.getText());   //加密文本获取
                    String ctext1 = RSAProvider.decryptPrivateKey(str2, siyao); //私钥解密
                    n0.setText(ctext1);   //解密
                    System.out.println("解密会话密钥：" + ctext1);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //解密甄别码
        c11.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String strtext = s0.getText().toString();   //加密文本获取
                    String[] strs=strtext.split("&");
                    byte[] str5 = toByteArray(strs[1].toString());   //加密文本获取
                    String ctext1 = RSAProvider.decryptPrivateKey(str5, siyao); //私钥解密
                    n2.setText(ctext1);   //解密
                    System.out.println("解密甄别码：" + ctext1);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //解密试卷
        c12.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String strtext = s0.getText().toString();   //加密文本获取
                    String[] strs=strtext.split("&");                	
                    byte[] str2 = toByteArray(strs[0].toString());   //加密文本获取
                    String str0 = n0.getText().toString();   //对称密钥获取
                    byte[] ctext1 = DESProvider.decrypt(str2, str0); //DES解密
                    s1.setText(new String(ctext1));   //解密
                    System.out.println("混合文本：" + new String(ctext1));
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //MD5加密
        c13.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = s1.getText().toString();   //原文获取
                    System.out.println("原文数据：" + str1);
                    String HashData1 = n2.getText().toString();   //Hash获取
                    String HashData = hash.encrypt(str1);
                    n3.setText("");
                    n3.setText("解密试卷原文MD5加密："+HashData);
                    System.out.println("解密试卷Hash加密数据：" + HashData);
                    if(HashData.equals("")||HashData.equals("")) {
                        JOptionPane.showMessageDialog(null, "请执行之前的步骤", "提示", JOptionPane.ERROR_MESSAGE);
                        }
                    else {
                    	if(HashData.equals(HashData1)) {
                    		JOptionPane.showMessageDialog(null, "原文未修改", "提示", JOptionPane.ERROR_MESSAGE);
                    	}
                    	else {
                    		JOptionPane.showMessageDialog(null, "原文被篡改", "提示", JOptionPane.ERROR_MESSAGE);
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


        //保存
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
                            if (!file.exists())  //文件不存在 则创建一个
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
                            System.err.println("文件创建失败：");
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
        JLabel b0, b1, b2, b3, b4, b5; //标签
        JButton b10, b11, b12, b13, b14, b15, b16, b17; //按钮
        JScrollPane jspane0, jspane1, jspanel2, jspanel3; //滚动窗口
        JPanel p0 = new JPanel(new BorderLayout());//0
        JPanel p1 = new JPanel(new BorderLayout());//1
        JPanel p2 = new JPanel(new BorderLayout());//2
        JPanel p3 = new JPanel(new BorderLayout());//3
        JPanel p4 = new JPanel(new BorderLayout());//4
        JPanel p5 = new JPanel(new GridLayout(1, 4, 13, 13)); //5


        JPanel p6 = new JPanel(new BorderLayout());//中心2,3
        JPanel p7 = new JPanel(new BorderLayout());//中心0，1
        JPanel p8 = new JPanel(new GridLayout(4, 1, 13, 13)); //总

        JPanel p9 = new JPanel(new GridLayout(2, 1, 13, 13)); //新增内容
        JPanel p10 = new JPanel(new BorderLayout());
        JPanel p11 = new JPanel(new BorderLayout());

        JFrame f = new JFrame("加密");
        //设置面板为流式布局居中显示，组件横、纵间距为5个像素
        f.setLayout(new BorderLayout(5, 5));

        //0,1,p7
        b10 = new JButton("生成会话密钥");//0
        final JTextField m0 = new JTextField();
        p0.add("Center", m0);
        p0.add("East", b10);

        b1 = new JLabel("加密会话密钥:");//1
        final JTextArea m1 = new JTextArea();
        m1.setLineWrap(true);	//设置多行文本框自动换行
        jspane0 = new JScrollPane(m1);	//创建滚动窗格
        p1.add("Center", jspane0);
        p1.add("North", b1);
        p7.add("North", p0);
        p7.add("Center", p1);

        //2,3;p6
        b11 = new JButton("试卷加载");//2
        final JTextField m2 = new JTextField();
        p2.add("Center", m2);
        p2.add("East", b11);

        b2 = new JLabel("试卷内容:");//3
        p3.add("North", b2);
        final JTextArea t0 = new JTextArea();
        t0.setLineWrap(true);	//设置多行文本框自动换行
        jspane1 = new JScrollPane(t0);	//创建滚动窗格
        p3.add("Center", jspane1);

        p6.add("North", p2); //2,3
        p6.add("Center", p3);

        //4,p4
        b3 = new JLabel("加密试卷:");//4
        p4.add("North", b3);
        final JTextArea t1 = new JTextArea();
        t1.setLineWrap(true);	//设置多行文本框自动换行
        jspanel2 = new JScrollPane(t1);	//创建滚动窗格
        p4.add("Center", jspanel2);

        //5,p5
        b12 = new JButton("加密会话密钥");//5
        b13 = new JButton("加密试卷");
        b14 = new JButton("发送");
        b15 = new JButton("清除");
        p5.add(b12);
        p5.add(b13);
        p5.add(b14);
        p5.add(b15);

        //hash加密p9
        b16 = new JButton("  MD5加密  "); //p10
        p10.add("East", b16);
        final JTextField m10 = new JTextField();
        p10.add("Center", m10);

        b17 = new JButton("甄别码加密"); //p11
        p11.add("East", b17);
        final JTextArea t2 = new JTextArea();
        t2.setLineWrap(true);	//设置多行文本框自动换行
        jspanel3 = new JScrollPane(t2);
        p11.add("Center", jspanel3);
        p9.add(p10);//p9
        p9.add(p11);

        //总
        p8.add(p6);
        p8.add(p7);
        p8.add(p4);
        p8.add(p9);

        b0 = new JLabel("考 试 模 拟 系 统 （ 发 送 端 ）", JLabel.CENTER); //标题
        f.getContentPane().add("North", b0);
        f.getContentPane().add(p8, BorderLayout.CENTER);//将面板添加到左边位置
        f.getContentPane().add("South", p5);

        f.setTitle("加密");
        f.setSize(700, 600);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        //让窗体居中显示


        //生成对话密钥
        b10.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String symmetricKey = DESProvider.generateKey();    //生成会话密钥
                    StreamUtil.printToFile("A/SymmetricKey", symmetricKey);
                    System.out.println("会话密钥：" + symmetricKey);
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

        //加密会话密钥
        b12.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = m0.getText().toString();   //获取对话密钥
                    //生成公钥：A/BPublicKey
                    Map<String, Object> keyPair = RSAProvider.generateKeyPair();//生成密钥对
                    String pubkey = RSAProvider.getPublicKeyBytes(keyPair);
                    StreamUtil.printToFile("A/BPublicKey", pubkey);

                    //生成私钥：B/BPrivateKey
                    String prikey = RSAProvider.getPrivateKeyBytes(keyPair);
                    StreamUtil.printToFile("B/BPrivateKey", prikey);
                    System.out.println("RSA公钥：" + pubkey);
                    System.out.println("RSA私钥：" + prikey);
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

        //试卷加载
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

        //加密试卷
        b13.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = t0.getText().toString();   //原文获取
                    System.out.println("混合加密明文数据：" + str1);
                    //进行原文DES加密
                    String SymmetricKey = StreamUtil.readFromFile("A/SymmetricKey");
                    byte[] ctext3 = DESProvider.encrypt(str1, SymmetricKey);//DES加密的原文
                    String Hunjiami = toHexString(ctext3);
                    t1.setText(Hunjiami);   //加密
                    System.out.println("DES加密后数据：" + Hunjiami);
                    StreamUtil.printToFile("加密文件/HUNHEjiami.txt", Hunjiami); //加密后的原文文件存储
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });


        //MD5加密
        b16.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = t0.getText().toString();   //原文获取
                    System.out.println("明文数据：" + str1);
                    String HashData = hash.encrypt(str1);
                    m10.setText("");
                    m10.setText(HashData);
                    System.out.println("Hash加密数据：" + HashData);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        //甄别码加密
        b17.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str1 = m10.getText().toString();   //原文获取
                    System.out.println("散列值数据：" + str1);
                    byte[] ctext11 = RSAProvider.encryptPublicKey(str1, gongyao);
                    String zhenbiema = toHexString(ctext11);
                    t2.setText("");
                    t2.setText(zhenbiema);
                    System.out.println("公钥：" + gongyao);
                    System.out.println("甄别码：" + zhenbiema);
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });


        //清除数据
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
        //发送
        b14.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                try
                {
                    String str2 = m1.getText().toString();   //会话获取
                    String str3 = t1.getText().toString();   //原文获取
                    String str4 = t2.getText().toString();   //甄别码获取
                    n1.setText(str2);
                    s0.setText(str3 + '&' + str4); //原文+甄别码

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
