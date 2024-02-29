package com.trello.utils.config;

import com.trello.utils.JsonUtils;
import lombok.Data;

@Data
public class TrelloApiRoute {
    private String baseUrl;
    private String basePath;
    private String boards;
    private String cards;
    private String lists;
    private String allBoards;

    public static TrelloApiRoute trelloApiPath() {
        return JsonUtils.to("src/test/resources/configs/api_path.json", TrelloApiRoute.class);
    }
}
