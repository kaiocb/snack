
# Snack Server.

O projeto consiste em determinados serviços REST afim de suprir a lógica necessaria para o funcionamento da camada de aplicação.

[Definição do projeto](PROJECT.md)

Utilizando Spring Boot (versão 1.5.2) como principal framework para manter coesão de tecnologias, utilizei a versão mencionada 
pois ainda não li todo o changelog da versão atual 1.5.3.

#### Gerenciamento de Dependências
Foi utilizado Maven como gerenciador de dependências porém estou utilizando mais o Gradle como gerenciador e task builder 
e aproveitando a estrutura de repositórios do maven.

#### REST API
Substitui o convencional Spring MVC para utilizar o Jersey JAX-RS para construir os serviços, ambos têm diversos compentências
mas fui por uma decisão de usabilidade e agilidade, embora nesse projeto tenha sido feito com o Jersey, creio que a abordagem
com o Spring MVC seja mais adequada se for utilizar todos os outros módulos disponíveis no framework.

#### Servlet Container
Utilizando o padrão do spring-boot application: embbeded tomcat.
 
#### Projeto
Estruturalmente o projeto pode ser classificado em 2 partes o domínio (domain), onde são encontrados as entities/beans/components
junto com seus respectivos repositórios, tais repositórios são a camada de acesso aos dados, seguindo o princípio de data access objects.

As classes de entidades são utilizadas para mapear/estruturar o conjuntos de dados que serão manipulados pelos serviços.
Para o projeto defini três entidades: 
* Refeição (Meal): utilizada na camada de aplicação como o "burger".
* Ingredientes (Ingredient): elmentos de construção da refeição.
* Desconto (Discount): descontos que podem ser aplicacados em uma refeição.

Seus repositorios contêm a instanciação, manipulação e chacagem dessas entidades.

Por fim temos os serviços para listagem de refeições e ingredientes e o serviço mais importante que provê o cálculo da refeição
retornando o preço final e o conjunto de descontos caso tenham sido aplicados.

# HOWTO

#### REQUISITOS
* Java, Git, Maven

#### INSTRUÇõES

* `git clone https://github.com/kaiocb/snack-server.git`

* goto: `cd snack-server`

* test: `mvn test`

* run: `mvn spring-boot:run`

* build: `mvn install`
    * `java -jar target/snack-1.0.jar` (broken - [https://github.com/jersey/jersey/issues/2357])