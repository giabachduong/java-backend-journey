public class ExpressShipping implements ShippingMethod{

    @Override
    public double calculateFee(double weight) {
        return weight * 20;
    }

    @Override
    public String getType(){
        return "Express";
    }
}
