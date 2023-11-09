package Model;

public class Contact extends PIR{
    private String identifier;
    private String name;
    private String address;
    private String mobile;
    public Contact(String identifier, String name, String address, String mobile){
        this.address = address;
        this.name = name;
        this.mobile = mobile;
        this.identifier = identifier;
    }
    @Override
    public <T> T accept(pirVisitor<T> visitor) {
        return visitor.visitContact(this);
    }

    public String getIdentifier(){
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }
}
