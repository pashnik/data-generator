package DataModels;

import javax.persistence.*;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    public Manufacturer() {
    }

    public Manufacturer(String countryName, String manufacturerName) {
        this.countryName = countryName;
        this.manufacturerName = manufacturerName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
