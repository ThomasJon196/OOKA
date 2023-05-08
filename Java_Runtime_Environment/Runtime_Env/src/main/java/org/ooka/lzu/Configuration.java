// package org.ooka.lzu;

// import java.util.ArrayList;
// import java.util.List;

// public class Configuration {
//     private List<ComponentConfig> componentConfigs = new ArrayList<>();

//     public Configuration(List<Component> components) {
//         for (Component component : components) {
//             componentConfigs.add(new ComponentConfig(component.getFilePath()));
//         }
//     }

//     public List<ComponentConfig> getComponentConfigs() {
//         return componentConfigs;
//     }
// }

// Usefull when saving multiple component properties in a standardized way
// class ComponentConfig {
//     private String filePath;

//     public ComponentConfig(String filePath) {
//         this.filePath = filePath;
//     }

//     public String getFilePath() {
//         return filePath;
//     }
// }
