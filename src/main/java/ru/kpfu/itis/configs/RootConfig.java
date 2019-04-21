package ru.kpfu.itis.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.kpfu.itis.forms"})
public class RootConfig{}
