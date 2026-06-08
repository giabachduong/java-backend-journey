public enum ParcelStatus {
    PENDING("Pending"),
    SHIPPING("Shipping"),
    DELIVERED("Delivered");

    private final String displayName;

    ParcelStatus(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public static ParcelStatus fromInput(String input){
        for(ParcelStatus status : ParcelStatus.values()){
            if(status.displayName.equalsIgnoreCase(input)){
                return status;
            }
        }

        return null;
    }
}
