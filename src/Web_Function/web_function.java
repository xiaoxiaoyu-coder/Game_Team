package Web_Function;

import com.sun.net.httpserver.HttpExchange;
import function.Match_Manager;
import role.Match;
import role.Member;
import role.Team;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class web_function {

    private final Match_Manager matchManager = new Match_Manager();

    public Match_Manager getMatchManager() {
        return matchManager;
    }

    // 处理 GET /api/matches：返回所有比赛
    public void handleMatches(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            sendJson(exchange, "{\"error\":\"只支持 GET\"}");
            return;
        }
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < matchManager.getAll().size(); i++) {
            Match m = matchManager.getAll().get(i);
            json.append("{\"id\":").append(m.getMatch_ID())
                .append(",\"name\":\"").append(m.getMatch_Name())
                .append("\",\"description\":\"").append(m.getMatch_Description()).append("\"}");
            if (i < matchManager.getAll().size() - 1) json.append(",");
        }
        json.append("]");
        sendJson(exchange, json.toString());
    }

    // 处理 GET /api/match/{id}/teams：返回指定比赛及其参赛战队
    public void handleMatchTeams(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            sendJson(exchange, "{\"error\":\"只支持 GET\"}");
            return;
        }
        // 路径形如 /api/match/1/teams
        int matchId = parseIdFromPath(exchange, 3);
        if (matchId < 0) {
            sendJson(exchange, "{\"error\":\"路径应为 /api/match/{id}/teams\"}");
            return;
        }
        for (Match m : matchManager.getAll()) {
            if (m.getMatch_ID() == matchId) {
                sendJson(exchange, matchToJson(m));
                return;
            }
        }
        sendJson(exchange, "{\"error\":\"未找到比赛 " + matchId + "\"}");
    }

    // 处理 GET /api/team/{id}/members：返回指定战队及其选手
    public void handleTeamMembers(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            sendJson(exchange, "{\"error\":\"只支持 GET\"}");
            return;
        }
        // 路径形如 /api/team/1/members
        int teamId = parseIdFromPath(exchange, 3);
        if (teamId < 0) {
            sendJson(exchange, "{\"error\":\"路径应为 /api/team/{id}/members\"}");
            return;
        }
        // 在所有比赛的战队里查找
        for (Match match : matchManager.getAll()) {
            for (Team t : match.getTeams()) {
                if (t.getTeam_ID() == teamId) {
                    sendJson(exchange, teamToJson(t));
                    return;
                }
            }
        }
        sendJson(exchange, "{\"error\":\"未找到战队 " + teamId + "\"}");
    }

    // 从 /api/xxx/{id}/yyy 路径中取出 id，失败返回 -1
    private int parseIdFromPath(HttpExchange exchange, int index) {
        String[] parts = exchange.getRequestURI().getPath().split("/");
        if (parts.length <= index) return -1;
        try {
            return Integer.parseInt(parts[index]);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String matchToJson(Match m) {
        StringBuilder json = new StringBuilder();
        json.append("{\"id\":").append(m.getMatch_ID())
            .append(",\"name\":\"").append(m.getMatch_Name())
            .append("\",\"description\":\"").append(m.getMatch_Description())
            .append("\",\"teams\":[");
        for (int i = 0; i < m.getTeams().size(); i++) {
            Team t = m.getTeams().get(i);
            json.append("{\"id\":").append(t.getTeam_ID())
                .append(",\"name\":\"").append(t.getTeam_Name())
                .append("\",\"description\":\"").append(t.getTeam_Description()).append("\"}");
            if (i < m.getTeams().size() - 1) json.append(",");
        }
        json.append("]}");
        return json.toString();
    }

    private String teamToJson(Team t) {
        StringBuilder json = new StringBuilder();
        json.append("{\"id\":").append(t.getTeam_ID())
            .append(",\"name\":\"").append(t.getTeam_Name())
            .append("\",\"description\":\"").append(t.getTeam_Description())
            .append("\",\"members\":[");
        for (int i = 0; i < t.getMembers().size(); i++) {
            Member m = t.getMembers().get(i);
            json.append("{\"id\":").append(m.getMember_ID())
                .append(",\"name\":\"").append(m.getMember_Name())
                .append("\",\"description\":\"").append(m.getMember_Description()).append("\"}");
            if (i < t.getMembers().size() - 1) json.append(",");
        }
        json.append("]}");
        return json.toString();
    }

    private void sendJson(HttpExchange exchange, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        // 允许跨域：WebStorm 页面在不同端口，必须加这个头
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
