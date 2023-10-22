# Use a imagem oficial do OpenJDK 17 como imagem base
FROM openjdk:17

# Define um diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR da sua aplicação Spring Boot para o diretório de trabalho no contêiner
COPY build/libs/application.jar app.jar

# Expõe a porta em que a aplicação Spring Boot está em execução
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]