public class StandardShipping implements ShippingMethod{
    @Override
    public double calculateFee(double weight){
        return weight * 10;
    }

    @Override
    public String getType(){
        return "Standard";
    }
}
