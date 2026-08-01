package com.mcp_server_app.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mcp_server_app.tool.BookTools;

@Configuration
public class McpToolConfig {

    /**
     * Spring AI's MCP server auto-configuration picks up any
     * ToolCallbackProvider bean and registers its tools with the MCP server.
     */
    @Bean
    public ToolCallbackProvider bookToolCallbackProvider(BookTools bookTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(bookTools)
                .build();
    }
}
