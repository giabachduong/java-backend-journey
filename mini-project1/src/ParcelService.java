import java.util.*;

public class ParcelService {

    private ParcelRepository repository;

    public ParcelService(ParcelRepository repository){
        this.repository = repository;
    }

    public static boolean isValidStatus(String status){
        return ParcelStatus.fromInput(status) != null;
    }

    public void validateParcel(Parcel p) throws InvalidParcelException{
        if(p.getSender().isBlank()){
            throw new InvalidParcelException("Sender cannot be empty");
        }

        if(p.getReceiver().isBlank()){
            throw new InvalidParcelException("Receiver cannot be empty");
        }

        if(p.getWeight() <= 0){
            throw new InvalidParcelException("Weight must be positive");
        }

        if(getShippingMethod(p.getShippingType()).isEmpty()){
            throw new InvalidParcelException("Invalid shipping type");
        }
    }

    public Parcel createParcel(Parcel parcel) throws InvalidParcelException {
        validateParcel(parcel);

        repository.addParcel(parcel);

        return parcel;

    }

    public Optional<ShippingMethod> getShippingMethod(String shippingType){
        if(shippingType.equalsIgnoreCase("Standard")){
            return Optional.of(new StandardShipping());
        }
        else if(shippingType.equalsIgnoreCase("Express")){
            return Optional.of(new ExpressShipping());
        }

        return Optional.empty();
    }

    public Optional<Double> calculateShippingFee(Parcel parcel){
        Optional<ShippingMethod> shippingMethod = getShippingMethod(parcel.getShippingType());

        if(shippingMethod.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(shippingMethod.get().calculateFee(parcel.getWeight()));
    }

    public long countPendingParcels(){
        return repository.getParcels().stream().filter(
                p -> p.getStatus() == ParcelStatus.PENDING
        ).count();
    }

    public void sortByIdDescending(){
        repository.getParcels().sort(
                (a,b) -> Integer.compare(
                        b.getId(),
                        a.getId()
                )
        );
    }

    public void sortByDefaultId(){
        Collections.sort(repository.getParcels());
    }

    public void sortByWeight(){
        this.repository.getParcels().sort(new ParcelWeightComparator());
    }

    public HashSet<String> getUniqueSenders(){
        HashSet<String> uniqueSenders = new HashSet<>();

        for(Parcel p : this.repository.getParcels()){
            uniqueSenders.add(p.getSender());
        }

        return uniqueSenders;
    }

    public List<String> getSenderNames(){
        return repository.getParcels().stream()
                .map(Parcel::getSender)
                .toList();
    }

    public boolean anyShippingParcel(){
        return repository.getParcels().stream()
                .anyMatch(
                        p -> p.getStatus() == ParcelStatus.SHIPPING
                );
    }

    public boolean areAllDelivered(){
        return repository.getParcels().stream()
                .allMatch(
                        p -> p.getStatus() == ParcelStatus.DELIVERED
                );
    }

}
