package web_main;

import Web_Function.web_function;
import com.sun.net.httpserver.HttpServer;
import data.DataInit;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    public static void main(String[] args) throws IOException {
        web_function function = new web_function();
        DataInit.initData(function.getMatchManager());

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/matches", function::handleMatches);
        server.createContext("/api/match/", function::handleMatchTeams);
        server.createContext("/api/team/", function::handleTeamMembers);
        server.start();
        System.out.println("服务器已启动: http://localhost:8080");
        System.out.println("试试访问: http://localhost:8080/api/matches");
    }
}
