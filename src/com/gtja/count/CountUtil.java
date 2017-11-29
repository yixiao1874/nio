package com.gtja.count;

import java.text.NumberFormat;
import java.util.*;

public class CountUtil {
    /**
     * 计算历史波动率
     * @param originalData  原始数据每日收盘价
     * @return  历史波动率
     */
    public static double countVolatility(double[] originalData){
        //计算得到所有ln值
        double[] doubles = countLn(originalData);

        //求出标准差
        double standardDiviation = sd(doubles);
        double volatility = standardDiviation*Math.sqrt(250);
        return volatility;
    }

    /**
     * 计算最大回撤
     * @param originalData  原始数据每日收盘价
     * @return  最大回撤
     */
    public static double maxDrawdown(double[] originalData){
        double[] difference = new double[originalData.length-1];
        for(int i=0; i<originalData.length-1; i++){
            difference[i] = originalData[i] - originalData[i+1];
        }
        Arrays.sort(difference);
        return difference[difference.length-1];
    }


    /**
     * 计算年化收益率
     * @param income    收益
     * @param principal 本金
     * @param day       天数
     * @return  年化收益率
     */
    public static String annualizedReturn(double income,double principal,int day){
        double annualizedReturn = (income/principal)*((double)250/day);
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);// 小数点后保留几位
        String str = nf.format(annualizedReturn);
        //annualizedReturn = Double.parseDouble(str);
        return str;
    }

    /**
     *
     * @param er    预期收益率
     * @param rf    无风险收益率
     * @param volatility    波动率
     * @return      夏普比率
     */
    public static double sharpeRatio(double er, double rf, double volatility){
        return (er - rf)/volatility;
    }

    /**
     * 贝塔值
     * @param cov   账户日收益与参考基准日收益的协方差
     * @param var   参考基准日收益的方差
     * @return  贝塔值
     */
    public static double beta(double cov,double var){
        return cov/var;
    }

    /**
     * @param annualizedReturn  账户年化收益
     * @param riskfree          无风险收益
     * @param beta              贝塔值
     * @param standardEarnings  参考基准年化收益率
     * @return  阿尔法值
     */
    public static double alpha(double annualizedReturn,double riskfree,double beta,double standardEarnings){
        return (annualizedReturn - riskfree) - beta*(standardEarnings - riskfree);
    }

    //计算ln(Si/Si-1)
    private static double[] countLn(double[] originalData) {
        double[] doubles = new double[originalData.length-1];
        for(int i=1; i<originalData.length; i++){
            doubles[i-1] = Math.log(originalData[i]/originalData[i-1]);
        }
        return doubles;
    }

    /**
     *
     * @param rf    无风险收益率
     * @param beta  贝塔值
     * @param erm   市场投资组合预期收益率
     * @return  预期收益率
     */
    public static double er(double rf,double beta,double erm){
        return rf + beta*(erm - rf);
    }

    //标准差σ=sqrt(s^2)
    private static double sd(double[] x) {
        int m=x.length;
        double sum=0;
        for(int i=0;i<m;i++){//求和
            sum+=x[i];
        }
        double dAve=sum/m;//求平均值
        double dVar=0;
        for(int i=0;i<m;i++){//求方差
            dVar+=(x[i]-dAve)*(x[i]-dAve);
        }
        return Math.sqrt(dVar/m);
    }

    public static void main(String[] args) {
        //System.out.println(annualizedReturn(300,10000,300));
        double[] doubles = {1.0,2.0,3.0,5.0,3.0,2.0,9.0,1.0};
        System.out.println(maxDrawdown(doubles));
    }
}
