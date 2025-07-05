package com.example.mcpnlp;

public class InputParser {

    public static String extractSearchValue(String input) {
        // 1. Match a 10-digit number (likely MSISDN)
        java.util.regex.Matcher numberMatch = java.util.regex.Pattern.compile("\\b\\d{10}\\b").matcher(input);
        if (numberMatch.find()) return numberMatch.group();

        // 2. Match email
        java.util.regex.Matcher emailMatch = java.util.regex.Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+").matcher(input);
        if (emailMatch.find()) return emailMatch.group();

        // 3. Match any long alphanumeric identifier (6+ characters)
        java.util.regex.Matcher idMatch = java.util.regex.Pattern.compile("\\b\\w{6,}\\b").matcher(input);
        if (idMatch.find()) return idMatch.group();

        return "";
    }


    public static String extractField(String input) {
        String lower = input.toLowerCase();
        int index = 0;
        if (lower.contains("second")) index = 1;
        else if (lower.contains("third")) index = 2;

        // Extract the field asked using regex: get/give me <field> for ...
        String fieldRaw = input.replaceAll("(?i).*?(get|give me)\\s*", "")
                .replaceAll("(?i)\\s*for.*", "")
                .trim()
                .replaceAll("[-_\\s]", "")  // normalize spaces, hyphens
                .toLowerCase();

        // Known mappings
        if (fieldRaw.contains("firstname")) return "profileDetails.firstName";
        if (fieldRaw.contains("lastname")) return "profileDetails.lastName";
        if (fieldRaw.contains("middlename")) return "profileDetails.middleName";
        if (fieldRaw.contains("email")) return "notificationEndpointRequests[" + index + "].notificationUrl";
        if (fieldRaw.contains("sms")) return "notificationEndpointRequests[" + index + "].notificationUrl";
        if (fieldRaw.contains("notification")) return "notificationEndpointRequests[" + index + "].notificationUrl";
        if (fieldRaw.contains("loginid")) return "loginIdentifiers[" + index + "].value";
        if (fieldRaw.contains("kycidvalue") || fieldRaw.contains("kycvalue")) return "kycs[" + index + "].kycIdValue";
        if (fieldRaw.contains("kycidtype")) return "kycs[" + index + "].kycIdType";
        if (fieldRaw.contains("dob")) return "profileDetails.dateOfBirth";
        if (fieldRaw.contains("fathername")) return "profileDetails.fatherName";
        if (fieldRaw.contains("mothername")) return "profileDetails.motherName";
        if (fieldRaw.contains("spousename")) return "profileDetails.spouseFirstName";
        if (fieldRaw.contains("balance")) return "accountGroupDetails[0].accountDetails[" + index + "].balances.balance";
        if (fieldRaw.contains("accountnumber")) return "accountGroupDetails[0].accountDetails[" + index + "].accountNumber";
        if (fieldRaw.contains("productname")) return "accountGroupDetails[0].accountDetails[" + index + "].productName";
        if (fieldRaw.contains("region")) return "profileDetails.region";
        if (fieldRaw.contains("city")) return "profileDetails.city";
        if (fieldRaw.contains("state")) return "profileDetails.state";
        if (fieldRaw.contains("country")) return "profileDetails.countryName";

        return fieldRaw;
    }

}
