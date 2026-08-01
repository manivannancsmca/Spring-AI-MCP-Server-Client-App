package com.mcp_client_app.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatModel chatModel, ToolCallbackProvider mcpToolCallbackProvider) {
        return ChatClient.builder(chatModel)
                .defaultSystem("""
                    You are a library assistant. You can create, read, update, delete,
                    and search for books using the tools available to you. Always confirm
                    destructive actions (like delete) by stating what was removed.
                    """)
                .defaultToolCallbacks(mcpToolCallbackProvider.getToolCallbacks())
                .build();
    }
}
