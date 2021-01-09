package Util;

import java.io.Serializable;

/**
 * 商品信息实体类
 */
public class Objects implements Serializable {

    private String num;
    private String name;
    private double price;
    private String kind;
    private int sales;
    private int sum;
    private String sell;
    public checkBox cb=new checkBox();

    //构造器
    public Objects(String Num,String Name,double Price,String Kind,int Sales,int Sum,String Sell){
        this.num = Num;
        this.name = Name;
        this.price = Price;
        this.kind = Kind;
        this.sales = Sales;
        this.sum = Sum;
        this.sell = Sell;
    }
    //空构造器
    public Objects(String Num){this.num = Num;}
    public String getNum(){return num;}
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public String getKind(){
        return kind;
    }
    public int getSales(){
        return sales;
    }
    public int getSum(){
        return sum;
    }
    public String getSell(){
        return sell;
    }

}
