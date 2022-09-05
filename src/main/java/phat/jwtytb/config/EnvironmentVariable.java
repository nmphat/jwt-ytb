// package phat.jwtytb.config;
//
// import lombok.Data;
// import lombok.EqualsAndHashCode;
// import lombok.NoArgsConstructor;
// import lombok.ToString;
// import lombok.extern.log4j.Log4j2;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.stereotype.Component;
//
// @Data
// @Component
// @Log4j2
// public class EnvironmentVariable {
//     @Value("${secret}")
//     public String secret;
//
//     @Bean
//     protected void testVars() {
//         log.error("SECRET [{}]", secret);
//     }
// }
