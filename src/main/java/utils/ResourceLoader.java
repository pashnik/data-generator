package utils;

import utils.objectResources.DataConfigResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ResourceLoader {

    private static ResourceLoader loader;

    private ResourceLoader() {
    }

    public static ResourceLoader getInstance() {
        if (loader == null) {
            loader = new ResourceLoader();
        }
        return loader;
    }

    public DataConfigResource getDataObject(String path) {
        Properties properties = new Properties();
        DataConfigResource data = new DataConfigResource();
        try (Reader stream = new InputStreamReader(new FileInputStream(path),
                StandardCharsets.UTF_8)) {
            properties.load(stream);

            data.setManufacturerNumber(Integer.parseInt(
                    (String) properties.get("manufacturer_number")));
            data.setCarNumber(Integer.parseInt(
                    (String) properties.get("car_number")));
            data.setComplectationNumber(Integer.parseInt(
                    (String) properties.get("complectation_number")));
            data.setTransmissionNumber(Integer.parseInt(
                    (String) properties.get("transmission_number")));
            data.setBodyNumber(Integer.parseInt(
                    (String) properties.get("body_number")));
            data.setEngineNumber(Integer.parseInt(
                    (String) properties.get("engine_number")));
            data.setOptionalNumber(Integer.parseInt((String)
                    properties.get("optional_number")));
            data.setComplectationOptionalNumber(Integer.parseInt(
                    (String) properties.get("complectation_optional_number")));
            data.setComplectationEngineNumber(Integer.parseInt(
                    (String) properties.get("complectation_engine_number")));
            data.setOptionalComplectationNumber(Integer.parseInt(
                    (String) properties.get("optional_complectation_number")));
            data.setEngineComplectationNumber(Integer.parseInt(
                    (String) properties.get("engine_complectation_number")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
