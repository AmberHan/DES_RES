import javax.swing.*;

import java.awt.*;

public class BorderLayoutDemo1 extends JFrame
{
	JLabel b0,b1,b2;//标签
	JTextArea t0,t1,t2;//文本框
	JScrollPane jspane0,jspane1,jspane2;//滚动窗口
	JPanel p = new JPanel(new GridLayout(3, 1, 3, 3));//左边面板

	JPanel p0 = new JPanel(new BorderLayout());//中间上面面板
	JPanel p1 = new JPanel(new BorderLayout());//中间中间面板
	JPanel p2 = new JPanel(new BorderLayout());//中间下面面板	
	JPanel p3 = new JPanel(new GridLayout(3,1, 3, 3));//中间面板1
	
	JPanel p4 = new JPanel(new GridLayout(1,7,13,13));//加密解密 右
	JPanel p5 = new JPanel(new BorderLayout());//文件  下
	JPanel p7 = new JPanel(new BorderLayout());//中间面板
	JPanel p8 = new JPanel(new BorderLayout());//中间面板需要改的变量
	JPanel p9 = new JPanel(new BorderLayout());//中间面板需要改的变量
    String str[] = {"DES", "RSA", "DES&RSA"};
    public BorderLayoutDemo1()
    {

        //设置面板为流式布局居中显示，组件横、纵间距为5个像素
        setLayout(new BorderLayout(5,5));
        for(int i = 0; i <3; i++)
        {
           JButton b = new JButton(str[i]);
           p.add(b);
            //将按钮添加到面板作左边中
        }
        getContentPane().add("North", new JLabel("欢迎使用网络考试数据加密模拟系统",JLabel.CENTER));
        getContentPane().add(p, BorderLayout.WEST);//将面板添加到左边位置
        
        b0=new JLabel("明文输入：",JLabel.LEFT);
        p0.add("North",b0);
        t0=new JTextArea();        
        t0.setLineWrap(true);	//设置多行文本框自动换行
		jspane0=new JScrollPane(t0);	//创建滚动窗格
        p0.add("Center",jspane0);
        //上 下
        final JTextField file = new JTextField();
        final JButton scan = new JButton("文件浏览");
        final JTextField pubkeyT = new JTextField();
        p5.add("Center",file);
        p5.add("East",scan); 
        p0.add(p5, BorderLayout.SOUTH);//将面板添加到右边边位置  
   
        
        b1=new JLabel("加密输出：",JLabel.LEFT);
        p1.add("North",b1);
        t1=new JTextArea();        
        t1.setLineWrap(true);	//设置多行文本框自动换行
		jspane1=new JScrollPane(t1);	//创建滚动窗格
        p1.add("Center",jspane1);
        
        
        b2=new JLabel("解密输出：",JLabel.LEFT);
        p2.add("North",b2);
        t2=new JTextArea();        
        t2.setLineWrap(true);	//设置多行文本框自动换行
		jspane2=new JScrollPane(t2);	//创建滚动窗格
        p2.add("Center",jspane2);
        
        //加密解密
//        p4.add(new Panel());
        p4.add(new JButton("Encrypt"));
//        p4.add(new Panel());
        p4.add(new JButton("Decrypt"));
 //       p4.add(new Panel());
        p4.add(new JButton("Clean"));
 //       p4.add(new Panel());
        p2.add(p4, BorderLayout.SOUTH);//将面板添加到右边边位置     
        
        
 //       p3.add(new Panel());//中间控件
        p3.add(p0);
        p3.add(p1);
        p3.add(p2);
        p7.add(p3);
        
        p8.add(p7, BorderLayout.CENTER);
        p9.add(new JLabel("密钥：",JLabel.LEFT), BorderLayout.WEST);
        p9.add(pubkeyT,BorderLayout.CENTER);
        p8.add(p9, BorderLayout.NORTH);
    //    getContentPane().add(p9, BorderLayout.NORTH);
        getContentPane().add(p8, BorderLayout.CENTER);
    }

    public static void main(String args[])
    {
        BorderLayoutDemo1 f = new BorderLayoutDemo1();
        f.setTitle("加密");
        f.setSize(700, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        //让窗体居中显示
    }
}
