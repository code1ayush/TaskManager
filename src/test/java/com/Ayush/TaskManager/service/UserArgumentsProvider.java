package com.Ayush.TaskManager.service;

import com.Ayush.TaskManager.Entity.User;
import net.bytebuddy.implementation.MethodCall;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("aish").password("aish").build()),
                Arguments.of(User.builder().userName("akash").password("akash").build()),
                Arguments.of(User.builder().userName("userWithoutpass").password("").build())
        );
    }
}
