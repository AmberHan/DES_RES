import javax.swing.*;

import java.awt.*;

public class BorderLayoutDemo1 extends JFrame
{
	JLabel b0,b1,b2;//��ǩ
	JTextArea t0,t1,t2;//�ı���
	JScrollPane jspane0,jspane1,jspane2;//��������
	JPanel p = new JPanel(new GridLayout(3, 1, 3, 3));//������

	JPanel p0 = new JPanel(new BorderLayout());//�м��������
	JPanel p1 = new JPanel(new BorderLayout());//�м��м����
	JPanel p2 = new JPanel(new BorderLayout());//�м��������	
	JPanel p3 = new JPanel(new GridLayout(3,1, 3, 3));//�м����1
	
	JPanel p4 = new JPanel(new GridLayout(1,7,13,13));//���ܽ��� ��
	JPanel p5 = new JPanel(new BorderLayout());//�ļ�  ��
	JPanel p7 = new JPanel(new BorderLayout());//�м����
	JPanel p8 = new JPanel(new BorderLayout());//�м������Ҫ�ĵı���
	JPanel p9 = new JPanel(new BorderLayout());//�м������Ҫ�ĵı���
    String str[] = {"DES", "RSA", "DES&RSA"};
    public BorderLayoutDemo1()
    {

        //�������Ϊ��ʽ���־�����ʾ������ᡢ�ݼ��Ϊ5������
        setLayout(new BorderLayout(5,5));
        for(int i = 0; i <3; i++)
        {
           JButton b = new JButton(str[i]);
           p.add(b);
            //����ť��ӵ�����������
        }
        getContentPane().add("North", new JLabel("��ӭʹ�����翼�����ݼ���ģ��ϵͳ",JLabel.CENTER));
        getContentPane().add(p, BorderLayout.WEST);//�������ӵ����λ��
        
        b0=new JLabel("�������룺",JLabel.LEFT);
        p0.add("North",b0);
        t0=new JTextArea();        
        t0.setLineWrap(true);	//���ö����ı����Զ�����
		jspane0=new JScrollPane(t0);	//������������
        p0.add("Center",jspane0);
        //�� ��
        final JTextField file = new JTextField();
        final JButton scan = new JButton("�ļ����");
        final JTextField pubkeyT = new JTextField();
        p5.add("Center",file);
        p5.add("East",scan); 
        p0.add(p5, BorderLayout.SOUTH);//�������ӵ��ұ߱�λ��  
   
        
        b1=new JLabel("���������",JLabel.LEFT);
        p1.add("North",b1);
        t1=new JTextArea();        
        t1.setLineWrap(true);	//���ö����ı����Զ�����
		jspane1=new JScrollPane(t1);	//������������
        p1.add("Center",jspane1);
        
        
        b2=new JLabel("���������",JLabel.LEFT);
        p2.add("North",b2);
        t2=new JTextArea();        
        t2.setLineWrap(true);	//���ö����ı����Զ�����
		jspane2=new JScrollPane(t2);	//������������
        p2.add("Center",jspane2);
        
        //���ܽ���
//        p4.add(new Panel());
        p4.add(new JButton("Encrypt"));
//        p4.add(new Panel());
        p4.add(new JButton("Decrypt"));
 //       p4.add(new Panel());
        p4.add(new JButton("Clean"));
 //       p4.add(new Panel());
        p2.add(p4, BorderLayout.SOUTH);//�������ӵ��ұ߱�λ��     
        
        
 //       p3.add(new Panel());//�м�ؼ�
        p3.add(p0);
        p3.add(p1);
        p3.add(p2);
        p7.add(p3);
        
        p8.add(p7, BorderLayout.CENTER);
        p9.add(new JLabel("��Կ��",JLabel.LEFT), BorderLayout.WEST);
        p9.add(pubkeyT,BorderLayout.CENTER);
        p8.add(p9, BorderLayout.NORTH);
    //    getContentPane().add(p9, BorderLayout.NORTH);
        getContentPane().add(p8, BorderLayout.CENTER);
    }

    public static void main(String args[])
    {
        BorderLayoutDemo1 f = new BorderLayoutDemo1();
        f.setTitle("����");
        f.setSize(700, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        //�ô��������ʾ
    }
}
