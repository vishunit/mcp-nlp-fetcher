package com.example.mcpnlp;

import org.apache.hc.client5.http.fluent.Request;

public class ApiService {
    public static String fetchUserData(String searchValue) throws Exception {
        String url = "http://172.25.48.177:9999/jigsaw/v2/ums/user/search?searchValue=" + searchValue;
        return Request.get(url)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Imp3cy1rZXktcHVibGljLTEifQ.eyJzY29wZSI6WyJCQUxBTkNFQU5ERFVFIiwiQkFMRU5RIiwiQk5LQkFMRU5RIiwiREFJTFlfV0FMTEVUX0JBTEFOQ0UiLCJHRVRCQUwiLCJHRVRCQUxBTkNFIiwiR0VUSU5TVFdBTEJBTCIsIkdFVFVTRVIiLCJHRVRVU0VSX0JZX1BBWU1FTlRIQU5ETEUiLCJHRVRVU1JDQVRQUk9GSUxFIiwiR0VUX0lOU1RSVU1FTlRTX0JZX1VTRVJJRCIsIkdFVF9TRUxGX1VTRVJfQU5EX0FDQ09VTlRfREVUQUlMUyIsIkdFVF9VU0VSX0FORF9BQ0NPVU5UX0RFVEFJTFMiLCJHRVRfVVNFUl9LWUMiLCJPTFlCQUxFTlEiLCJVU1JTVE1UIiwiV0FMTEVUQkFMRU5RIl0sImV4cCI6MTc1MTU2NzM1MSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9DTElFTlQiXSwianRpIjoiNWRhODg3NDMtNWNjOC00MDFiLWE3MGEtMjY1NGFjOWRjM2QxIiwiY2xpZW50X2lkIjoiTWVudU1hbmFnZXJVU1NEIn0.eoyDyDbbFLaZ6nthQqYRAaJUMyPitpBfte2W2SFjPcmmZy2cjmblldLm33rsnv_98F_MjsWJklLhO1ZK-RiP3ZVnCAE469svILUAyMvU2agG8fCbnGiZaTwD_J7NpXqtkQbzN6lWXGYcJF3tYRLPh9eZF9izQDeFDIHSBlU7tjb4FeBZg8ESh84aaTqmgES-EJnuECpR3Q6M8T2e62gaoijJICf8iSvB7bPdYtTShG7Ze3xhvFHsSywQFiu7uCE8qETNFXge-TGvrHRkAkotiUiCMNG3WO3wH6QC9NNHgXWZmsfd6mX-tl9NZYo152NjBmUJcMcQUi4T5izrbgEb4w")
                .addHeader("SkipSecurityHeaderValidation", "true")
                .addHeader("SkipPayloadEncryption", "true")
                .addHeader("X-Channel", "WEB")
                .addHeader("X-DeviceId", "123")
                .addHeader("X-Client-IP", "0.0.0.0")
                .execute()
                .returnContent()
                .asString();
    }
}
