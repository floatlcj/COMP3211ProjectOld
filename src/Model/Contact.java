package Model;

import java.io.Serializable;

public class Contact extends PIR implements Serializable {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        String contactStr = identifier + "\n" + name + "\n" + address + "\n" + mobile;
        return contactStr;
    }
}
