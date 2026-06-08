import java.util.Comparator;

public class ParcelWeightComparator implements Comparator<Parcel> {
    @Override
    public int compare(Parcel p1, Parcel p2) {
        return Double.compare(p1.getWeight(), p2.getWeight());
    }
}
