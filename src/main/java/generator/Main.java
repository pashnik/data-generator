package generator;

import utils.ResourceLoader;
import utils.objectResources.DataConfigResource;

public class Main {

    private static final String DATA_PROPERTIES =
            "/Users/pashnik/IdeaProjects/data-generator/src/main/resources/data_config.properties";

    public static void main(String[] args) {
        DataConfigResource resource = ResourceLoader.getInstance().getDataObject(DATA_PROPERTIES);
        Aggregator aggregator = new Aggregator(resource);
        aggregator.fillTables();
        aggregator.stop();
    }
}
