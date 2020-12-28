# DES_RES_MD5
<<<<<<< HEAD
网络考试模拟系统，采用DES，RES，MD5混合加密的系统（暂不带http,仅仅本地模拟实现混合加密效果）
=======
网络考试模拟系统，采用DES，RES及多种混合加密解密的系统；分为服务端加密，客户端解密两部分（不带http,实现混合加密效果）
>>>>>>> 3170b166484374ed938f975a01cbb77cfcd3f636
 
# 简述
主要针对网络考试模拟系统中的试卷数据机密性和完整性两个特性,对比对称密钥加密和非对称密钥加密体制的优劣特点,对其中具有代表性的DES算法和RSA算法以及消息摘要函数MDS算法的原理进行详细分析,实现了一种基于DES、RSA和MDS算法的混合加密的网络考试模拟系统。

# 技术特点:
使用Java编程语言生成发送接收端两个界面,发送端一边将试卷内容进行MD5加密得到单项散列值,再用接收端的公钥进行加密,得到新的密文,称为鉴别码:同时用会话密钥对试卷内容加密,将鉴别码放在试卷密文前面形成扩展密文发给接收端:接收端收到扩展密文后,对前面的鉴别码用本身的私钥解密获取发送端发来的单项散列值会话密钥DES解密试卷密文,对试卷采用MD5加密得到一行新的散列值,两个散列值比较,如果一样则试卷没有被篡改,则保存试卷,完成传输。

# 软件流程（见代码流程图）
1. 发送端Alice:随机生成DES加密码（code1），通过公钥对code1加密得到A，经网络发送给Bob,bob进行私钥解密，得到code1;
2. 试卷采用DES加密，加密码为（code1），得到内容B；经网络发给Bob,Bob收到后，对B内容采用DES解密（即code1码），得到试卷内容。
3. 同时过程2，还对试卷采用MD5加密，得到甄别码，和内容C一起发送；C内容解密得到试卷明文后，MD5加密与甄别码进行校验。 

# 存在问题
不可以识别是不是Alice发送的，因为RSA使用的都是Bob的公钥，私钥。收到内容时候，我不知道是不是Alice发送的。

# 解决方案（见代码流程图）
增加一个预通信的过程，预通信的过程为，Alice和Bob告知各自的公钥；发送过程中，增加Alice的数字签名，Bob收到后，进行数字认证，确保是Alice发送的。


# 技术用途:
用于考试模拟系统中,采用混合加密算法,对数据进行加密,用以保护传输的数据,不易被他人监听或者篡改。

# 代码说明：
版权所有，已申请软著，如用于商业用途，请与我联系。
