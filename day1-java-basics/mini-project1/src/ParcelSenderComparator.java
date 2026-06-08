import java.util.Comparator;

public class ParcelSenderComparator implements Comparator<Parcel> {
    @Override
    public int compare(Parcel p1, Parcel p2) {
        return p1.getSender().compareTo(p2.getSender());
    }
}
