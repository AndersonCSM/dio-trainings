public final class Salesman extends  Employee{
    private double percentPerSold;

    private double soldAmount;

    @Override
    public String getCode(){
        return "Salesman" + super.getCode();
    }

    public double getPercentPerSold() {
        return percentPerSold;
    }

    public void setPercentPerSold(double percentPerSold) {
        this.percentPerSold = percentPerSold;
    }

    public double getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(double soldAmount) {
        this.soldAmount = soldAmount;
    }

    @Override
    public double getFullSalary() {
        return 0;
    }
}
