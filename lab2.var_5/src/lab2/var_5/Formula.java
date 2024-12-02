package lab2.var_5;

public class Formula {
    private double sum=0;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return Double.toString(sum);
    }
}
