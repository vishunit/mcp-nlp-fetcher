package com.example.mcpnlp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import java.util.Map;

public class ResponseExtractor {

    public static String getFieldValue(String json, String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            JsonNode users = root.path("users");
            if (users.isArray() && users.size() > 0) {
                return getFieldRecursive(users.get(0), path);
            }

            JsonNode userDetails = root.path("userDetails");
            if (!userDetails.isMissingNode()) {
                return getFieldRecursive(userDetails, path);
            }

            return "No valid user data found.";

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private static String getFieldRecursive(JsonNode node, String path) {
        try {
            String[] parts = path.split("\\.");
            JsonNode current = node;

            for (String part : parts) {
                if (part.contains("[")) {
                    // Array access like field[0]
                    String field = part.substring(0, part.indexOf("["));
                    int index = Integer.parseInt(part.substring(part.indexOf("[") + 1, part.indexOf("]")));
                    current = current.path(field);
                    if (!current.isArray() || current.size() <= index) {
                        return fuzzySearch(node, part); // fallback
                    }
                    current = current.get(index);
                } else {
                    current = current.path(part);
                    if (current.isMissingNode()) {
                        return fuzzySearch(node, part); // fallback
                    }
                }
            }

            return current.isValueNode() ? current.asText() : current.toString();
        } catch (Exception e) {
            return "Error during field traversal: " + e.getMessage();
        }
    }

    private static String fuzzySearch(JsonNode node, String targetKey) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            if (entry.getKey().equalsIgnoreCase(targetKey)) {
                JsonNode val = entry.getValue();
                return val.isValueNode() ? val.asText() : val.toString();
            }
        }

        // Try deeper in nested objects
        for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext();) {
            Map.Entry<String, JsonNode> entry = it.next();
            JsonNode child = entry.getValue();
            if (child.isContainerNode()) {
                String result = fuzzySearch(child, targetKey);
                if (!result.startsWith("Field")) return result;
            }
        }

        return "Field not found: " + targetKey;
    }
}
