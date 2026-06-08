import java.util.*;

public class ParcelRepository {

    private List<Parcel> parcels;

    public ParcelRepository() {
        this.parcels = new ArrayList<>();
    }

    public List<Parcel> getParcels() {
        return this.parcels;
    }

    public void addParcel(Parcel parcel) {
        this.parcels.add(parcel);
    }

    public void removeParcel(Parcel parcel) {
        this.parcels.remove(parcel);
    }

    public Optional<Parcel> findParcelById(int id) {
        for (Parcel p : this.parcels) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public void showAllParcels() {
        for (Parcel p : this.parcels) {
            p.displayInfo();
        }
    }
}




