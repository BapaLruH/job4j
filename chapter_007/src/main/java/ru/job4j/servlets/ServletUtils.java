package ru.job4j.servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface ServletUtils {
    HashMap<String, Function<HttpServletRequest, Map<String, String>>> ACTIONS = new HashMap<>();

    static Map<String, String> execute(HttpServletRequest req) {
        String action = req.getParameter("action");
        Function<HttpServletRequest, Map<String, String>> function = ACTIONS.getOrDefault(action, request -> {
            throw new UnsupportedOperationException(String.format("Action %s is not found", action));
        });
        return function.apply(req);
    }

    Map<String, String> add(HttpServletRequest req);

    Map<String, String> update(HttpServletRequest req);

    Map<String, String> delete(HttpServletRequest req);
}
