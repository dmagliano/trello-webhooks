# Trello-webhook project
## Projeto desenvolvido em 2021 no prog. de estágio da [Mobicare](https://www.mobicare.com.br/)
A ideia do projeto é poder acompanhar a movimentação de cards em um board do trello para atribuição de pontos, geração de métricas sobre entregas. Fazemos isso através dos webhooks, do trello, um serviço que fazem chamadas rest, do tipo POST para um endpoint especificado, passando a ação que foi feita em um card, lista ou board. Tudo pode ser monitora.

## [Falo do projeto neste post do Medium](https://medium.com/mobicareofficial/gamifica%C3%A7%C3%A3o-de-entregas-no-trello-24c8bd1e31bb)

This project uses Quarkus, the Supersonic Subatomic Java Framework.
### Running the application in dev mode
You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ 
