# thecats
The new TheCatApi.

Collections Postman -> no projeto  https://github.com/Daciano0/thecats

## Passo a passo para testar o projeto Projeto thecat: Esse é o projeto base. Mas tem o outro projeto que seria a interação com as bases - 

https://github.com/Daciano0/typesofcat

Use o terminal Linux ou GitBash

 1 - Baixa o projeto - comando -> git clone <clone> 
 2 - Na raiz do projeto existe um arquivo 'docker-compose' - comando -> docker-compose up -d 
 3 - No terminal use o comando -> docker ps 
 4 - Faça a opção abaixo. Opções essas com a IDE de sua preferência ,ou use o docker para testar as imagens
# Obs; após configurar o consul, será necessário restartar o container 'thecat' para acessar ao mongo e urls da thecatapi comando  - docker restart thecat

======

Rodando local com docker:

Consul usado para service registry.

Projeto thecat -> Favor jogar isso dentro do consul -> no browser http://0.0.0.0:8500/ui/dc1/kv - >

clicar no botão CREATE (cor azul) e crie o path -> config/thecat-breeds/data  -> 

Obs; Padrão yml o item abaixo.

spring:
  cloud:
    discovery:
      enabled: false
    loadbalancer:
      ribbon:
        enabled: false
  data:
    mongodb:
      host: mongo-cat
      port: 27017
thecat:
  url_cat: https://api.thecatapi.com/v1/breeds
  url_address: https://api.thecatapi.com/v1/images/search 
  connectTimeout: 20000
  readTimeout: 25000

 
===== 
 4.1 - > Rodar sem o docker o projeto thecat, vai precisar alterar o arquivo bootstrap.ym  o host do consul para: 0.0.0.0:8500  


======
Rodando local com a IDE:

Consul usado para service registry.

Projeto thecat -> Favor jogar isso dentro do consul -> no browser http://0.0.0.0:8500/ui/dc1/kv - >

clicar no botão CREATE (cor azul) e crie o path -> config/thecat-breeds/data  -> 


spring:
  cloud:
    discovery:
      enabled: false
    loadbalancer:
      ribbon:
        enabled: false
  data:
    mongodb:
      host: localhost
      port: 27017
thecat:
  url_cat: https://api.thecatapi.com/v1/breeds
  url_address: https://api.thecatapi.com/v1/images/search 
  connectTimeout: 20000
  readTimeout: 25000
  securitycat: bigsecurity
  
======


# 5 - Vamos falar sobre os endpoints:

usar o docker como referência para - os paths;

Verbo http | Path

POST - http://0.0.0.0:8080/address/cats/save  -> Salva as informações de todos os gatos da api (https://thecatapi.com/)

Header com - Basic YWRtaW46Ymlnc2VjdXJpdHk=    | Precisa para acessar aplicação, por conta do status 401 (Não autorizado).

POST - http://0.0.0.0:8080/cats/save - > Salva as fotos dos gatos gerados antes.

Header com - Basic YWRtaW46Ymlnc2VjdXJpdHk=    Precisa para acessar aplicação, por conta do status 401 (Não autorizado).

Obs;

para o local é só trocar o path para:  http://localhost:8080/

 ======
 
 ##Passo a passo para testar o projeto Projeto typescat:
 
 Collections Postman -> no projeto https://github.com/Daciano0/typesofcat
 
 No mesmo docker-compose já geramos esse projeto. Os passos são os mesmo, porém muda o path do consul e a configuração.

 1 - No terminal use o comando -> docker ps 
 2 - Faça a opção abaixo. Opções essas com a IDE de sua preferência ,ou use o docker para testar as imagens
# Obs; após configurar o consul, será necessário restartar o container 'typescat' para acessar ao mongo e urls da thecatapi comando  - docker restart typescat


caminho de config ==> config/typesofcat-breeds/data
 
 
spring:
  cloud:
    discovery:
      enabled: false
    loadbalancer:
      ribbon:
        enabled: false
  data:
    mongodb:
      host: mongo-cat
      port: 27017

=====

# 5 - Vamos falar sobre os endpoints:

usar o docker como referência para - os paths;

Verbo http | Path

GET -List - http://0.0.0.0:8081//types/list  -> Lista todos os gatos
GET - http://0.0.0.0:8081/types/breed - >  Pega o gato por raça
GET -List http://0.0.0.0.0:8081/types/temperament/list   -> Lista de todos os gatos pela temperamento
GET -List http://0.0.0.0.0:8081/types/origin/list   - > Lista de todos os gatos pela origin

GET -List -Criteria http://localhost:8081/types/breeds/queries ->  Usei criteria para fazer qualquer tipo de consulta possível. Header com - Basic YWRtaW46Ymlnc2VjdXJpdHk=    | Precisa para acessar aplicação, por conta do status 401 (Não autorizado) Soment desse path.

GET -List - http://localhost:8080//types/list  -
GET - http://localhost:8080/types/breed
GET -List http://localhost:8080/types/temperament/list
GET -List http://localhost:8080/types/origin/list
GET -List -Criteria http://localhost:8080/types/breeds/queries
