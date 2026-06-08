public class InternationShipping implements ShippingMethod{
    @Override
    public double calculateFee(double weight){
        return weight *50;
    }

    @Override
    public String getType(){
        return "International";
    }
}
