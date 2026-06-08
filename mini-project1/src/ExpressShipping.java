public class ExpressShipping implements ShippingMethod{

    @Override
    public double calculateFee(double weight){
        return weight * 20;
    }
}
