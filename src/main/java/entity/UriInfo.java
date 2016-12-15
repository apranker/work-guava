package entity;

/**
 * Created by dayong.gao on 2016/12/15.
 */
public class UriInfo {

    public String getUrivalue() {
        return urivalue;
    }

    public void setUrivalue(String urivalue) {
        this.urivalue = urivalue;
    }

    public String getUriType() {
        return uriType;
    }

    public void setUriType(String uriType) {
        this.uriType = uriType;
    }

    @Override public String toString() {
        return "UriInfo{" +
                "uriType='" + uriType + '\'' +
                ", urivalue='" + urivalue + '\'' +
                '}';
    }

    public UriInfo(String uriType, String urivalue) {
        this.uriType = uriType;
        this.urivalue = urivalue;
    }

    private String uriType;
    private String urivalue;
}
