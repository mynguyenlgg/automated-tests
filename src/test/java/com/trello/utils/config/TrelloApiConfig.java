package com.trello.utils.config;

import com.trello.utils.JsonUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrelloApiConfig {
    private TrelloApiRoute apiPath;

}
