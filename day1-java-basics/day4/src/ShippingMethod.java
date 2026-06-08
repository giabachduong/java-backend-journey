public interface ShippingMethod {

    double calculateFee(double weight);
    String getType();
}
