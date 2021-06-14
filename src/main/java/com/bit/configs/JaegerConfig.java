package com.bit.configs;

import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaegerConfig {

    @Bean
    public JaegerTracer jaegerTracer() {

        return new io.jaegertracing.Configuration("b5it-final-project-client-app")
                .withSampler(
                    new io.jaegertracing.Configuration.SamplerConfiguration().withType(ConstSampler.TYPE)
                        .withParam(1)
                )
                .withReporter(
                    new io.jaegertracing.Configuration.ReporterConfiguration().withLogSpans(true)
                )
                .getTracer();
    }
}
