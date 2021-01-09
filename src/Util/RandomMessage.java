package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 随机生成商品信息并存入数据库
 */
public class RandomMessage {
    private static String firstName="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";

    private static String[] lastname=("牛奶,面包,卫生纸,烟,笔,盐,水枪").split(",");

    private static String[] sell = ("上架,下架").split(",");
    public static int getInt(int start,int end) {
        //随机返回返回指定范围间的整数
        //Math.random()随机返回0.0至1.0之间的数
        return (int) (Math.random() * (end - start + 1) + start);
    }
    public static Double getDouble(int start, int end) {
        //随机返回返回指定范围间的小数
        //Math.random()随机返回0.0至1.0之间的数
        return (Double) (Math.random() * (end - start + 1) + start);
    }
    //返回商品编号
    private static int getNum() {
        int NUM = getInt(200000,999999);
        return NUM;
    }
    //返回商品单价
    private static double getPrice(){
        double PRICE = getDouble(1,100);
        return PRICE;
    }
    //返回商品库存
    private static int getSum(){
        int SUM = getInt(1000,10000);
        return SUM;
    }
    //返回商品销量
    private static int getSales(){
        int SALES = getInt(1,1000);
        return SALES;
    }
    //返回销售状态
    private static String getSell(){
        int index = getInt(0,sell.length-1);
        String s = sell[index];
        return s;
    }
    public static void addData() {
        DbConnection dbcn=new DbConnection();//使用1中定义的连接数据库的类
        String sql="insert into objects(num,name,price,kind,sales,sum,sell) values(?,?,?,?,?,?,?)";//使用占位符定义插入语句
        try(Connection conn=dbcn.getConnection();//获取数据库接
            PreparedStatement stmt=conn.prepareStatement(sql);){//实例化PreparedStatement
            ArrayList<String> alist=new ArrayList<String>();//定义集合
            for(int i=1;i<=10000;) {
                int Num = getNum();
                String Name=null;//随机获取姓名
                int index1=getInt(0, firstName.length()-1);
                String first=firstName.substring(index1, index1+1);
                int index2 = getInt(0,lastname.length-1);
                String last=lastname[index2];
                Name = first + '氏' +last;
                double Price = getPrice();
                String Kind = "";
                if(last.equals("牛奶")){
                    Kind = "饮品";
                }else if(last.equals("面包")){
                    Kind = "食品";
                }else if(last.equals("卫生纸")){
                    Kind= "生活用品";
                }else if(last.equals("烟")){
                    Kind = "烟酒";
                }else if(last.equals("笔")){
                    Kind = "文具";
                }else if(last.equals("盐")){
                    Kind = "调味品";
                }else if(last.equals("水枪")){
                    Kind = "玩具";
                }
                int Sales=getSales();
                int Sum=getSum();
                String Sell=getSell();
                System.out.println(Num+"\t" + Name +"\t"+Price+"\t"+Kind+"\t"+Sales+"\t"+Sum+"\t"+Sell+"\t");
                stmt.setString(1, String.valueOf(Num));//定义第1个占位符的内容
                stmt.setString(2, Name);//定义第2个占位符的内
                stmt.setDouble(3,Price);//定义第3个占位符的内容
                stmt.setString(4, Kind);//定义第4个占位符的内容
                stmt.setInt(5, Sales);//定义第5个占位符的内容
                stmt.setInt(6, Sum);//定义第6个占位符的内容
                stmt.setString(7,Sell);
                stmt.executeUpdate();//加入批处理等待执行
                i++;//循环继续往下执行
            }
            stmt.executeBatch();//批量执行插入操作
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        RandomMessage r = new RandomMessage();
        r.addData();
    }
}
