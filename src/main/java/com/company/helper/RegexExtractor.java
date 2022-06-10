package com.company.helper;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.constants.RegularExpressions.CODE_REGEX;

@Component
public class RegexExtractor {
    public String createRegex(String code, String regex) {
        return regex.replaceFirst(CODE_REGEX, code);
    }

    public String getFirst(String body, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(body);
        String value = "";
        if (matcher.find()) {
            value = matcher.group(1);
        }
        return value;
    }
}
